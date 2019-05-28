package es.ldrsoftware.core.spt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Domi;

public class BsDomiEditArea extends BaseBSArea {

	public BsDomiEditAreaIn  IN  = new BsDomiEditAreaIn();
	public BsDomiEditAreaOut OUT = new BsDomiEditAreaOut();
	
	public class BsDomiEditAreaIn {
		public long   iden;
		public String nomb;
		public String desc;
	}
	
	public class BsDomiEditAreaOut {
		public Domi domi;
	}
}
