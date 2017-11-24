package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.cnt.entity.CuenDAO;
import es.ldrsoftware.fecon.data.AppNotify;

@Component
public class BsCuenGet extends BaseBS {
	
	@Autowired
	public CuenDAO cuenDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCuenGetArea area = (BsCuenGetArea)a;
		
		Cuen cuen = cuenDao.getByIden(SESSION.get().inst, area.IN.iden);
		
		area.OUT.cuen = cuen;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCuenGetArea area = (BsCuenGetArea)a;
		
		validateIntRequired(area.IN.iden, AppNotify.CUEN_GETC_IDEN_RQRD);
	}
}