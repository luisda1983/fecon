package es.ldrsoftware.core.btc.job;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseNotifyManager;
import es.ldrsoftware.core.arq.BtcTxCtrl;
import es.ldrsoftware.core.arq.LoggerManager;
import es.ldrsoftware.core.arq.data.Session;
import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.core.btc.entity.Ejec;
import es.ldrsoftware.core.btc.entity.Mpla;
import es.ldrsoftware.core.btc.entity.Plan;
import es.ldrsoftware.core.fwk.data.LiteData;

@Component
public class JobPlan extends BaseNotifyManager {
	
	@Autowired
	LoggerManager logger;
	
	@Autowired
	BtcTxCtrl btcTxCtrl;
	
	/*
	 * Expresiones Cron
	 * second, minute, hour, day of month, month, day(s) of week
	 * (*) = any
	 * (* /x) = cada x
	 * (?) = sin especifica
	 */
	//@Scheduled(cron = "0 39 00 * * *") /* Para pruebas en desarrollo */
	//@Scheduled(cron = "0 00 21 * * *") /* Posible valor para producci�n (-6H = 3H-SPAIN) */
	@Scheduled(cron = "0 0 * * * *")
	//@Scheduled(cron = "0 * * * * *")
	public void planificador() {

		try {
			
			SESSION.set(new Session());
			SESSION.get().initializeBtcSession();
		
			//Lo primero que hacemos es obtener el puntero Batch y avanzarlo
			btcTxCtrl.getFbatch();
			btcTxCtrl.nextFbatch();
			logger.logBatch(0, "PLANIFICACIÓN DE PROCESOS ARRANCADA");
			logger.logBatch(4, "- Fecha de Planificación: " + SESSION.get().fbop);
			logger.logBatch(4, "- Hora de Planificación: " + SESSION.get().hbop);
			logger.logBatch(4, "- Fecha de Aplicación: " + SESSION.get().fbtc);
			
			btcTxCtrl.init();
			
			Mpla mpla = btcTxCtrl.getConfiguration();
			
			if (mpla == null) {
				logger.logBatch(0, "¡¡¡¡No existe franja de Planificación!!!!");
			} else if (LiteData.LT_EL_MPLAESTA_ACTIVADO.equals(mpla.getEsta())) {
				logger.logBatch(0, " ");
				logger.logBatch(1, "Franja de planificación ACTIVADA");
				
				//Pasamos la planificación a estado EN CURSO
				Plan plan = btcTxCtrl.getPlan();
				plan.setFbtc(SESSION.get().fbtc);
				plan.setFein(DateTimeUtil.getFeop());
				plan.setHoin(DateTimeUtil.getHoop());
				plan.setEsta(LiteData.LT_EL_PLANESTA_EN_CURSO);
				btcTxCtrl.doPlan(plan);
			
				logger.logBatch(1, "Pase Batch en curso");
				
				//Variables para el resumen de procesos que se grabarán en la planificación
				int proc = 0;
				boolean error = false;
				
				//Obtenemos todas las ejecuciones planificadas y las procesamos
				List<Ejec> ejecList = btcTxCtrl.getJobs();
				ListIterator<Ejec> it = ejecList.listIterator();
				
				logger.logBatch(1, "Total de Procesos planificados: " + ejecList.size());
				
				while (it.hasNext()) {
					Ejec ejec = it.next();
					
					logger.logBatch(2, "Ejecución Planificada: " + ejec.getBtch());
					
					ejec.setFein(DateTimeUtil.getFeop());
					ejec.setHoin(DateTimeUtil.getHoop());
					ejec.setFbtc(SESSION.get().fbtc);
					long tiin = DateTimeUtil.getTime();
						
					try {
						btcTxCtrl.doJob(ejec.getBtch());
					} catch (Exception e) {
						ejec.setNotf(SESSION.get().EXEC_VOID.getIden());
						error = true;
					} finally {
						long tifi = DateTimeUtil.getTime();
						String _esta = "";
						if (Session.EXEC_STATE_VOID.equals(SESSION.get().EXEC_STATE)) {
							ejec.setEsta(LiteData.LT_EL_EJECESTA_VOID);
							_esta = "VOID";
						} else {
							ejec.setEsta(LiteData.LT_EL_EJECESTA_FINALIZADA);
							_esta = "FINALIZADA";
						}
						SESSION.get().EXEC_STATE = Session.EXEC_STATE_OK;
						ejec.setFefi(DateTimeUtil.getFeop());
						ejec.setHofi(DateTimeUtil.getHoop());
						ejec.setTiej(new Long(tifi - tiin).intValue());
						ejec.setFepl(SESSION.get().fbop);
						ejec.setHopl(SESSION.get().hbop);
						
						logger.logBatch(2, "Resultado de ejecución: [" + ejec.getEsta() + "] " + _esta);
						logger.logBatch(2, "Tiempo de ejecución: " + ejec.getTiej() + "ms");
						if (LiteData.LT_EL_EJECESTA_VOID.equals(ejec.getEsta())) {
							logger.logBatch (3, "+ Código de Error: " + ejec.getNotf());
						}
						logger.logBatch(0, " ");
						btcTxCtrl.doResult(ejec);
							
						proc = proc + 1;
					}
				}
				String _esta = "";
				if (error) {
					plan.setEsta(LiteData.LT_EL_PLANESTA_ERROR);
					_esta = "ERROR";
				} else {
					plan.setEsta(LiteData.LT_EL_PLANESTA_FINALIZADO);
					_esta = "FINALIZADO";
				}
				plan.setFefi(DateTimeUtil.getFeop());
				plan.setHofi(DateTimeUtil.getHoop());
				plan.setProc(proc);
				
				long planTime = 0;
					
				if (plan.getFein() != plan.getFefi()) {
					planTime = plan.getHofi();
					planTime = planTime + 2400 - plan.getHoin();
						
				} else {
					planTime = plan.getHofi() - plan.getHoin();
				}
				
				if (plan.getVent() == 0) {
					plan.setVent(0015);
				}

				double porc = new Double(planTime / (plan.getVent() * 100));
				BigDecimal bd = new BigDecimal(porc);
				bd.setScale(6, RoundingMode.HALF_UP);
				plan.setPorc(bd.doubleValue());
				
				if (plan.getProc() == 0) {
					plan.setPorc(0);
				} else {
					if (plan.getPorc() == 0) {
						plan.setPorc(0.000001);
					}
				}

				btcTxCtrl.doPlan(plan);
				
				logger.logBatch(0, "FIN DE PASE BATCH. RESULTADO: [" + plan.getEsta() + "] " + _esta);
				
			} else if (LiteData.LT_EL_MPLAESTA_DESACTIVADO.equals(mpla.getEsta())){
				logger.logBatch(1, "Franja de planificación desactivada");
			} else {
				logger.logBatch(0, "¡¡¡¡Franja de Planificación con un estado incorrecto: " + mpla.getEsta() + "!!!!");
			}
			
		} catch (Exception e) {
			try {
				logger.logBatch(0, "¡¡¡¡EXCEPCION NO CONTROLADA DURANTE LA PLANIFICACION DE PROCESOS: " + e.getLocalizedMessage() + "!!!!");
			} catch (Exception e2) {}
			
			e.printStackTrace();
		}
	}
}
