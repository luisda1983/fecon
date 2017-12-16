package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.cnt.entity.Hcon;

public class BsHconApunArea extends BaseBSArea {

	public BsHconApunAreaIn  IN  = new BsHconApunAreaIn();
	public BsHconApunAreaOut OUT = new BsHconApunAreaOut();
	
	public class BsHconApunAreaIn {
		public long   cate;
		public long   conc;
		public long   cuen;
		public double impo;
		public int    feva;
		public String desc;
	}
	
	public class BsHconApunAreaOut {
		public Hcon hcon;
		public Cuen cuen;
	}
}
