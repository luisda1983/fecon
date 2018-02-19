package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Inst;

public class BsInstGetkArea extends BaseBSArea {

	public BsInstGetAreaIn  IN  = new BsInstGetAreaIn();
	public BsInstGetAreaOut OUT = new BsInstGetAreaOut();
	
	public class BsInstGetAreaIn {
		public long iden;
	}
	
	public class BsInstGetAreaOut {
		public Inst inst;
	}
}
