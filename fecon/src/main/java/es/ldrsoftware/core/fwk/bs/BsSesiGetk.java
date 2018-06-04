package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.entity.Sesi;
import es.ldrsoftware.core.fwk.entity.SesiDAO;

@Component
public class BsSesiGetk extends BaseBS {

	@Autowired
	SesiDAO sesiDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsSesiGetkArea area = (BsSesiGetkArea)a;
		
		//Obtenemos la sesión por clave única de usuario
		Sesi sesi = sesiDao.getByIden(area.IN.iden);
				
		area.OUT.sesi = sesi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsSesiGetkArea area = (BsSesiGetkArea)a;
		
		validateIntRequired(area.IN.iden, CoreNotify.SESI_GETK_IDEN_RQRD);
	}

}
