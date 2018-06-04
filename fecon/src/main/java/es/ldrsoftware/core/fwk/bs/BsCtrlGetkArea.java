package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Ctrl;

public class BsCtrlGetkArea extends BaseBSArea {

	public BsCtrlGetkAreaIn  IN  = new BsCtrlGetkAreaIn();
	public BsCtrlGetkAreaOut OUT = new BsCtrlGetkAreaOut();
	
	public class BsCtrlGetkAreaIn {
		public String iden;
	}
	
	public class BsCtrlGetkAreaOut {
		public Ctrl ctrl;
	}
}
