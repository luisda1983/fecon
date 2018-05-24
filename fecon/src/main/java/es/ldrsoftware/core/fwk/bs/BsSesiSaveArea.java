package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Sesi;

public class BsSesiSaveArea extends BaseBSArea {

	public BsSesiSaveAreaIn  IN  = new BsSesiSaveAreaIn();
	public BsSesiSaveAreaOut OUT = new BsSesiSaveAreaOut();
	
	public class BsSesiSaveAreaIn {
		public Sesi sesi;
	}
	
	public class BsSesiSaveAreaOut {
		public Sesi sesi;
	}
}
