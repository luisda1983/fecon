package es.ldrsoftware.core.btc.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Plan;

public class BsPlanListArea extends BaseBSArea {

	public BsPlanListAreaIn  IN  = new BsPlanListAreaIn();
	public BsPlanListAreaOut OUT = new BsPlanListAreaOut();
	
	public class BsPlanListAreaIn {
		public String tipo;
		public    int fech;
	}
	
	public class BsPlanListAreaOut {
		public List<Plan> planList;
	}
}
