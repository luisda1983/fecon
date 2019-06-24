package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Notf;

public class BsNotfEditArea extends BaseBSArea {

	public BsNotfEditAreaIn  IN  = new BsNotfEditAreaIn();
	public BsNotfEditAreaOut OUT = new BsNotfEditAreaOut();
	
	public class BsNotfEditAreaIn {
		public String iden;
		public String tipo;
		public String desc;
	}
	
	public class BsNotfEditAreaOut {
		public Notf notf;
	}
}
