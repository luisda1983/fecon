package es.ldrsoftware.core.btc.job;


import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.Btc;
import es.ldrsoftware.core.arq.LoggerManager;
import es.ldrsoftware.core.arq.util.DateTimeData;
import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.core.btc.bs.BsBtchList;
import es.ldrsoftware.core.btc.bs.BsBtchListArea;
import es.ldrsoftware.core.btc.bs.BsEjecPlan;
import es.ldrsoftware.core.btc.bs.BsEjecPlanArea;
import es.ldrsoftware.core.btc.entity.Btch;
import es.ldrsoftware.core.btc.entity.Ejec;
import es.ldrsoftware.core.fwk.bs.BsParaTbla;
import es.ldrsoftware.core.fwk.data.PVFechperiod;
import es.ldrsoftware.core.fwk.data.ParaData;

@Component
public class BtcPlanificador extends Btc {

	@Autowired
	LoggerManager logger;

	@Autowired
	BsBtchList bsBtchList;

	@Autowired
	BsEjecPlan bsEjecPlan;
	
	@Autowired
	BsParaTbla bsParaTbla;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void executeBtc() throws Exception {

		logger.logProc("Planificador", 0, "EJECUCION DE PROCESO: Planificador");
		logger.logProc("Planificador", 4, "Fecha de ejecución: " + SESSION.get().feop);
		logger.logProc("Planificador", 4, "Hora de ejecución: " + SESSION.get().hoop);
		logger.logProc("Planificador", 4, "Fecha de proceso: " + SESSION.get().fbtc);
		
		BsBtchListArea bsBtchListArea = new BsBtchListArea();
		bsBtchListArea.IN.tipo = BsBtchList.BTCH_LIST_AUTO;
		bsBtchList.executeBS(bsBtchListArea);
		
		List<Btch> btchList = bsBtchListArea.OUT.btchList;
		logger.logProc("Planificador", 1, "Número de Batchs a planificar: " + btchList.size());
		
		ListIterator<Btch> itBtch = btchList.listIterator();
		
		while (itBtch.hasNext()) {
			Btch btch = itBtch.next();
			
			logger.logProc("Planificador", 2, "Preparando planificación de proceso : " + btch.getIden());
			
			switch(btch.getTipo()) {
			case ParaData.PM_EL_BTCHAVANCE_DIARIO:
				 planificaDiario(btch);
		 		 break;
			case ParaData.PM_EL_BTCHAVANCE_MENSUAL:
				 planificaMensual(btch);
				 break;
			default:
				logger.logProc("Planificador", 2, "Tipo de Batch no contemplado: " + btch.getTipo() + ". Proceso no tratado.");
			}
		}
		
		logger.logProc("Planificador", 0, "FIN DE EJECUCION DE PROCESO: Planificador");
	}
	
	private void planificaDiario(Btch btch) throws Exception {
	
		int _cont = 0;
		
		logger.logProc("Planificador", 3, "Planificación de proceso de periodicidad diaria.");
		
		//Montamos la fecha/hora de referencia
		DateTimeData dtdRefe = new DateTimeData();
		dtdRefe.fech = SESSION.get().fbop;
		dtdRefe.hora = SESSION.get().hbop;
		
		logger.logProc("Planificador", 3, "Fecha de referencia: " + dtdRefe.fech + ":" + dtdRefe.hora);
		
		//Montamos el periodo de avance: 1 día
		PVFechperiod pv = new PVFechperiod();
		pv.avre = "+";
		pv.DD = 1;
		
		//Avanzamos 15 días desde la fecha de referencia
		for (int i = 1; i <= 15; i++) {
			dtdRefe = DateTimeUtil.fechPeriod(dtdRefe, pv);
		}
		
		logger.logProc("Planificador", 3, "Planificar hasta: " + dtdRefe.fech + ":" + dtdRefe.hora);
		
		//Montamos la fecha de última planificación del proceso, teniendo en cuenta que pueda que nunca haya
		//  sido planificado
		DateTimeData dtdProc = new DateTimeData();
		if (btch.getFupl() == 0) {
			dtdProc.fech = SESSION.get().fbop;
			dtdProc.hora = SESSION.get().hbop;
		} else {
			dtdProc.fech = btch.getFupl();
			dtdProc.hora = btch.getHupl();
		}
		
		logger.logProc("Planificador", 3, "Fecha de últma planificación del proceso: " + dtdProc.fech + ":" + dtdRefe.hora);
		
		//Si la fecha calculada es anterior a la fecha de referencia, faltan planificaciones
		if (btch.getFupl() < dtdRefe.fech) {
			//Planificamos hasta alcanzar la fecha de referencia
			while (dtdRefe.fech > btch.getFupl()) {
				//Avanzamos un día
				dtdProc = DateTimeUtil.fechPeriod(dtdProc, pv);
				//Realizamos el ajuste a la hora parametrizada
				dtdProc.hora = btch.getAux1();
				
				//Planificamos la ejecución
				BsEjecPlanArea bsEjecPlanArea = new BsEjecPlanArea();
				bsEjecPlanArea.IN.btch = btch.getIden();
				bsEjecPlanArea.IN.fech = dtdProc.fech;
				bsEjecPlanArea.IN.hora = dtdProc.hora;
				bsEjecPlanArea.IN.secu = 1;
				bsEjecPlanArea.IN.ajus = true;
				bsEjecPlan.executeBS(bsEjecPlanArea);
				
				btch = bsEjecPlanArea.OUT.btch;
				Ejec ejec = bsEjecPlanArea.OUT.ejec; 
				
				logger.logProc("Planificador", 3, "Planificación de proceso: " + btch.getIden() + " [" + ejec.getFech() + "][" + ejec.getHora() + "]");
				
				_cont++;
			}
		} else {
			logger.logProc("Planificador", 3, "No es necesario realizar ninguna planificación.");
		}
		
		logger.logProc("Planificador", 2, "Fin de planificación de proceso. Planificaciones realizadas: " + _cont);
	}
	
