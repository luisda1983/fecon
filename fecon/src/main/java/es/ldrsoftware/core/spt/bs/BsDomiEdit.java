package es.ldrsoftware.core.spt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.spt.entity.Domi;

@Component
public class BsDomiEdit extends BaseBS {
	
	@Autowired
	BsDomiGetk bsDomiGetk;
	
	@Autowired
	BsDomiGetn bsDomiGetn;
	
	@Autowired
	BsDomiSave bsDomiSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDomiEditArea area = (BsDomiEditArea)a;

		//Recuperamos el dominio
		BsDomiGetkArea bsDomiGetkArea = new BsDomiGetkArea();
		bsDomiGetkArea.IN.iden = area.IN.iden;
		bsDomiGetk.executeBS(bsDomiGetkArea);
		
		Domi domi = bsDomiGetkArea.OUT.domi;
		
		validateDtoNotFound(domi, LiteData.LT_EL_DTO_DOMI, Domi.key(area.IN.iden));
		
		if (domi.getNomb().equals(area.IN.nomb) && domi.getDesc().equals(area.IN.desc)) {
			notify(CoreNotify.DOMI_EDIT_CHNG_NO);
		}
		
		//Validamos que no existe un dominio con el mismo nombre
		BsDomiGetnArea bsDomiGetnArea = new BsDomiGetnArea();
		bsDomiGetnArea.IN.nomb = area.IN.nomb;
		bsDomiGetn.executeBS(bsDomiGetnArea);
		
		Domi domiNomb = bsDomiGetnArea.OUT.domi;
		
		if (domiNomb != null) {
			if (domi.getIden() != domiNomb.getIden()) {
				notify(CoreNotify.DOMI_EDIT_NOMB_DP);
			}
		}
		
		domi.setNomb(area.IN.nomb);
		domi.setDesc(area.IN.desc);
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
		BsDomiEditArea area = (BsDomiEditArea)a;
				
		validateInputField(area.IN.iden, Domi.IDEN);
		validateInputField(area.IN.nomb, Domi.NOMB);
		validateInputField(area.IN.desc, Domi.DESC);
	}
}