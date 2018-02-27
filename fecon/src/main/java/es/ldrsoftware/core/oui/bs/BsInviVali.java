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
	private BsInviGetk bsInviGet;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInviValiArea area = (BsInviValiArea)a;
	
		//Obtenemos la invitación
		BsInviGetkArea bsInviGetArea = new BsInviGetkArea();
		bsInviGetArea.IN.iden = area.IN.iden;
		bsInviGet.executeBS(bsInviGetArea);
		
		Invi invi = bsInviGetArea.OUT.invi;
		
		validateDto(invi, CoreNotify.INVI_VALI_NF);
		
		if (!LiteData.LT_EL_INVIESTA_ACEPTADA.equals(invi.getEsta()) &&
			!LiteData.LT_EL_INVIESTA_ENVIADA.equals(invi.getEsta())) {
			notify(CoreNotify.INVI_VALI_ESTA_ERRO);
		}
		
		area.OUT.invi = invi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviValiArea area = (BsInviValiArea)a;
		
		validateStringRequired(area.IN.iden, CoreNotify.INVI_VALI_IDEN_RQRD);

	}
}