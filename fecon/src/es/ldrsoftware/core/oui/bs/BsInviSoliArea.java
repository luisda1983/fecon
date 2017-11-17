package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Invi;

public class BsInviSoliArea extends BaseBSArea {

	public BsInviSoliAreaIn  IN  = new BsInviSoliAreaIn();
	public BsInviSoliAreaOut OUT = new BsInviSoliAreaOut();
	
	public class BsInviSoliAreaIn {
		public String mail;
	}
	
	public class BsInviSoliAreaOut {
		public Invi invi;
	}
}
