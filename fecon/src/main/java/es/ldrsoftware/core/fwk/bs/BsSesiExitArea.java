package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Sesi;

public class BsSesiExitArea extends BaseBSArea {

	public BsSesiExitAreaIn  IN  = new BsSesiExitAreaIn();
	public BsSesiExitAreaOut OUT = new BsSesiExitAreaOut();
	
	public class BsSesiExitAreaIn {
		public long   iden;
	}
	
	public class BsSesiExitAreaOut {
		public Sesi sesi;
	}
}
