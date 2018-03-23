package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Inst;

public class BsInstNormArea extends BaseBSArea {

	public BsInstNormAreaIn  IN  = new BsInstNormAreaIn();
	public BsInstNormAreaOut OUT = new BsInstNormAreaOut();
	
	public class BsInstNormAreaIn {
		public long iden;
	}
	
	public class BsInstNormAreaOut {
		public Inst inst;
	}
}
