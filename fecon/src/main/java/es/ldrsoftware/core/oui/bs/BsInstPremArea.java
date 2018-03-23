package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Inst;

public class BsInstPremArea extends BaseBSArea {

	public BsInstPremAreaIn  IN  = new BsInstPremAreaIn();
	public BsInstPremAreaOut OUT = new BsInstPremAreaOut();
	
	public class BsInstPremAreaIn {
		public long iden;
	}
	
	public class BsInstPremAreaOut {
		public Inst inst;
	}
}
