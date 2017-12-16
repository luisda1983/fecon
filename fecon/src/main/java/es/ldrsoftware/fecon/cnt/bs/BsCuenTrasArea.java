package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.cnt.entity.Hcon;

public class BsCuenTrasArea extends BaseBSArea {

	public BsCuenTrasAreaIn  IN  = new BsCuenTrasAreaIn();
	public BsCuenTrasAreaOut OUT = new BsCuenTrasAreaOut();
	
	public class BsCuenTrasAreaIn {
		public long   ctor;
		public long   ctde;
		public int    feva;
		public double impo;
	}
	
	public class BsCuenTrasAreaOut {
		public Cuen ctor;
		public Cuen ctde;
		public Hcon hcor;
		public Hcon hcde;
	}
}
