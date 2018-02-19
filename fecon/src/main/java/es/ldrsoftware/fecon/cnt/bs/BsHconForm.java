package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;

@Component
public class BsHconForm extends BaseBS {
	
	@Autowired
	public BsHconApun bsHconApun;
	
	@Autowired
	public BsHconModi bsHconModi;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsHconFormArea area = (BsHconFormArea)a;

		if (area.IN.iden == 0) {
			BsHconApunArea bsHconApunArea = new BsHconApunArea();
			bsHconApunArea.IN.cate = area.IN.cate;
			bsHconApunArea.IN.conc = area.IN.conc;
			bsHconApunArea.IN.cuen = area.IN.cuen;
			bsHconApunArea.IN.impo = area.IN.impo;
			bsHconApunArea.IN.feva = area.IN.feva;
			bsHconApunArea.IN.desc = area.IN.desc;
			bsHconApun.executeBS(bsHconApunArea);
			
			area.OUT.hcon = bsHconApunArea.OUT.hcon;
		} else {
			BsHconModiArea bsHconModiArea = new BsHconModiArea();
			bsHconModiArea.IN.tipo = area.IN.tipo;
			bsHconModiArea.IN.iden = area.IN.iden;
			bsHconModiArea.IN.cate = area.IN.cate;
			bsHconModiArea.IN.conc = area.IN.conc;
			bsHconModiArea.IN.cuen = area.IN.cuen;
			bsHconModiArea.IN.impo = area.IN.impo;
			bsHconModiArea.IN.feva = area.IN.feva;
			bsHconModiArea.IN.desc = area.IN.desc;
			bsHconModi.executeBS(bsHconModiArea);
			
			area.OUT.hcon = bsHconModiArea.OUT.hcon;
		}
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsHconFormArea area = (BsHconFormArea)a;
	}
}