package es.ldrsoftware.core.spt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Domi;

public class BsDomiGetnArea extends BaseBSArea {

	public BsDomiGetnAreaIn  IN  = new BsDomiGetnAreaIn();
	public BsDomiGetnAreaOut OUT = new BsDomiGetnAreaOut();
	
	public class BsDomiGetnAreaIn {
		public String nomb;
	}
	
	public class BsDomiGetnAreaOut {
		public Domi domi;
	}
}
