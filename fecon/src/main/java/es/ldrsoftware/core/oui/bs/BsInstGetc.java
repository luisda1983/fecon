//Normalizado
package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.InstDAO;

@Component
public class BsInstGetc extends BaseBS {
	
	@Autowired
	InstDAO instDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInstGetcArea area = (BsInstGetcArea)a;
		
		//Obtenemos la instalación
		Inst inst = instDao.getByCodi(area.IN.codi);
		
		area.OUT.inst = inst;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInstGetcArea area = (BsInstGetcArea)a;
		
		//Validamos que el código de instalación esté informado
		validateStringRequired(area.IN.codi, CoreNotify.INST_GETC_CODI_RQRD);
		
	}
}