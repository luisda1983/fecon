package es.ldrsoftware.fecon.cnt.bs;


import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Hcon;

public class BsHconGetkArea extends BaseBSArea {

	public BsHconGetkAreaIn  IN  = new BsHconGetkAreaIn();
	public BsHconGetkAreaOut OUT = new BsHconGetkAreaOut();
	
	public class BsHconGetkAreaIn {
		public long iden;
	}
	
	public class BsHconGetkAreaOut {
		public Hcon hcon;
	}
}
