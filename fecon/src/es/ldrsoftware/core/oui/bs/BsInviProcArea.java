package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Invi;

public class BsInviProcArea extends BaseBSArea {

	public BsInviProcAreaIn  IN  = new BsInviProcAreaIn();
	public BsInviProcAreaOut OUT = new BsInviProcAreaOut();
	
	public class BsInviProcAreaIn {
		public String iden;
		public String usua;
		public String pass;
		public String cpas;
		public String mail;
	}
	
	public class BsInviProcAreaOut {
		public Invi invi;
	}
}
