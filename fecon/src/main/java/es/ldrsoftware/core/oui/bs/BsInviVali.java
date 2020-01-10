package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Invi;

@Component
public class BsInviVali extends BaseBS {
	
	@Autowired
	BsInviGetk bsInviGetk;
	
	@Autowired
	BsUsuaGetm bsUsuaGetm;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInviValiArea area = (BsInviValiArea)a;
	
		//Obtenemos la invitación
		BsInviGetkArea bsInviGetkArea = new BsInviGetkArea();
		bsInviGetkArea.IN.iden = area.IN.iden;
		bsInviGetk.executeBS(bsInviGetkArea);
		
		Invi invi = bsInviGetkArea.OUT.invi;
		
		validateDtoNotFound(invi, LiteData.LT_EL_DTO_INVI, Invi.key(area.IN.iden));
		
		//La invitación debe estar aceptada o enviada para poder ser procesada
		if (!LiteData.LT_EL_INVIESTA_ACEPTADA.equals(invi.getEsta()) &&
			!LiteData.LT_EL_INVIESTA_ENVIADA.equals(invi.getEsta())) {
			notify(CoreNotify.INVI_VALI_ESTA_ERRO);
		}

		//Buscamos si el email ya está registrado para indicar si la invitación será de un nuevo usuario o de uno ya existente.
		BsUsuaGetmArea bsUsuaGetmArea = new BsUsuaGetmArea();
		bsUsuaGetmArea.IN.mail = invi.getMail();
		bsUsuaGetm.executeBS(bsUsuaGetmArea);
		
		if (bsUsuaGetmArea.OUT.usua != null) {
			area.OUT.exus = true;
		} else {
			area.OUT.exus = false;
		}
		area.OUT.invi = invi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviValiArea area = (BsInviValiArea)a;

		//Validamos que el identificador de la invitación esté informado
		validateInputField(area.IN.iden, Invi.IDEN);

	}
}