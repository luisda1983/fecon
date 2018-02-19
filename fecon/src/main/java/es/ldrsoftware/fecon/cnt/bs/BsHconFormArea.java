package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.cnt.entity.Hcon;

public class BsHconFormArea extends BaseBSArea {

	public BsHconFormAreaIn  IN  = new BsHconFormAreaIn();
	public BsHconFormAreaOut OUT = new BsHconFormAreaOut();
	
	public class BsHconFormAreaIn {
		public String tipo;
		public long   iden;
		public long   cate;
		public long   conc;
		public long   cuen;
		public double impo;
		public int    feva;
		public String desc;
	}
	
	public class BsHconFormAreaOut {
		public Hcon hcon;
		public Cuen cuen;
	}
}
