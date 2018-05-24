package es.ldrsoftware.core.btc.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Btch;
import es.ldrsoftware.core.btc.entity.Ejec;

public class BsEjecPlanArea extends BaseBSArea {

	public BsEjecPlanAreaIn  IN  = new BsEjecPlanAreaIn();
	public BsEjecPlanAreaOut OUT = new BsEjecPlanAreaOut();
	
	public class BsEjecPlanAreaIn {
		public String btch;
		public    int fech;
		public    int hora;
		public    int secu;
		public boolean ajus;
	}
	
	public class BsEjecPlanAreaOut {
		public Btch btch;
		public Ejec ejec;
	}
}
