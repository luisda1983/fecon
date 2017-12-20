package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Pres;

public class BsPresGetArea extends BaseBSArea {

	public BsPresGetAreaIn  IN  = new BsPresGetAreaIn();
	public BsPresGetAreaOut OUT = new BsPresGetAreaOut();
	
	public class BsPresGetAreaIn {
		public int  anua;
		public int  mesp;
		public long cate;
		public long conc;
	}
	
	public class BsPresGetAreaOut {
		public Pres pres;
	}
}
