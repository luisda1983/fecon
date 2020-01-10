package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Invi;
import es.ldrsoftware.core.oui.entity.InviDAO;

@Component
public class BsInviGetk extends BaseBS {
	
	@Autowired
	InviDAO inviDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInviGetkArea area = (BsInviGetkArea)a;
	
		//Consultamos la invitación
		Invi invi = inviDao.getByIden(area.IN.iden);
		
		area.OUT.invi = invi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviGetkArea area = (BsInviGetkArea)a;
		
		//Validamos que el identificador de invitación esté informado
		validateInputField(area.IN.iden, Invi.IDEN);

	}
}