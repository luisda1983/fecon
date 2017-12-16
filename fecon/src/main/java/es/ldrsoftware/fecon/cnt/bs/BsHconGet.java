package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Hcon;
import es.ldrsoftware.fecon.cnt.entity.HconDAO;
import es.ldrsoftware.fecon.data.AppNotify;

@Component
public class BsHconGet extends BaseBS {
	
	@Autowired
	public HconDAO hconDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsHconGetArea area = (BsHconGetArea)a;
		
		Hcon hcon = hconDao.getByIden(SESSION.get().inst, area.IN.iden);
		
		area.OUT.hcon = hcon;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsHconGetArea area = (BsHconGetArea)a;
		
		validateIntRequired(area.IN.iden, AppNotify.HCON_GETH_IDEN_RQRD);
	}
}