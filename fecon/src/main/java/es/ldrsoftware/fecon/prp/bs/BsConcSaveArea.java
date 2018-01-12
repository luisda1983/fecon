package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Conc;

public class BsConcSaveArea extends BaseBSArea {

	public BsConcSaveAreaIn  IN  = new BsConcSaveAreaIn();
	public BsConcSaveAreaOut OUT = new BsConcSaveAreaOut();
	
	public class BsConcSaveAreaIn {
		public Conc conc;
	}
	
	public class BsConcSaveAreaOut {
		public Conc conc;
	}
}
