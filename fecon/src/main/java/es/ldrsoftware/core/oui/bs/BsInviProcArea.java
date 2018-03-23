package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.Invi;
import es.ldrsoftware.core.oui.entity.Usua;

public class BsInviProcArea extends BaseBSArea {

	public BsInviProcAreaIn  IN  = new BsInviProcAreaIn();
	public BsInviProcAreaOut OUT = new BsInviProcAreaOut();
	
	public class BsInviProcAreaIn {
		public String iden;
		public String desc;
		public String numo;
		public String usua;
		public String pass;
		public String cpas;
		public String mail;
	}
	
	public class BsInviProcAreaOut {
		public Invi invi;
		public Usua usua;
		public Inst inst;
	}
}
