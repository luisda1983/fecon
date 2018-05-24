package es.ldrsoftware.core.btc.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Plan;

public class BsPlanSaveArea extends BaseBSArea {

	public BsPlanSaveAreaIn  IN  = new BsPlanSaveAreaIn();
	public BsPlanSaveAreaOut OUT = new BsPlanSaveAreaOut();
	
	public class BsPlanSaveAreaIn {
		public Plan plan;
	}
	
	public class BsPlanSaveAreaOut {
		public Plan plan;
	}
}
