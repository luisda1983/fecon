package es.ldrsoftware.core.sts.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.sts.entity.Stst;

public class BsStstRegiArea extends BaseBSArea {

	public BsStstRegiAreaIn  IN  = new BsStstRegiAreaIn();
	public BsStstRegiAreaOut OUT = new BsStstRegiAreaOut();
	
	public class BsStstRegiAreaIn {
		public String ctrl;
		public long inej;
		public long fiej;
		public String reej;
		public String notf;
	}
	
	public class BsStstRegiAreaOut {
		public Stst stst;
	}
}
