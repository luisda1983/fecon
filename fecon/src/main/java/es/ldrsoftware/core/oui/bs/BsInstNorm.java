package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Inst;

@Component
public class BsInstNorm extends BaseBS {

	@Autowired
	BsInstGetk bsInstGetk;
	
	@Autowired
	BsInstSave bsInstSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInstNormArea area = (BsInstNormArea)a;

		//Obtenemos la instalación
		BsInstGetkArea bsInstGetkArea = new BsInstGetkArea();
		bsInstGetkArea.IN.iden = area.IN.iden;
		bsInstGetk.executeBS(bsInstGetkArea);
		
		Inst inst = bsInstGetkArea.OUT.inst;
		
		//Validamos que exista la instalación
		validateDtoNotFound(inst, LiteData.LT_EL_DTO_INST, Inst.key(area.IN.iden));
		
		//Validamos que la instalación sea de tipo Premium
		test(false, inst.getTipo(), LiteData.LT_EL_INSTTIPO_PREMIUM, CoreNotify.INST_NORM_INST_PREM_NO);

		//Establecemos la instalación cómo Normal
		inst.setTipo(LiteData.LT_EL_INSTTIPO_NORMAL);
		//Limpiamos la fecha de caducidad Premium
		inst.setFeca(0);
		
		//Guardamos la instalación
		BsInstSaveArea bsInstSaveArea = new BsInstSaveArea();
		bsInstSaveArea.IN.inst = inst;
		bsInstSave.executeBS(bsInstSaveArea);

		area.OUT.inst = bsInstSaveArea.OUT.inst;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInstNormArea area = (BsInstNormArea)a;

		//Se valida que el identificador de instalación esté informado
		validateInputField(area.IN.iden, Inst.IDEN);
	}

}
