package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Lite;

public class BsLiteGetArea extends BaseBSArea {

	public BsLiteGetAreaIn  IN  = new BsLiteGetAreaIn();
	public BsLiteGetAreaOut OUT = new BsLiteGetAreaOut();
	
	public class BsLiteGetAreaIn {
		public String tbla;
		public String clav;
	}
	
	public class BsLiteGetAreaOut {
		public Lite lite;
	}
}
