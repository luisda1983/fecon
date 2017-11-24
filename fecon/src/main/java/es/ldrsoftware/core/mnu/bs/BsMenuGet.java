package es.ldrsoftware.core.mnu.bs;

import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.mnu.entity.Ctmn;
import es.ldrsoftware.core.mnu.entity.CtmnDAO;
import es.ldrsoftware.core.mnu.entity.Dtmn;
import es.ldrsoftware.core.mnu.entity.DtmnDAO;

@Component
public class BsMenuGet extends BaseBS {

	@Autowired
	CtmnDAO ctmnDao;
	
	@Autowired
	DtmnDAO dtmnDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsMenuGetArea area = (BsMenuGetArea)a;
		
		List<Ctmn> ctmnList = ctmnDao.getListByPerf(area.IN.perf);
		ListIterator<Ctmn> ctmnIt = ctmnList.listIterator();
		
		while (ctmnIt.hasNext()) {
			Ctmn ctmn = ctmnIt.next();
			List<Dtmn> dtmnList = dtmnDao.getListByCtmn(ctmn.getIden());
			ctmn.dtmnList = dtmnList;
		}
		
		area.OUT.ctmnList = ctmnList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsMenuGetArea area = (BsMenuGetArea)a;
		
		if (area.IN.perf == null || "".equals(area.IN.perf)) { notify(CoreNotify.MENU_PERF_RQRD); }
		
	}

}
