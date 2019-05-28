package es.ldrsoftware.core.spt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.spt.entity.Dele;

@Component
public class BsDeleNuev extends BaseBS {
	
	@Autowired
	BsDeleGetv bsDeleGetv;
	
	@Autowired
	BsDeleSave bsDeleSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDeleNuevArea area = (BsDeleNuevArea)a;

		//Validamos que no existe un elemento de dominio con el mismo nombre en el mismo dominio
		BsDeleGetvArea bsDeleGetvArea = new BsDeleGetvArea();
		bsDeleGetvArea.IN.domi = area.IN.domi;
		bsDeleGetvArea.IN.valo = area.IN.valo;
		bsDeleGetv.executeBS(bsDeleGetvArea);
		
		Dele dele = bsDeleGetvArea.OUT.dele;
		
		validateDtoEmpty(dele, CoreNotify.DELE_NUEV_VALO_DP);
		
		dele = new Dele();
		dele.setInst(SESSION.get().inst);
		dele.setDomi(area.IN.domi);
		dele.setValo(area.IN.valo);
		dele.setFeal(SESSION.get().feop);
		dele.setHoal(SESSION.get().hoop);
		dele.setUsal(SESSION.get().usua);
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
		BsDeleNuevArea area = (BsDeleNuevArea)a;
		
		validateStringRequired(area.IN.domi, CoreNotify.DELE_NUEV_DOMI_RQRD);
		validateStringRequired(area.IN.valo, CoreNotify.DELE_NUEV_VALO_RQRD);
	}
}