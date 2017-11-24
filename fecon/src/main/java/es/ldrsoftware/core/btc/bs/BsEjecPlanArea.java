package es.ldrsoftware.core.btc.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Ejec;

public class BsEjecPlanArea extends BaseBSArea {

	public BsEjecPlanAreaIn  IN  = new BsEjecPlanAreaIn();
	public BsEjecPlanAreaOut OUT = new BsEjecPlanAreaOut();
	
	public class BsEjecPlanAreaIn {
		public String btch;
		public    int fech;
		public    int secu;
		public    int orde;
	}
	
	public class BsEjecPlanAreaOut {
		public Ejec ejec;
	}
}
