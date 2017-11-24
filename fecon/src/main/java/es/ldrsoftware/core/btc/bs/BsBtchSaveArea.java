package es.ldrsoftware.core.btc.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Btch;

public class BsBtchSaveArea extends BaseBSArea {

	public BsBtchSaveAreaIn  IN  = new BsBtchSaveAreaIn();
	public BsBtchSaveAreaOut OUT = new BsBtchSaveAreaOut();
	
	public class BsBtchSaveAreaIn {
		public Btch btch;
	}
	
	public class BsBtchSaveAreaOut {
		public Btch btch;
	}
}
