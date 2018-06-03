package es.ldrsoftware.core.sts.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.sts.entity.Stme;

public class BsStmeSaveArea extends BaseBSArea {

	public BsStmeSaveAreaIn  IN  = new BsStmeSaveAreaIn();
	public BsStmeSaveAreaOut OUT = new BsStmeSaveAreaOut();
	
	public class BsStmeSaveAreaIn {
		public Stme stme;
	}
	
	public class BsStmeSaveAreaOut {
		public Stme stme;
	}
}
