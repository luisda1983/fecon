package es.ldrsoftware.core.spt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Domi;

@Component
public class BsDomiForm extends BaseBS {
	
	@Autowired
	BsDomiNuev bsDomiNuev;
	
	@Autowired
	BsDomiEdit bsDomiEdit;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDomiFormArea area = (BsDomiFormArea)a;

		Domi domi;
		
		if (area.IN.iden == 0) {
			BsDomiNuevArea bsDomiNuevArea = new BsDomiNuevArea();
			bsDomiNuevArea.IN.nomb = area.IN.nomb;
			bsDomiNuevArea.IN.desc = area.IN.desc;
			bsDomiNuev.executeBS(bsDomiNuevArea);
			
			domi = bsDomiNuevArea.OUT.domi;
		} else {
			BsDomiEditArea bsDomiEditArea = new BsDomiEditArea();
			bsDomiEditArea.IN.iden = area.IN.iden;
			bsDomiEditArea.IN.nomb = area.IN.nomb;
			bsDomiEditArea.IN.desc = area.IN.desc;
			bsDomiEdit.executeBS(bsDomiEditArea);
			
			domi = bsDomiEditArea.OUT.domi;
		}
		
		area.OUT.domi = domi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsDomiFormArea area = (BsDomiFormArea)a;
	}
}