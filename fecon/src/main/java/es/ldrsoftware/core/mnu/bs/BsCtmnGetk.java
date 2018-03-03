package es.ldrsoftware.core.mnu.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.mnu.entity.Ctmn;
import es.ldrsoftware.core.mnu.entity.CtmnDAO;

@Component
public class BsCtmnGetk extends BaseBS {

	@Autowired
	CtmnDAO ctmnDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCtmnGetkArea area = (BsCtmnGetkArea)a;

		//Obtenemos la categoría de menú
		Ctmn ctmn = ctmnDao.getByIden(area.IN.iden);
		
		area.OUT.ctmn = ctmn;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCtmnGetkArea area = (BsCtmnGetkArea)a;

		//Se valida que el identificador de categoría de menú esté informado
		validateIntRequired(area.IN.iden, CoreNotify.CTMN_GETK_IDEN_RQRD);
	}

}
