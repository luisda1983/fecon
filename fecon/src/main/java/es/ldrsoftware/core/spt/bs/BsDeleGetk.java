package es.ldrsoftware.core.spt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.spt.entity.Dele;
import es.ldrsoftware.core.spt.entity.DeleDAO;

@Component
public class BsDeleGetk extends BaseBS {
	
	@Autowired
	DeleDAO deleDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDeleGetkArea area = (BsDeleGetkArea)a;

		//Obtenemos el elemento dominio
		Dele dele = deleDao.getByIden(SESSION.get().inst, area.IN.iden);
		
		area.OUT.dele = dele;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDeleGetkArea area = (BsDeleGetkArea)a;
		
		validateIntRequired(area.IN.iden, CoreNotify.DELE_GETK_IDEN_RQRD);
	}
}