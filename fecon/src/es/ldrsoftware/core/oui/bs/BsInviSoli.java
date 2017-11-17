package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.core.oui.entity.Invi;

@Component
public class BsInviSoli extends BaseBS {

	@Autowired
	private BsInviSave bsInviSave;
	
	@Autowired
	private BsInviList bsInviList;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInviSoliArea area = (BsInviSoliArea)a;
	
		//Verificamos que no existe ninguna invitación solicitada con el mismo email.
		BsInviListArea bsInviListArea = new BsInviListArea();
		bsInviListArea.IN.tipo = "I";
		bsInviListArea.IN.esta = "S";
		bsInviListArea.IN.mail = area.IN.mail;
		bsInviList.executeBS(bsInviListArea);
		
		if (bsInviListArea.OUT.inviList != null && bsInviListArea.OUT.inviList.size() > 0) {
			notify(CoreNotify.INVI_LIST_SOLI_PEND);
		}
		
		//TODO: se debería verificar que tampoco hay una solicitud aceptada o enviada...
		//TODO: se podría también controlar unos limites de solicitudes rechazadas en un plazo de tiempo
		
		Invi invi = new Invi();
		invi.setIden("INV" + DateTimeUtil.getNope());
		invi.setTipo("I");
		invi.setEsta("S");
		invi.setMail(area.IN.mail);
		invi.setInst(0);
		invi.setUsua("");
		invi.setFeal(SESSION.get().feop);
		invi.setHoal(SESSION.get().hoop);
		invi.setFemo(0);
		invi.setHomo(0);
		
		BsInviSaveArea bsInviSaveArea = new BsInviSaveArea();
		bsInviSaveArea.IN.invi = invi;
		bsInviSave.executeBS(bsInviSaveArea);
		
		area.OUT.invi = bsInviSaveArea.OUT.invi;
		
		notify(CoreNotify.INVI_SOLI_OK);
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviSoliArea area = (BsInviSoliArea)a;
		
		validateStringRequired(area.IN.mail, CoreNotify.INVI_SOLI_MAIL_RQRD);

	}
}