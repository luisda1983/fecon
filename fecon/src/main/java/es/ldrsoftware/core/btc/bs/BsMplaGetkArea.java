package es.ldrsoftware.core.btc.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Mpla;

public class BsMplaGetkArea extends BaseBSArea {

	public BsMplaGetkAreaIn  IN  = new BsMplaGetkAreaIn();
	public BsMplaGetkAreaOut OUT = new BsMplaGetkAreaOut();
	
	public class BsMplaGetkAreaIn {
		public int hora;
	}
	
	public class BsMplaGetkAreaOut {
		public Mpla mpla;
	}
}
