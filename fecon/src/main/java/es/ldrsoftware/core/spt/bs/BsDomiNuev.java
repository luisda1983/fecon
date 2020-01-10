package es.ldrsoftware.core.spt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.spt.entity.Domi;

@Component
public class BsDomiNuev extends BaseBS {
	
	@Autowired
	BsDomiGetn bsDomiGetn;
	
	@Autowired
	BsDomiSave bsDomiSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDomiNuevArea area = (BsDomiNuevArea)a;

		//Validamos que no existe un dominio con el mismo nombre
		BsDomiGetnArea bsDomiGetnArea = new BsDomiGetnArea();
		bsDomiGetnArea.IN.nomb = area.IN.nomb;
		bsDomiGetn.executeBS(bsDomiGetnArea);
		
		Domi domi = bsDomiGetnArea.OUT.domi;
		
		testData(domi, CoreNotify.DOMI_NUEV_NOMB_DP);
		
		domi = new Domi();
		domi.setInst(SESSION.get().inst);
		domi.setNomb(area.IN.nomb);
		domi.setDesc(area.IN.desc);
		domi.setFeal(SESSION.get().feop);
		domi.setHoal(SESSION.get().hoop);
		domi.setUsal(SESSION.get().usua);
		domi.setFemo(SESSION.get().feop);
		domi.setHomo(SESSION.get().hoop);
		domi.setUsmo(SESSION.get().usua);
		
		BsDomiSaveArea bsDomiSaveArea = new BsDomiSaveArea();
		bsDomiSaveArea.IN.domi = domi;
		bsDomiSave.executeBS(bsDomiSaveArea);
		
		domi = bsDomiSaveArea.OUT.domi;
		
		area.OUT.domi = domi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDomiNuevArea area = (BsDomiNuevArea)a;
				
		validateInputField(area.IN.nomb, Domi.NOMB);
		validateInputField(area.IN.desc, Domi.DESC);
	}
}