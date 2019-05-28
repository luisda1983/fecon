package es.ldrsoftware.core.spt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.spt.entity.Domi;
import es.ldrsoftware.core.spt.entity.DomiDAO;

@Component
public class BsDomiGetn extends BaseBS {
	
	@Autowired
	DomiDAO domiDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDomiGetnArea area = (BsDomiGetnArea)a;

		//Obtenemos el dominio
		Domi domi = domiDao.getByNomb(SESSION.get().inst, area.IN.nomb);
		
		area.OUT.domi = domi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDomiGetnArea area = (BsDomiGetnArea)a;
		
		validateStringRequired(area.IN.nomb, CoreNotify.DOMI_GETN_NOMB_RQRD);
	}
}