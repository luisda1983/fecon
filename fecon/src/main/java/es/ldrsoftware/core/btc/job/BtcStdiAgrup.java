package es.ldrsoftware.core.btc.job;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.Btc;
import es.ldrsoftware.core.sts.entity.Stdi;
import es.ldrsoftware.core.sts.entity.StdiDao;
import es.ldrsoftware.core.sts.entity.Stme;
import es.ldrsoftware.core.sts.entity.StmeDao;

@Component
public class BtcStdiAgrup extends Btc {

	@Autowired
	public StdiDao stdiDao;
	
	@Autowired
	public StmeDao stmeDao;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void executeBtc(int fech) {

		int yyyymm = fech / 100;
		int yyyy = yyyymm / 100;
		int mm = yyyymm - yyyy * 100;
		
		int dd = fech - yyyymm * 100;
		
		int fein = fech - dd;
		int fefi = fein + 99;
		
		List<Stdi> stdiList = stdiDao.getListBetweenFech(fein, fefi);
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
				
					stmeDao.save(stme);
				}
				
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
		
			stmeDao.save(stme);
		}
	}
}
