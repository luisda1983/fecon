package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Usua;

public class BsUsuaValiArea extends BaseBSArea {

	public BsUsuaValiAreaIn  IN  = new BsUsuaValiAreaIn();
	public BsUsuaValiAreaOut OUT = new BsUsuaValiAreaOut();
	
	public class BsUsuaValiAreaIn {
		public String iden;
		public String pass;
		public String mail;
	}
	
	public class BsUsuaValiAreaOut {
		public Usua usua;
	}
}
