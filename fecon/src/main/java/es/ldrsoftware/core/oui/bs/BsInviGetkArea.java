package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Invi;

public class BsInviGetkArea extends BaseBSArea {

	public BsInviGetkAreaIn  IN  = new BsInviGetkAreaIn();
	public BsInviGetkAreaOut OUT = new BsInviGetkAreaOut();
	
	public class BsInviGetkAreaIn {
		public String iden;
	}
	
	public class BsInviGetkAreaOut {
		public Invi invi;
	}
}
