package es.ldrsoftware.core.spt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Domi;

public class BsDomiNuevArea extends BaseBSArea {

	public BsDomiNuevAreaIn  IN  = new BsDomiNuevAreaIn();
	public BsDomiNuevAreaOut OUT = new BsDomiNuevAreaOut();
	
	public class BsDomiNuevAreaIn {
		public String nomb;
		public String desc;
	}
	
	public class BsDomiNuevAreaOut {
		public Domi domi;
	}
}
