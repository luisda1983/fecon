package es.ldrsoftware.core.sts.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.sts.entity.Stst;

public class BsStstSaveArea extends BaseBSArea {

	public BsStstSaveAreaIn  IN  = new BsStstSaveAreaIn();
	public BsStstSaveAreaOut OUT = new BsStstSaveAreaOut();
	
	public class BsStstSaveAreaIn {
		public String ctrl;
		public long inej;
		public long fiej;
		public String reej;
		public String notf;
	}
	
	public class BsStstSaveAreaOut {
		public Stst stst;
	}
}
