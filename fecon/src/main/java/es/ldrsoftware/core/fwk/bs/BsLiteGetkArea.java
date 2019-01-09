package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Lite;

public class BsLiteGetkArea extends BaseBSArea {

	public BsLiteGetkAreaIn  IN  = new BsLiteGetkAreaIn();
	public BsLiteGetkAreaOut OUT = new BsLiteGetkAreaOut();
	
	public class BsLiteGetkAreaIn {
		public String tbla;
		public String clav;
	}
	
	public class BsLiteGetkAreaOut {
		public Lite lite;
	}
}
