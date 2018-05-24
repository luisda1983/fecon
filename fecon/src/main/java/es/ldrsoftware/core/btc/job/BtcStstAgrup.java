package es.ldrsoftware.core.btc.job;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.Btc;
import es.ldrsoftware.core.arq.LoggerManager;
import es.ldrsoftware.core.sts.entity.Stdi;
import es.ldrsoftware.core.sts.entity.StdiDao;
import es.ldrsoftware.core.sts.entity.Stst;
import es.ldrsoftware.core.sts.entity.StstDao;

@Component
public class BtcStstAgrup extends Btc {

	@Autowired
	LoggerManager logger;

	@Autowired
	public StstDao ststDao;

	@Autowired
	public StdiDao stdiDao;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void executeBtc() throws Exception {

		logger.logProc("StstAgrup", 0, "EJECUCION DE PROCESO: FbtcAvance");
		logger.logProc("StstAgrup", 4, "Fecha de ejecución: " + SESSION.get().feop);
		logger.logProc("StstAgrup", 4, "Hora de ejecución: " + SESSION.get().hoop);
		logger.logProc("StstAgrup", 4, "Fecha de proceso: " + SESSION.get().fbtc);
		
		int fech = SESSION.get().fbtc;
		
		List<Stst> ststList = ststDao.getListByFeej(fech);
		logger.logProc("StstAgrup", 1, "Estadísticas totales registradas: " + ststList.size());
		
		ListIterator<Stst> it = ststList.listIterator();
		
		Stdi stdi = new Stdi();
		String ctrl = "";
		
		int tota = 0;
		long acum = 0;
		int tima = 0;
		int timi = 0;
		int nuer = 0;
		
		while (it.hasNext()) {
			Stst stst = it.next();
		
			if (ctrl.equals(stst.getCtrl())) {
				tota++;
				if (stst.getTiej() > tima) {
					tima = stst.getTiej();
				}
				if (stst.getTiej() < timi) {
					timi = stst.getTiej();
				}
				acum = acum + stst.getTiej();
				
				if ("V".equals(stst.getReej())) {
					nuer++;
				}
				
			} else {
				
				if (!"".equals(ctrl)) {
					stdi.setTota(tota);
					stdi.setTima(tima);
					stdi.setTimi(timi);
					stdi.setTime(new Float(new Float(acum) / new Float(tota)).floatValue());
					stdi.setNuer(nuer);
				
					stdiDao.save(stdi);
					
					logger.logProc("StstAgrup", 2, "Ejecuciones: " + stdi.getTota());
					logger.logProc("StstAgrup", 2, "Tiempo medio: " + stdi.getTime());
					logger.logProc("StstAgrup", 2, "Tiempo mínimo: " + stdi.getTimi());
					logger.logProc("StstAgrup", 2, "Tiempo máximo: " + stdi.getTima());
				}
				
				logger.logProc("StstAgrup", 1, "Tratando estadísticas de Controlador : " + stst.getCtrl());
				
				ctrl = stst.getCtrl();
				stdi = new Stdi();
				stdi.setFech(fech);
				stdi.setCtrl(stst.getCtrl());
				
				tota = 1;
				tima = stst.getTiej();
				timi = stst.getTiej();
				acum = stst.getTiej();
				if ("V".equals(stst.getReej())) {
					nuer = 1;
				} else {
					nuer = 0;
				}
				
			}
			
		}
		
		if (!"".equals(ctrl)) {
			stdi.setTota(tota);
			stdi.setTima(tima);
			stdi.setTimi(timi);
			stdi.setTime(new Float(new Float(acum) / new Float(tota)).floatValue());
			stdi.setNuer(nuer);
		
			stdiDao.save(stdi);
			
			logger.logProc("StstAgrup", 2, "Ejecuciones: " + stdi.getTota());
			logger.logProc("StstAgrup", 2, "Tiempo medio: " + stdi.getTime());
			logger.logProc("StstAgrup", 2, "Tiempo mínimo: " + stdi.getTimi());
			logger.logProc("StstAgrup", 2, "Tiempo máximo: " + stdi.getTima());
		}
		
		logger.logProc("StstAgrup", 0, "FIN DE EJECUCION DE PROCESO: StstAgrup");
	}
}
