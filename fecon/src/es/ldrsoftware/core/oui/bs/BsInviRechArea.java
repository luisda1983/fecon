package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Invi;

public class BsInviRechArea extends BaseBSArea {

	public BsInviRechAreaIn  IN  = new BsInviRechAreaIn();
	public BsInviRechAreaOut OUT = new BsInviRechAreaOut();
	
	public class BsInviRechAreaIn {
		public String iden;
	}
	
	public class BsInviRechAreaOut {
		public Invi invi;
	}
}
