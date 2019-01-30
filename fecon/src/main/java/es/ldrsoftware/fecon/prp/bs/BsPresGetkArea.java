package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Pres;

public class BsPresGetkArea extends BaseBSArea {

	public BsPresGetkAreaIn  IN  = new BsPresGetkAreaIn();
	public BsPresGetkAreaOut OUT = new BsPresGetkAreaOut();
	
	public class BsPresGetkAreaIn {
		public int  anua;
		public int  mesp;
		public long cate;
		public long conc;
	}
	
	public class BsPresGetkAreaOut {
		public Pres pres;
	}
}
