package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Invi;

public class BsInviEnviArea extends BaseBSArea {

	public BsInviEnviAreaIn  IN  = new BsInviEnviAreaIn();
	public BsInviEnviAreaOut OUT = new BsInviEnviAreaOut();
	
	public class BsInviEnviAreaIn {
		public String tipo;
		public String mail;
	}
	
	public class BsInviEnviAreaOut {
		public Invi invi;
	}
}
