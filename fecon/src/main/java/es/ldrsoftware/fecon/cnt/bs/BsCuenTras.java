package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.cnt.entity.Hcon;

@Component
public class BsCuenTras extends BaseBS {

	@Autowired
	BsHconTras bsHconTras;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCuenTrasArea area = (BsCuenTrasArea)a;
		
		BsHconTrasArea bsHconTrasArea = new BsHconTrasArea();
		bsHconTrasArea.IN.ctor = area.IN.ctor;
		bsHconTrasArea.IN.ctde = area.IN.ctde;
		bsHconTrasArea.IN.feva = area.IN.feva;
		bsHconTrasArea.IN.impo = area.IN.impo;
		bsHconTras.execute(bsHconTrasArea);
		
		area.OUT.ctor = bsHconTrasArea.OUT.ctor;
		area.OUT.ctde = bsHconTrasArea.OUT.ctde;
		area.OUT.hcor = bsHconTrasArea.OUT.hcor;
		area.OUT.hcde = bsHconTrasArea.OUT.hcde;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCuenTrasArea area = (BsCuenTrasArea)a;
		
		validateInputField(area.IN.ctor, Cuen.CTOR);
		validateInputField(area.IN.ctde, Cuen.CTDE);
		validateInputField(area.IN.impo, Hcon.IMPO);
		
		if (!data(area.IN.feva)) {
			area.IN.feva = SESSION.get().feop;
		}
	}

}
