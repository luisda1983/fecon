package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Rela;

public class BsRelaSaveArea extends BaseBSArea {

	public BsRelaSaveAreaIn  IN  = new BsRelaSaveAreaIn();
	public BsRelaSaveAreaOut OUT = new BsRelaSaveAreaOut();
	
	public class BsRelaSaveAreaIn {
		public Rela rela;
	}
	
	public class BsRelaSaveAreaOut {
		public Rela rela;
	}
}
