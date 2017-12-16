package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Hcon;

public class BsHconApunAnulArea extends BaseBSArea {

	public BsHconApunAnulAreaIn  IN  = new BsHconApunAnulAreaIn();
	public BsHconApunAnulAreaOut OUT = new BsHconApunAnulAreaOut();
	
	public class BsHconApunAnulAreaIn {
		public Hcon hcon;
	}
	
	public class BsHconApunAnulAreaOut {
		public Hcon hcon;
	}
}
