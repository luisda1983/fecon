package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Invi;

public class BsInviAcepArea extends BaseBSArea {

	public BsInviAcepAreaIn  IN  = new BsInviAcepAreaIn();
	public BsInviAcepAreaOut OUT = new BsInviAcepAreaOut();
	
	public class BsInviAcepAreaIn {
		public String iden;
	}
	
	public class BsInviAcepAreaOut {
		public Invi invi;
	}
}
