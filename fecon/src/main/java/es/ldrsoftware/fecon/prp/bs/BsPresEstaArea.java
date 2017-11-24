package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Pres;

public class BsPresEstaArea extends BaseBSArea {

	public BsPresEstaAreaIn  IN  = new BsPresEstaAreaIn();
	public BsPresEstaAreaOut OUT = new BsPresEstaAreaOut();
	
	public class BsPresEstaAreaIn {
		public    int anua;
		public    int mesp;
		public   long cate;
		public   long conc;
		public String esta;
	}
	
	public class BsPresEstaAreaOut {
		public Pres pres;
	}
}
