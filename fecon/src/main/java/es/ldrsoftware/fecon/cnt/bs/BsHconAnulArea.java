package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Hcon;

public class BsHconAnulArea extends BaseBSArea {

	public BsHconAnulAreaIn  IN  = new BsHconAnulAreaIn();
	public BsHconAnulAreaOut OUT = new BsHconAnulAreaOut();
	
	public class BsHconAnulAreaIn {
		public long   iden;
	}
	
	public class BsHconAnulAreaOut {
		public Hcon hcon;
	}
}
