//Normalizado
package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.InstDAO;

@Component
public class BsInstGetd extends BaseBS {
	
	@Autowired
	InstDAO instDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInstGetdArea area = (BsInstGetdArea)a;
		
		//Obtenemos la instalación
		Inst inst = instDao.getByDesc(area.IN.desc);
		
		area.OUT.inst = inst;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInstGetdArea area = (BsInstGetdArea)a;
		
		//Validamos que la descripción de instalación esté informada
		validateInputField(area.IN.desc, Inst.DESC);
		
	}
}