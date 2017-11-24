package es.ldrsoftware.core.btc.job;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseNotifyManager;
import es.ldrsoftware.core.arq.BtcTxCtrl;
import es.ldrsoftware.core.arq.data.Session;
import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.core.btc.entity.Ejec;

@Component
public class JobPlanificador extends BaseNotifyManager {
	
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
	@Scheduled(cron = "0 00 21 * * *") /* Posible valor para producción (-6H = 3H-SPAIN) */
	public void planificador() {
		
		try {
			SESSION.set(new Session());
			SESSION.get().initializeBtcSession();
		
			btcTxCtrl.init();
			
			btcTxCtrl.getFbatch();
			
			btcTxCtrl.doPlanification();
			
			List<Ejec> ejecList = btcTxCtrl.getJobs();
			ListIterator<Ejec> it = ejecList.listIterator();
			
			while (it.hasNext()) {
				Ejec ejec = it.next();
				
				ejec.setFein(DateTimeUtil.getFeop());
				ejec.setHoin(DateTimeUtil.getHoop());
				long tiin = DateTimeUtil.getTime();
				
				try {
					btcTxCtrl.doJob(ejec.getBtch());
				} catch (Exception e) {
					ejec.setNotf(SESSION.get().EXEC_VOID.getIden());
				} finally {
					long tifi = DateTimeUtil.getTime();
					ejec.setEsta(SESSION.get().EXEC_STATE);
					ejec.setFefi(DateTimeUtil.getFeop());
					ejec.setHofi(DateTimeUtil.getHoop());
					ejec.setTiej(new Long(tifi - tiin).intValue());
					btcTxCtrl.doResult(ejec);
				}
			}
			
			btcTxCtrl.nextFbatch();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
