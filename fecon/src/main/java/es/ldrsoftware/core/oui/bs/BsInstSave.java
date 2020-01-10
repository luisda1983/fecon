package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.InstDAO;

@Component
public class BsInstSave extends BaseBS {
	
	@Autowired
	InstDAO instDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInstSaveArea area = (BsInstSaveArea)a;

		//Guardamos la instalación
		Inst inst = instDao.save(area.IN.inst);
		
		area.OUT.inst = inst;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInstSaveArea area = (BsInstSaveArea)a;

		//Validamos que la instalación esté informada
		Inst inst = (Inst)validateDto(area.IN.inst, LiteData.LT_EL_DTO_INST); 
		inst.validate();
		
	}
}