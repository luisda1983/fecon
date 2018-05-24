package es.ldrsoftware.core.btc.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Mpla;

public class BsMplaSaveArea extends BaseBSArea {

	public BsMplaSaveAreaIn  IN  = new BsMplaSaveAreaIn();
	public BsMplaSaveAreaOut OUT = new BsMplaSaveAreaOut();
	
	public class BsMplaSaveAreaIn {
		public Mpla mpla;
	}
	
	public class BsMplaSaveAreaOut {
		public Mpla mpla;
	}
}
