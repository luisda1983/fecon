package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Para;

public class BsParaSaveArea extends BaseBSArea {

	public BsParaSaveAreaIn  IN  = new BsParaSaveAreaIn();
	public BsParaSaveAreaOut OUT = new BsParaSaveAreaOut();
	
	public class BsParaSaveAreaIn {
		public Para para;
	}
	
	public class BsParaSaveAreaOut {
		public Para para;
	}
}
