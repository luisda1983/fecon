package es.ldrsoftware.core.spt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.spt.entity.Dele;

@Component
public class BsDeleEdit extends BaseBS {
	
	@Autowired
	BsDeleGetk bsDeleGetk;
	
	@Autowired
	BsDeleGetv bsDeleGetv;
	
	@Autowired
	BsDeleSave bsDeleSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDeleEditArea area = (BsDeleEditArea)a;

		//Recuperamos el dominio
		BsDeleGetkArea bsDeleGetkArea = new BsDeleGetkArea();
		bsDeleGetkArea.IN.iden = area.IN.iden;
		bsDeleGetk.executeBS(bsDeleGetkArea);
		
		Dele dele = bsDeleGetkArea.OUT.dele;
		
		validateDtoRequired(dele, CoreNotify.DELE_EDIT_DELE_NF);
		
		if (!dele.getDomi().equals(area.IN.domi)) {
			notify(CoreNotify.DELE_EDIT_DOMI_CHNG_NPER);
		}
		
		if (dele.getValo().equals(area.IN.valo)) {
			notify(CoreNotify.DELE_EDIT_CHNG_NO);
		}
		
		//Validamos que no existe un dominio con el mismo nombre
		BsDeleGetvArea bsDeleGetvArea = new BsDeleGetvArea();
		bsDeleGetvArea.IN.domi = area.IN.domi;
		bsDeleGetvArea.IN.valo = area.IN.valo;
		bsDeleGetv.executeBS(bsDeleGetvArea);
		
		Dele deleNomb = bsDeleGetvArea.OUT.dele;
		
		if (deleNomb != null) {
			if (dele.getIden() != deleNomb.getIden()) {
				notify(CoreNotify.DELE_EDIT_VALO_DP);
			}
		}
		
		dele.setValo(area.IN.valo);
		dele.setFemo(SESSION.get().feop);
		dele.setHomo(SESSION.get().hoop);
		dele.setUsmo(SESSION.get().usua);
		
		BsDeleSaveArea bsDeleSaveArea = new BsDeleSaveArea();
		bsDeleSaveArea.IN.dele = dele;
		bsDeleSave.executeBS(bsDeleSaveArea);
		
		dele = bsDeleSaveArea.OUT.dele;
		
		area.OUT.dele = dele;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDeleEditArea area = (BsDeleEditArea)a;
				
		validateIntRequired(area.IN.iden, CoreNotify.DELE_EDIT_IDEN_RQRD);
		validateStringRequired(area.IN.domi, CoreNotify.DELE_EDIT_DOMI_RQRD);
		validateStringRequired(area.IN.valo, CoreNotify.DELE_EDIT_VALO_RQRD);
	}
}