package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.oui.entity.Invi;
import es.ldrsoftware.core.oui.entity.InviDAO;

@Component
public class BsInviGetk extends BaseBS {
	
	@Autowired
	public InviDAO inviDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInviGetkArea area = (BsInviGetkArea)a;
	
		Invi invi = inviDao.getByIden(area.IN.iden);
		
		area.OUT.invi = invi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviGetkArea area = (BsInviGetkArea)a;
		
		validateStringRequired(area.IN.iden, CoreNotify.INVI_GETI_IDEN_RQRD);

	}
}