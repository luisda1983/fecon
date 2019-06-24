package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.entity.Notf;
import es.ldrsoftware.core.fwk.entity.NotfDAO;

@Component
public class BsNotfGetk extends BaseBS {

	@Autowired
	public NotfDAO notfDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsNotfGetkArea area = (BsNotfGetkArea)a;
	
		Notf notf = notfDao.getByIden(area.IN.iden);
		
		area.OUT.notf = notf;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsNotfGetkArea area = (BsNotfGetkArea)a;
		
		validateStringRequired(area.IN.iden, CoreNotify.NOTF_GETK_IDEN_RQRD);		
	}
}