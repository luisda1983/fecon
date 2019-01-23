package es.ldrsoftware.fecon.cnt.bs;


import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Hcon;

public class BsHconGetArea extends BaseBSArea {

	public BsHconGetAreaIn  IN  = new BsHconGetAreaIn();
	public BsHconGetAreaOut OUT = new BsHconGetAreaOut();
	
	public class BsHconGetAreaIn {
		public long iden;
	}
	
	public class BsHconGetAreaOut {
		public Hcon hcon;
	}
}
