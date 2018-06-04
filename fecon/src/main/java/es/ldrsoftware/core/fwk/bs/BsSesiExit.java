package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.entity.Sesi;

@Component
public class BsSesiExit extends BaseBS {

	@Autowired
	BsSesiGetk bsSesiGetk;
	
	@Autowired
	BsSesiSave bsSesiSave;

	protected void execute(BaseBSArea a) throws Exception {
		BsSesiExitArea area = (BsSesiExitArea)a;
		
		//Obtenemos la sesión
		BsSesiGetkArea bsSesiGetkArea = new BsSesiGetkArea();
		bsSesiGetkArea.IN.iden = area.IN.iden;
		bsSesiGetk.executeBS(bsSesiGetkArea);
		
		Sesi sesi = bsSesiGetkArea.OUT.sesi;
		
		if (sesi == null) {
			notify(CoreNotify.SESI_EXIT_SESI_NF);
		}
		
		//Validamos que la sesión está abierta
		if (LiteData.LT_EL_SESIESTA_ABIERTA.equals(sesi.getEsta())) {
			sesi.setEsta(LiteData.LT_EL_SESIESTA_CERRADA);
		}
		
		sesi.setClav(0);
		sesi.setFeul(SESSION.get().feop);
		sesi.setHoul(SESSION.get().hoop);
		sesi.setFeca(0);
		sesi.setHoca(0);
		sesi.setFere(0);
		sesi.setHore(0);
		
		BsSesiSaveArea bsSesiSaveArea = new BsSesiSaveArea();
		bsSesiSaveArea.IN.sesi = sesi;
		bsSesiSave.executeBS(bsSesiSaveArea);
		
		area.OUT.sesi = bsSesiSaveArea.OUT.sesi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsSesiExitArea area = (BsSesiExitArea)a;

		validateIntRequired(area.IN.iden, CoreNotify.SESI_EXIT_IDEN_RQRD);
	}
}
