package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Conc;

public class BsConcGetdArea extends BaseBSArea {

	public BsConcGetdAreaIn  IN  = new BsConcGetdAreaIn();
	public BsConcGetdAreaOut OUT = new BsConcGetdAreaOut();
	
	public class BsConcGetdAreaIn {
		public long   cate;
		public String desl;
		public String desc;
	}
	
	public class BsConcGetdAreaOut {
		public Conc concDesl;
		public Conc concDesc;
	}
}
