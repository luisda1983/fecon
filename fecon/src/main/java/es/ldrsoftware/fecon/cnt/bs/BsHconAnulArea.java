package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.cnt.entity.Hcon;
import es.ldrsoftware.fecon.prp.entity.Pres;

public class BsHconAnulArea extends BaseBSArea {

	public BsHconAnulAreaIn  IN  = new BsHconAnulAreaIn();
	public BsHconAnulAreaOut OUT = new BsHconAnulAreaOut();
	
	public class BsHconAnulAreaIn {
		public long   iden;
	}
	
	public class BsHconAnulAreaOut {
		public Hcon hcon;
		public Cuen cuen;
		public Pres pres;
	}
}
