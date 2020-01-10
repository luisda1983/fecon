package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Conc;
import es.ldrsoftware.fecon.prp.entity.ConcDAO;

@Component
public class BsConcGetk extends BaseBS {

	@Autowired
	ConcDAO concDao;;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsConcGetkArea area = (BsConcGetkArea)a;
		
		Conc conc = concDao.getByIden(SESSION.get().inst, area.IN.iden);
		
		area.OUT.conc = conc;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsConcGetkArea area = (BsConcGetkArea)a;
		
		validateInputField(area.IN.iden, Conc.IDEN);
	}

}
