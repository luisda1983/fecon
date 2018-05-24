package es.ldrsoftware.core.btc.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Plan;

public class BsPlanNuevArea extends BaseBSArea {

	public BsPlanNuevAreaIn  IN  = new BsPlanNuevAreaIn();
	public BsPlanNuevAreaOut OUT = new BsPlanNuevAreaOut();
	
	public class BsPlanNuevAreaIn {
		public int  fech;
		public int  hora;
		public int  fbtc;
	}
	
	public class BsPlanNuevAreaOut {
		public Plan plan;
	}
}
