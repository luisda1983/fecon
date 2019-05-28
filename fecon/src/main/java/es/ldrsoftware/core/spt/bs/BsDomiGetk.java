package es.ldrsoftware.core.spt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.spt.entity.Domi;
import es.ldrsoftware.core.spt.entity.DomiDAO;

@Component
public class BsDomiGetk extends BaseBS {
	
	@Autowired
	DomiDAO domiDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDomiGetkArea area = (BsDomiGetkArea)a;

		//Obtenemos el dominio
		Domi domi = domiDao.getByIden(SESSION.get().inst, area.IN.iden);
		
		area.OUT.domi = domi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDomiGetkArea area = (BsDomiGetkArea)a;
		
		validateIntRequired(area.IN.iden, CoreNotify.DOMI_GETK_IDEN_RQRD);
	}
}