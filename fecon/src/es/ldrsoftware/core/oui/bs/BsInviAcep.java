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
	private BsInviSave bsInviSave;
	
	@Autowired
	private BsInviGet  bsInviGet;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInviAcepArea area = (BsInviAcepArea)a;
	
		//Consultamos la invitación
		BsInviGetArea bsInviGetArea = new BsInviGetArea();
		bsInviGetArea.IN.iden = area.IN.iden;
		bsInviGet.executeBS(bsInviGetArea);
		
		Invi invi = bsInviGetArea.OUT.invi;
		
		if (invi == null) {
			notify(CoreNotify.INVI_ACEP_NF);
		}
		
		if (!LiteData.LT_EL_INVIESTA_SOLICITADA.equals(invi.getEsta())) {
			notify(CoreNotify.INVI_ACEP_ESTA_ERRO);
		}
		
		invi.setEsta(LiteData.LT_EL_INVIESTA_ACEPTADA);
		invi.setFemo(SESSION.get().feop);
		invi.setHomo(SESSION.get().hoop);
		
		BsInviSaveArea bsInviSaveArea = new BsInviSaveArea();
		bsInviSaveArea.IN.invi = invi;
		bsInviSave.executeBS(bsInviSaveArea);
		
		area.OUT.invi = bsInviSaveArea.OUT.invi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviAcepArea area = (BsInviAcepArea)a;
		
		validateStringRequired(area.IN.iden, CoreNotify.INVI_ACEP_IDEN_RQRD);

	}
}