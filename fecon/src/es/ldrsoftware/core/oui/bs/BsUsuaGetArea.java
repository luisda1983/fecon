package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Usua;

public class BsUsuaGetArea extends BaseBSArea {

	public BsUsuaGetAreaIn  IN  = new BsUsuaGetAreaIn();
	public BsUsuaGetAreaOut OUT = new BsUsuaGetAreaOut();
	
	public class BsUsuaGetAreaIn {
		public String iden;
		public String mail;
	}
	
	public class BsUsuaGetAreaOut {
		public Usua usua;
	}
}
