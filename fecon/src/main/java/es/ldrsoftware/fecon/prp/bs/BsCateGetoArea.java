package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;

public class BsCateGetoArea extends BaseBSArea {

	public BsCateGetoAreaIn  IN  = new BsCateGetoAreaIn();
	public BsCateGetoAreaOut OUT = new BsCateGetoAreaOut();
	
	public class BsCateGetoAreaIn {
	}
	
	public class BsCateGetoAreaOut {
		public int orde;
	}
}
