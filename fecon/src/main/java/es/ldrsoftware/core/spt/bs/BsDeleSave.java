package es.ldrsoftware.core.spt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.spt.entity.Dele;
import es.ldrsoftware.core.spt.entity.DeleDAO;

@Component
public class BsDeleSave extends BaseBS {
	
	@Autowired
	DeleDAO deleDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDeleSaveArea area = (BsDeleSaveArea)a;

		//Guardamos el elemento de dominio
		Dele dele = deleDao.save(area.IN.dele);
		
		area.OUT.dele = dele;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDeleSaveArea area = (BsDeleSaveArea)a;

		Dele dele = (Dele)validateDto(area.IN.dele, LiteData.LT_EL_DTO_DELE); 
		dele.validate();
	}
}