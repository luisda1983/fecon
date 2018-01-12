package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;

public class BsConcGetoArea extends BaseBSArea {

	public BsConcGetoAreaIn  IN  = new BsConcGetoAreaIn();
	public BsConcGetoAreaOut OUT = new BsConcGetoAreaOut();
	
	public class BsConcGetoAreaIn {
		public long cate;
	}
	
	public class BsConcGetoAreaOut {
		public int orde;
	}
}
