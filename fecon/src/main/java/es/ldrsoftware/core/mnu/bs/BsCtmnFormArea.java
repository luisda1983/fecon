package es.ldrsoftware.core.mnu.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.mnu.entity.Ctmn;

public class BsCtmnFormArea extends BaseBSArea {

	public BsCtmnFormAreaIn  IN  = new BsCtmnFormAreaIn();
	public BsCtmnFormAreaOut OUT = new BsCtmnFormAreaOut();
	
	public class BsCtmnFormAreaIn {
		public long   iden;
		public String perf;
		public String desc;
		public String acti;
		public long   orde;
	}
	
	public class BsCtmnFormAreaOut {
		public Ctmn ctmn;
	}
}
