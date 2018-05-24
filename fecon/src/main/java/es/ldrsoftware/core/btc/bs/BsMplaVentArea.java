package es.ldrsoftware.core.btc.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Mpla;

public class BsMplaVentArea extends BaseBSArea {

	public BsMplaVentAreaIn  IN  = new BsMplaVentAreaIn();
	public BsMplaVentAreaOut OUT = new BsMplaVentAreaOut();
	
	public class BsMplaVentAreaIn {
		public int hora;
	}
	
	public class BsMplaVentAreaOut {
		public int vent;
		public Mpla mpla;
	}
}
