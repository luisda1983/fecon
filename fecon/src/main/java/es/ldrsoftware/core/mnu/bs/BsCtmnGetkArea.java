package es.ldrsoftware.core.mnu.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.mnu.entity.Ctmn;

public class BsCtmnGetkArea extends BaseBSArea {

	public BsCtmnGetkAreaIn  IN  = new BsCtmnGetkAreaIn();
	public BsCtmnGetkAreaOut OUT = new BsCtmnGetkAreaOut();
	
	public class BsCtmnGetkAreaIn {
		public long iden;
	}
	
	public class BsCtmnGetkAreaOut {
		public Ctmn ctmn;
	}
}
