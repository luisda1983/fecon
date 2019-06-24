package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Notf;

public class BsNotfSaveArea extends BaseBSArea {

	public BsNotfSaveAreaIn  IN  = new BsNotfSaveAreaIn();
	public BsNotfSaveAreaOut OUT = new BsNotfSaveAreaOut();
	
	public class BsNotfSaveAreaIn {
		public Notf notf;
	}
	
	public class BsNotfSaveAreaOut {
		public Notf notf;
	}
}
