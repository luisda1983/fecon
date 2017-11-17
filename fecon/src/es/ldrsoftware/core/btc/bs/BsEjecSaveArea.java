package es.ldrsoftware.core.btc.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Ejec;

public class BsEjecSaveArea extends BaseBSArea {

	public BsEjecSaveAreaIn  IN  = new BsEjecSaveAreaIn();
	public BsEjecSaveAreaOut OUT = new BsEjecSaveAreaOut();
	
	public class BsEjecSaveAreaIn {
		public Ejec ejec;
	}
	
	public class BsEjecSaveAreaOut {
		public Ejec ejec;
	}
}
