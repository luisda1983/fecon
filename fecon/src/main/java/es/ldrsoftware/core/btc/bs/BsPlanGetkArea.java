package es.ldrsoftware.core.btc.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Plan;

public class BsPlanGetkArea extends BaseBSArea {

	public BsPlanGetkAreaIn  IN  = new BsPlanGetkAreaIn();
	public BsPlanGetkAreaOut OUT = new BsPlanGetkAreaOut();
	
	public class BsPlanGetkAreaIn {
		public int fech;
		public int hora;
	}
	
	public class BsPlanGetkAreaOut {
		public Plan plan;
	}
}
