package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Usua;

public class BsUsuaRegiArea extends BaseBSArea {

	public BsUsuaRegiAreaIn  IN  = new BsUsuaRegiAreaIn();
	public BsUsuaRegiAreaOut OUT = new BsUsuaRegiAreaOut();
	
	public class BsUsuaRegiAreaIn {
		public boolean invi;
		public long   inst;
		public String codi;
		public String numo;
		public String iden;
		public String pass;
		public String cpas;
		public String mail;
		public String perf;
	}
	
	public class BsUsuaRegiAreaOut {
		public Usua usua;
	}
}
