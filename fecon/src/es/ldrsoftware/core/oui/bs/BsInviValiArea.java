package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Invi;

public class BsInviValiArea extends BaseBSArea {

	public BsInviValiAreaIn  IN  = new BsInviValiAreaIn();
	public BsInviValiAreaOut OUT = new BsInviValiAreaOut();
	
	public class BsInviValiAreaIn {
		public String iden;
	}
	
	public class BsInviValiAreaOut {
		public Invi invi;
	}
}
