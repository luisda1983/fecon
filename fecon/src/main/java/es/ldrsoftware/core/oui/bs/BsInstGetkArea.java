package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Inst;

public class BsInstGetkArea extends BaseBSArea {

	public BsInstGetkAreaIn  IN  = new BsInstGetkAreaIn();
	public BsInstGetkAreaOut OUT = new BsInstGetkAreaOut();
	
	public class BsInstGetkAreaIn {
		public long iden;
	}
	
	public class BsInstGetkAreaOut {
		public Inst inst;
	}
}
