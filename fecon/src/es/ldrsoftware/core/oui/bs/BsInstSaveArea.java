package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Inst;

public class BsInstSaveArea extends BaseBSArea {

	public BsInstSaveAreaIn  IN  = new BsInstSaveAreaIn();
	public BsInstSaveAreaOut OUT = new BsInstSaveAreaOut();
	
	public class BsInstSaveAreaIn {
		public Inst inst;
	}
	
	public class BsInstSaveAreaOut {
		public Inst inst;
	}
}
