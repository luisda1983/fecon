package es.ldrsoftware.core.mnu.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.mnu.entity.Ctmn;

public class BsCtmnDesaArea extends BaseBSArea {

	public BsCtmnDesaAreaIn  IN  = new BsCtmnDesaAreaIn();
	public BsCtmnDesaAreaOut OUT = new BsCtmnDesaAreaOut();
	
	public class BsCtmnDesaAreaIn {
		public long iden;
	}
	
	public class BsCtmnDesaAreaOut {
		public Ctmn ctmn;
	}
}
