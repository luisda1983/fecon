package es.ldrsoftware.core.spt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Dele;

@Component
public class BsDeleForm extends BaseBS {
	
	@Autowired
	BsDeleNuev bsDeleNuev;
	
	@Autowired
	BsDeleEdit bsDeleEdit;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDeleFormArea area = (BsDeleFormArea)a;

		Dele dele;
		
		if (area.IN.iden == 0) {
			BsDeleNuevArea bsDeleNuevArea = new BsDeleNuevArea();
			bsDeleNuevArea.IN.domi = area.IN.domi;
			bsDeleNuevArea.IN.valo = area.IN.valo;
			bsDeleNuev.executeBS(bsDeleNuevArea);
			
			dele = bsDeleNuevArea.OUT.dele;
		} else {
			BsDeleEditArea bsDeleEditArea = new BsDeleEditArea();
			bsDeleEditArea.IN.iden = area.IN.iden;
			bsDeleEditArea.IN.domi = area.IN.domi;
			bsDeleEditArea.IN.valo = area.IN.valo;
			bsDeleEdit.executeBS(bsDeleEditArea);
			
			dele = bsDeleEditArea.OUT.dele;
		}
		
		area.OUT.dele = dele;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsDeleFormArea area = (BsDeleFormArea)a;
	}
}