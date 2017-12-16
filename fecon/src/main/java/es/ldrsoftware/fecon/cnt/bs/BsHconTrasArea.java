package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.cnt.entity.Hcon;

public class BsHconTrasArea extends BaseBSArea {

	public BsHconTrasAreaIn  IN  = new BsHconTrasAreaIn();
	public BsHconTrasAreaOut OUT = new BsHconTrasAreaOut();
	
	public class BsHconTrasAreaIn {
		public long   ctor;
		public long   ctde;
		public double impo;
		public int    feva;
	}
	
	public class BsHconTrasAreaOut {
		public Hcon hcor;
		public Cuen ctor;
		public Hcon hcde;
		public Cuen ctde;
	}
}
