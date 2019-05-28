package es.ldrsoftware.core.spt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Dele;

public class BsDeleNuevArea extends BaseBSArea {

	public BsDeleNuevAreaIn  IN  = new BsDeleNuevAreaIn();
	public BsDeleNuevAreaOut OUT = new BsDeleNuevAreaOut();
	
	public class BsDeleNuevAreaIn {
		public String domi;
		public String valo;
	}
	
	public class BsDeleNuevAreaOut {
		public Dele dele;
	}
}
