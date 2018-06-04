package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Sesi;

public class BsSesiGetkArea extends BaseBSArea {

	public BsSesiGetkAreaIn  IN  = new BsSesiGetkAreaIn();
	public BsSesiGetkAreaOut OUT = new BsSesiGetkAreaOut();
	
	public class BsSesiGetkAreaIn {
		public long   iden;
	}
	
	public class BsSesiGetkAreaOut {
		public Sesi sesi;
	}
}
