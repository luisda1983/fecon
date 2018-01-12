package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.prp.entity.Conc;
import es.ldrsoftware.fecon.prp.entity.ConcDAO;

@Component
public class BsConcSave extends BaseBS {
	
	@Autowired
	public ConcDAO concDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsConcSaveArea area = (BsConcSaveArea)a;

		Conc conc = area.IN.conc;
		
		//TODO: validaciones
		
		conc = concDao.save(conc);
		area.OUT.conc = conc;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsConcSaveArea area = (BsConcSaveArea)a;
		
		validateDto(area.IN.conc, AppNotify.CONC_SAVE_CONC_RQRD);
	}
}