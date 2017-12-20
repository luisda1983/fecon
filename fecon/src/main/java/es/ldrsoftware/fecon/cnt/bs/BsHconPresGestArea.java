package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Hcon;
import es.ldrsoftware.fecon.prp.entity.Pres;

public class BsHconPresGestArea extends BaseBSArea {

	public BsHconPresGestAreaIn  IN  = new BsHconPresGestAreaIn();
	public BsHconPresGestAreaOut OUT = new BsHconPresGestAreaOut();
	
	public class BsHconPresGestAreaIn {
		public String acci;
		public long   iden;
	}
	
	public class BsHconPresGestAreaOut {
		public Hcon hcon;
		public Pres pres;
	}
}
