package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.prp.entity.Conc;
import es.ldrsoftware.fecon.prp.entity.ConcDAO;

@Component
public class BsConcGetd extends BaseBS {

	@Autowired
	ConcDAO concDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsConcGetdArea area = (BsConcGetdArea)a;
		
		Conc conc;
		conc = concDao.getByCateDesc(SESSION.get().inst, area.IN.cate, area.IN.desc);
		area.OUT.concDesc = conc;
		
		conc = concDao.getByCateDesl(SESSION.get().inst, area.IN.cate, area.IN.desc);
		area.OUT.concDesl = conc;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsConcGetdArea area = (BsConcGetdArea)a;
		
		validateIntRequired(area.IN.cate, AppNotify.CONC_GETD_CATE_RQRD);
		validateStringRequired(area.IN.desl, AppNotify.CONC_GETD_DESL_RQRD);
		validateStringRequired(area.IN.desc, AppNotify.CONC_GETD_DESC_RQRD);
	}

}
