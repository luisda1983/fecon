package es.ldrsoftware.core.btc.job;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.Btc;
import es.ldrsoftware.core.arq.LoggerManager;
import es.ldrsoftware.core.sts.bs.BsStdiList;
import es.ldrsoftware.core.sts.bs.BsStdiListArea;
import es.ldrsoftware.core.sts.bs.BsStmeSave;
import es.ldrsoftware.core.sts.bs.BsStmeSaveArea;
import es.ldrsoftware.core.sts.entity.Stdi;
import es.ldrsoftware.core.sts.entity.Stme;

@Component
public class BtcStdiAgrup extends Btc {

	@Autowired
	LoggerManager logger;

	@Autowired
	public BsStdiList bsStdiList;
	
	@Autowired
	public BsStmeSave bsStmeSave;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void executeBtc() throws Exception {

		logger.logProc("StdiAgrup", 0, "EJECUCION DE PROCESO: FbtcAvance");
		logger.logProc("StdiAgrup", 4, "Fecha de ejecución: " + SESSION.get().feop);
		logger.logProc("StdiAgrup", 4, "Hora de ejecución: " + SESSION.get().hoop);
		logger.logProc("StdiAgrup", 4, "Fecha de proceso: " + SESSION.get().fbtc);
		
		int fech = SESSION.get().fbtc;
		
		int yyyymm = fech / 100;
		int yyyy = yyyymm / 100;
		int mm = yyyymm - yyyy * 100;
		
		int dd = fech - yyyymm * 100;
		
		int fein = fech - dd;
		int fefi = fein + 99;
		
		logger.logProc("StdiAgrup", 4, "Periodo de proceso: " + fein + " - " + fefi);
		
		BsStdiListArea bsStdiListArea = new BsStdiListArea();
		bsStdiListArea.IN.tipo = BsStdiList.STDI_LIST_BTWN_FECH;
		bsStdiListArea.IN.fein = fein;
		bsStdiListArea.IN.fefi = fefi;
		bsStdiList.executeBS(bsStdiListArea);
		
		List<Stdi> stdiList = bsStdiListArea.OUT.stdiList;
		logger.logProc("StdiAgrup", 1, "Estadísticas diarias totales registradas: " + stdiList.size());
		
		ListIterator<Stdi> it = stdiList.listIterator();
		
		Stme stme = new Stme();
		String ctrl = "";
		
		int tota = 0;
		float time = 0;
		int tima = 0;
		int timi = 0;
		int nuer = 0;
		
		while (it.hasNext()) {
			Stdi stdi = it.next();
		
			if (ctrl.equals(stdi.getCtrl())) {
				time = ((time * tota) / (tota + stdi.getTota())) + ((stdi.getTime() * stdi.getTota()) / (tota + stdi.getTota()));
				tota = tota + stdi.getTota();
				
				if (stdi.getTima() > tima) {
					tima = stdi.getTima();
				}
				if (stdi.getTimi() < timi) {
					timi = stdi.getTimi();
				}
				
				
				nuer = nuer + stdi.getNuer();
				
			} else {
				
				if (!"".equals(ctrl)) {
					stme.setTota(tota);
					stme.setTima(tima);
					stme.setTimi(timi);
					stme.setTime(time);
					stme.setNuer(nuer);
				
					BsStmeSaveArea bsStmeSaveArea = new BsStmeSaveArea();
					bsStmeSaveArea.IN.stme = stme;
					bsStmeSave.executeBS(bsStmeSaveArea);
					
					logger.logProc("StdiAgrup", 2, "Ejecuciones: " + stme.getTota());
					logger.logProc("StdiAgrup", 2, "Tiempo medio: " + stme.getTime());
					logger.logProc("StdiAgrup", 2, "Tiempo mínimo: " + stme.getTimi());
					logger.logProc("StdiAgrup", 2, "Tiempo máximo: " + stme.getTima());
				}
				
				logger.logProc("StdiAgrup", 1, "Tratando estadísticas de Controlador : " + stdi.getCtrl());
				
				ctrl = stdi.getCtrl();
				stme = new Stme();
				stme.setAnyo(yyyy);
				stme.setMess(mm);
				stme.setCtrl(stdi.getCtrl());
				
				tota = stdi.getTota();
				tima = stdi.getTima();
				timi = stdi.getTimi();
				time = stdi.getTime();
				nuer = stdi.getNuer();
				
			}
			
		}
		
		if (!"".equals(ctrl)) {
			stme.setTota(tota);
			stme.setTima(tima);
			stme.setTimi(timi);
			stme.setTime(time);
			stme.setNuer(nuer);
		
			BsStmeSaveArea bsStmeSaveArea = new BsStmeSaveArea();
			bsStmeSaveArea.IN.stme = stme;
			bsStmeSave.executeBS(bsStmeSaveArea);
			
			logger.logProc("StdiAgrup", 2, "Ejecuciones: " + stme.getTota());
			logger.logProc("StdiAgrup", 2, "Tiempo medio: " + stme.getTime());
			logger.logProc("StdiAgrup", 2, "Tiempo mínimo: " + stme.getTimi());
			logger.logProc("StdiAgrup", 2, "Tiempo máximo: " + stme.getTima());
		}
	}
}
