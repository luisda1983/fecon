package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Ctrl;

public class BsCtrlGetArea extends BaseBSArea {

	public BsCtrlGetAreaIn  IN  = new BsCtrlGetAreaIn();
	public BsCtrlGetAreaOut OUT = new BsCtrlGetAreaOut();
	
	public class BsCtrlGetAreaIn {
		public String iden;
	}
	
	public class BsCtrlGetAreaOut {
		public Ctrl ctrl;
	}
}
