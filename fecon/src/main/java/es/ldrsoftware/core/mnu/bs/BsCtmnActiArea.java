package es.ldrsoftware.core.mnu.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.mnu.entity.Ctmn;

public class BsCtmnActiArea extends BaseBSArea {

	public BsCtmnActiAreaIn  IN  = new BsCtmnActiAreaIn();
	public BsCtmnActiAreaOut OUT = new BsCtmnActiAreaOut();
	
	public class BsCtmnActiAreaIn {
		public long iden;
	}
	
	public class BsCtmnActiAreaOut {
		public Ctmn ctmn;
	}
}
