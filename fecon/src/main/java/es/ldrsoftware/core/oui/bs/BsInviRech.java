package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Invi;

@Component
public class BsInviRech extends BaseBS {

	@Autowired
	BsInviSave bsInviSave;
	
	@Autowired
	BsInviGetk  bsInviGet;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInviRechArea area = (BsInviRechArea)a;
	
		//Consultamos la invitación
		BsInviGetkArea bsInviGetArea = new BsInviGetkArea();
		bsInviGetArea.IN.iden = area.IN.iden;
		bsInviGet.executeBS(bsInviGetArea);
		
		Invi invi = bsInviGetArea.OUT.invi;
		
		validateDtoNotFound(invi, LiteData.LT_EL_DTO_INVI, Invi.key(area.IN.iden));
		
		//La invitación debe estar en estado solicitado para poder ser rechazada
		if (!LiteData.LT_EL_INVIESTA_SOLICITADA.equals(invi.getEsta())) {
			notify(CoreNotify.INVI_RECH_ESTA_ERRO);
		}
		
		//Rechazamos la invitación
		invi.setEsta(LiteData.LT_EL_INVIESTA_RECHAZADA);
		invi.setFemo(SESSION.get().feop);
		invi.setHomo(SESSION.get().hoop);
		
		//Guardamos la invitación
		BsInviSaveArea bsInviSaveArea = new BsInviSaveArea();
		bsInviSaveArea.IN.invi = invi;
		bsInviSave.executeBS(bsInviSaveArea);
		
		area.OUT.invi = bsInviSaveArea.OUT.invi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviRechArea area = (BsInviRechArea)a;

		//Validamos que el identificador de invitación esté informado
		validateInputField(area.IN.iden, Invi.IDEN);

	}
}