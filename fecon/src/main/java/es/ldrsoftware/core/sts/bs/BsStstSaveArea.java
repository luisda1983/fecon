package es.ldrsoftware.core.sts.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.sts.entity.Stst;

public class BsStstSaveArea extends BaseBSArea {

	public BsStstSaveAreaIn  IN  = new BsStstSaveAreaIn();
	public BsStstSaveAreaOut OUT = new BsStstSaveAreaOut();
	
	public class BsStstSaveAreaIn {
		public Stst stst;
	}
	
	public class BsStstSaveAreaOut {
		public Stst stst;
	}
}
