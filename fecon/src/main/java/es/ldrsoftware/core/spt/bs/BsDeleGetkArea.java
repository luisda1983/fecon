package es.ldrsoftware.core.spt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Dele;

public class BsDeleGetkArea extends BaseBSArea {

	public BsDeleGetkAreaIn  IN  = new BsDeleGetkAreaIn();
	public BsDeleGetkAreaOut OUT = new BsDeleGetkAreaOut();
	
	public class BsDeleGetkAreaIn {
		public long iden;
	}
	
	public class BsDeleGetkAreaOut {
		public Dele dele;
	}
}
