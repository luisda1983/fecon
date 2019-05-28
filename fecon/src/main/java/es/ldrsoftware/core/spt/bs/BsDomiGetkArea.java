package es.ldrsoftware.core.spt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Domi;

public class BsDomiGetkArea extends BaseBSArea {

	public BsDomiGetkAreaIn  IN  = new BsDomiGetkAreaIn();
	public BsDomiGetkAreaOut OUT = new BsDomiGetkAreaOut();
	
	public class BsDomiGetkAreaIn {
		public long iden;
	}
	
	public class BsDomiGetkAreaOut {
		public Domi domi;
	}
}
