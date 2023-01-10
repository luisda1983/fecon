package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Invi;
import es.ldrsoftware.core.oui.entity.InviDAO;

@Component
public class BsInviSave extends BaseBS {

	@Autowired
	InviDAO inviDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInviSaveArea area = (BsInviSaveArea)a;
	
		//Guardamos la invitación
		Invi invi = inviDao.save(area.IN.invi);
		
		area.OUT.invi = invi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviSaveArea area = (BsInviSaveArea)a;
		
		//Validamos que la invitación está informada
		Invi invi = (Invi)validateDto(area.IN.invi, LiteData.LT_EL_DTO_INVI); 
	}
}