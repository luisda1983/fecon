package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Sesi;

public class BsSesiGetArea extends BaseBSArea {

	public BsSesiGetAreaIn  IN  = new BsSesiGetAreaIn();
	public BsSesiGetAreaOut OUT = new BsSesiGetAreaOut();
	
	public class BsSesiGetAreaIn {
		public long   iden;
		public long   clav;
	}
	
	public class BsSesiGetAreaOut {
		public Sesi sesi;
	}
}
