package es.ldrsoftware.core.spt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Dele;

public class BsDeleSaveArea extends BaseBSArea {

	public BsDeleSaveAreaIn  IN  = new BsDeleSaveAreaIn();
	public BsDeleSaveAreaOut OUT = new BsDeleSaveAreaOut();
	
	public class BsDeleSaveAreaIn {
		public Dele dele;
	}
	
	public class BsDeleSaveAreaOut {
		public Dele dele;
	}
}
