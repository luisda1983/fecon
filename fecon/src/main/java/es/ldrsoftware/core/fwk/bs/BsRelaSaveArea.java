package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Rela;

public class BsRelaSaveArea extends BaseBSArea {

	public BsRelaSaveAreaIn  IN  = new BsRelaSaveAreaIn();
	public BsRelaSaveAreaOut OUT = new BsRelaSaveAreaOut();
	
	public class BsRelaSaveAreaIn {
		public String rela;
		public long   cln1;
		public long   cln2;
		public String clc1;
		public String clc2;
	}
	
	public class BsRelaSaveAreaOut {
		public Rela rela;
	}
}
