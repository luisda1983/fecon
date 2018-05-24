package es.ldrsoftware.core.btc.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Logp;

public class BsLogpSaveArea extends BaseBSArea {

	public BsLogpSaveAreaIn  IN  = new BsLogpSaveAreaIn();
	public BsLogpSaveAreaOut OUT = new BsLogpSaveAreaOut();
	
	public class BsLogpSaveAreaIn {
		public Logp logp;
	}
	
	public class BsLogpSaveAreaOut {
		public Logp logp;
	}
}