	private void planificaMensual(Btch btch) throws Exception {
		
		int _cont = 0;
		
		logger.logProc("Planificador", 3, "Planificación de proceso de periodicidad mensual.");
		
		//Montamos la fecha/hora de referencia
		DateTimeData dtdRefe = new DateTimeData();
		dtdRefe.fech = SESSION.get().fbop;
		dtdRefe.hora = SESSION.get().hbop;
		
		logger.logProc("Planificador", 3, "Fecha de referencia: " + dtdRefe.fech + ":" + dtdRefe.hora);
		
		//Montamos el periodo de avance: 1 mes
		PVFechperiod pv = new PVFechperiod();
		pv.avre = "+";
		pv.MM = 1;
		
		//Avanzamos 6 meses desde la fecha de referencia
		for (int i = 1; i <= 6; i++) {
			dtdRefe = DateTimeUtil.fechPeriod(dtdRefe, pv);
		}
		
		logger.logProc("Planificador", 3, "Planificar hasta: " + dtdRefe.fech + ":" + dtdRefe.hora);
		
		//Montamos la fecha de última planificación del proceso, teniendo en cuenta que pueda que nunca haya
		//  sido planificado
		DateTimeData dtdProc = new DateTimeData();
		if (btch.getFupl() == 0) {
			dtdProc.fech = SESSION.get().fbop;
			dtdProc.hora = SESSION.get().hbop;
		} else {
			dtdProc.fech = btch.getFupl();
			dtdProc.hora = btch.getHupl();
		}
		
		logger.logProc("Planificador", 3, "Fecha de últma planificación del proceso: " + dtdProc.fech + ":" + dtdRefe.hora);
		
		//Si la fecha calculada es anterior a la fecha de referencia, faltan planificaciones
		if (btch.getFupl() < dtdRefe.fech) {
			//Planificamos hasta alcanzar la fecha de referencia
			while (dtdRefe.fech > btch.getFupl()) {
				//Avanzamos un día
				dtdProc = DateTimeUtil.fechPeriod(dtdProc, pv);
				//Realizamos el ajuste al día y hora parametrizados
				dtdProc.hora = btch.getAux1();
				int f = dtdProc.fech / 100;
				f = f * 100 + btch.getAux2();
				dtdProc.fech = f;
				
				//Planificamos la ejecución
				BsEjecPlanArea bsEjecPlanArea = new BsEjecPlanArea();
				bsEjecPlanArea.IN.btch = btch.getIden();
				bsEjecPlanArea.IN.fech = dtdProc.fech;
				bsEjecPlanArea.IN.hora = dtdProc.hora;
				bsEjecPlanArea.IN.secu = 1;
				bsEjecPlanArea.IN.ajus = true;
				bsEjecPlan.executeBS(bsEjecPlanArea);
				
				btch = bsEjecPlanArea.OUT.btch;
				Ejec ejec = bsEjecPlanArea.OUT.ejec; 
				
				logger.logProc("Planificador", 3, "Planificación de proceso: " + btch.getIden() + " [" + ejec.getFech() + "][" + ejec.getHora() + "]");
				
				_cont++;
			}
		} else {
			logger.logProc("Planificador", 3, "No es necesario realizar ninguna planificación.");
		}
		
		logger.logProc("Planificador", 2, "Fin de planificación de proceso. Planificaciones realizadas: " + _cont);
	}
}
