package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Invi;

public class BsInviSaveArea extends BaseBSArea {

	public BsInviSaveAreaIn  IN  = new BsInviSaveAreaIn();
	public BsInviSaveAreaOut OUT = new BsInviSaveAreaOut();
	
	public class BsInviSaveAreaIn {
		public Invi invi;
	}
	
	public class BsInviSaveAreaOut {
		public Invi invi;
	}
}
