package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Inst;

@Component
public class BsInstActi extends BaseBS {

	@Autowired
	BsInstGetk bsInstGetk;
	
	@Autowired
	BsInstSave bsInstSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInstActiArea area = (BsInstActiArea)a;

		//Obtenemos la instalación
		BsInstGetkArea bsInstGetkArea = new BsInstGetkArea();
		bsInstGetkArea.IN.iden = area.IN.iden;
		bsInstGetk.executeBS(bsInstGetkArea);
		
		Inst inst = bsInstGetkArea.OUT.inst;
		
		//Validamos que exista la instalación
		validateDtoNotFound(inst, LiteData.LT_EL_DTO_INST, Inst.key(area.IN.iden));
		
		//Validamos que la instalación se encuentre inactiva
		test(false, inst.getEsta(), LiteData.LT_EL_INSTESTA_INACTIVA, CoreNotify.INST_ACTI_INST_INAC_NO);
		
		//Activamos la instalación
		inst.setEsta(LiteData.LT_EL_INSTESTA_ACTIVA);
		
		//Guardamos la instalación
		BsInstSaveArea bsInstSaveArea = new BsInstSaveArea();
		bsInstSaveArea.IN.inst = inst;
		bsInstSave.executeBS(bsInstSaveArea);
				
		area.OUT.inst = bsInstSaveArea.OUT.inst;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInstActiArea area = (BsInstActiArea)a;

		//Se valida que el identificador de instalación esté informado
		validateInputField(area.IN.iden, Inst.IDEN);
	}

}
