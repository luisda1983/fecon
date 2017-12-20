package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.prp.entity.Pres;
import es.ldrsoftware.fecon.prp.entity.PresDAO;

@Component
public class BsPresGet extends BaseBS {
	
	@Autowired
	public PresDAO presDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsPresGetArea area = (BsPresGetArea)a;

		Pres pres = presDao.getByAnuaMespCateConc(SESSION.get().inst, area.IN.anua, area.IN.mesp, area.IN.cate, area.IN.conc);

		area.OUT.pres = pres;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsPresGetArea area = (BsPresGetArea)a;
		
		validateIntRequired(area.IN.anua, AppNotify.PRES_GETP_ANUA_RQRD);
		validateIntRequired(area.IN.cate, AppNotify.PRES_GETP_CATE_RQRD);
	}
}