package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;

public class BsUsuaExitArea extends BaseBSArea {

	public BsUsuaExitAreaIn  IN  = new BsUsuaExitAreaIn();
	public BsUsuaExitAreaOut OUT = new BsUsuaExitAreaOut();
	
	public class BsUsuaExitAreaIn {
		public long sesi;
	}
	
	public class BsUsuaExitAreaOut {
		
	}
}
