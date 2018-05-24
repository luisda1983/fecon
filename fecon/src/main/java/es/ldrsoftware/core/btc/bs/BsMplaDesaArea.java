package es.ldrsoftware.core.btc.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Mpla;

public class BsMplaDesaArea extends BaseBSArea {

	public BsMplaDesaAreaIn  IN  = new BsMplaDesaAreaIn();
	public BsMplaDesaAreaOut OUT = new BsMplaDesaAreaOut();
	
	public class BsMplaDesaAreaIn {
		public int hora;
	}
	
	public class BsMplaDesaAreaOut {
		public Mpla mpla;
	}
}
