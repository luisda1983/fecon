package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Inst;

public class BsInstInacArea extends BaseBSArea {

	public BsInstInacAreaIn  IN  = new BsInstInacAreaIn();
	public BsInstInacAreaOut OUT = new BsInstInacAreaOut();
	
	public class BsInstInacAreaIn {
		public long iden;
	}
	
	public class BsInstInacAreaOut {
		public Inst inst;
	}
}
