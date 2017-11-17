package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Inst;

public class BsInstRegiArea extends BaseBSArea {

	public BsInstRegiAreaIn  IN  = new BsInstRegiAreaIn();
	public BsInstRegiAreaOut OUT = new BsInstRegiAreaOut();
	
	public class BsInstRegiAreaIn {
		public String invi;
		public String mail;
		public String usua;
		public String pass;
		public String cpas;
	}
	
	public class BsInstRegiAreaOut {
		public Inst inst;
	}
}
