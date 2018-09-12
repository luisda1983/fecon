package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Rela;

public class BsRelaInstUsuaArea extends BaseBSArea {

	public BsRelaSaveAreaIn  IN  = new BsRelaSaveAreaIn();
	public BsRelaSaveAreaOut OUT = new BsRelaSaveAreaOut();
	
	public class BsRelaSaveAreaIn {
		public long   inst;
		public String usua;
		public String perf;
	}
	
	public class BsRelaSaveAreaOut {
		public Rela rela;
	}
}
