package es.ldrsoftware.core.btc.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Mpla;

public class BsMplaActiArea extends BaseBSArea {

	public BsMplaActiAreaIn  IN  = new BsMplaActiAreaIn();
	public BsMplaActiAreaOut OUT = new BsMplaActiAreaOut();
	
	public class BsMplaActiAreaIn {
		public int hora;
	}
	
	public class BsMplaActiAreaOut {
		public Mpla mpla;
	}
}
