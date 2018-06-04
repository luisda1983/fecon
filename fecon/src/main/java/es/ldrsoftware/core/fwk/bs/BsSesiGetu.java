package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.entity.Sesi;
import es.ldrsoftware.core.fwk.entity.SesiDAO;

@Component
public class BsSesiGetu extends BaseBS {

	@Autowired
	SesiDAO sesiDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsSesiGetuArea area = (BsSesiGetuArea)a;
		
		Sesi sesi = sesiDao.getByUsua(area.IN.usua);
		
		area.OUT.sesi = sesi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsSesiGetuArea area = (BsSesiGetuArea)a;
		
		validateStringRequired(area.IN.usua, CoreNotify.SESI_GETU_USUA_RQRD);
	}

}
