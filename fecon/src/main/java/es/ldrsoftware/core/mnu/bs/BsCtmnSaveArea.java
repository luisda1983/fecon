package es.ldrsoftware.core.mnu.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.mnu.entity.Ctmn;

public class BsCtmnSaveArea extends BaseBSArea {

	public BsCtmnSaveAreaIn  IN  = new BsCtmnSaveAreaIn();
	public BsCtmnSaveAreaOut OUT = new BsCtmnSaveAreaOut();
	
	public class BsCtmnSaveAreaIn {
		public Ctmn ctmn;
	}
	
	public class BsCtmnSaveAreaOut {
		public Ctmn ctmn;
	}
}
