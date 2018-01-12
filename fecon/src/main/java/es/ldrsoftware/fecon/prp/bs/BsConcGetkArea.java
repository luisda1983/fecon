package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Conc;

public class BsConcGetkArea extends BaseBSArea {

	public BsConcGetkAreaIn  IN  = new BsConcGetkAreaIn();
	public BsConcGetkAreaOut OUT = new BsConcGetkAreaOut();
	
	public class BsConcGetkAreaIn {
		public long iden;
	}
	
	public class BsConcGetkAreaOut {
		public Conc conc;
	}
}
