package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Invi;

public class BsInviGetArea extends BaseBSArea {

	public BsInviGetAreaIn  IN  = new BsInviGetAreaIn();
	public BsInviGetAreaOut OUT = new BsInviGetAreaOut();
	
	public class BsInviGetAreaIn {
		public String iden;
	}
	
	public class BsInviGetAreaOut {
		public Invi invi;
	}
}
