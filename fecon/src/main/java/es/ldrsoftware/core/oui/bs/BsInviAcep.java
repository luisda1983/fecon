package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Invi;

@Component
public class BsInviAcep extends BaseBS {

	@Autowired
	BsInviSave bsInviSave;
	
	@Autowired
	BsInviGetk bsInviGetk;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInviAcepArea area = (BsInviAcepArea)a;
	
		//Consultamos la invitación
		BsInviGetkArea bsInviGetkArea = new BsInviGetkArea();
		bsInviGetkArea.IN.iden = area.IN.iden;
		bsInviGetk.executeBS(bsInviGetkArea);
		
		Invi invi = bsInviGetkArea.OUT.invi;
		
		validateDtoNotFound(invi, LiteData.LT_EL_DTO_INVI, Invi.key(area.IN.iden));
		
		//Si el estado de la invitación no es solicitada, no se puede aceptar
		if (!LiteData.LT_EL_INVIESTA_SOLICITADA.equals(invi.getEsta())) {
			notify(CoreNotify.INVI_ACEP_ESTA_ERRO);
		}
		
		//Pasamos la invitación a estado aceptada
		invi.setEsta(LiteData.LT_EL_INVIESTA_ACEPTADA);
		invi.setFemo(SESSION.get().feop);
		invi.setHomo(SESSION.get().hoop);
		
		//Guardamos la invitación
		BsInviSaveArea bsInviSaveArea = new BsInviSaveArea();
		bsInviSaveArea.IN.invi = invi;
		bsInviSave.executeBS(bsInviSaveArea);
		
		area.OUT.invi = bsInviSaveArea.OUT.invi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviAcepArea area = (BsInviAcepArea)a;
		
		//Validamos que el identificador de invitación está informado
		validateInputField(area.IN.iden, Invi.IDEN);
	}
}