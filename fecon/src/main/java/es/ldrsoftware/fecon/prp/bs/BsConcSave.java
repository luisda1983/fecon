package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.data.LiteData;
import es.ldrsoftware.fecon.prp.entity.Conc;
import es.ldrsoftware.fecon.prp.entity.ConcDAO;

@Component
public class BsConcSave extends BaseBS {
	
	@Autowired
	public ConcDAO concDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsConcSaveArea area = (BsConcSaveArea)a;

		Conc conc = area.IN.conc;
		
		conc = concDao.save(conc);
		
		area.OUT.conc = conc;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsConcSaveArea area = (BsConcSaveArea)a;
		
		Conc conc = (Conc)validateDto(area.IN.conc, LiteData.LT_EL_DTO_CONC); 
	}
}