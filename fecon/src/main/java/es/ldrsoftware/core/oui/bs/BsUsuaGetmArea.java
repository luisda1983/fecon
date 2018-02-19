package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Usua;

public class BsUsuaGetmArea extends BaseBSArea {

	public BsUsuaGetmAreaIn  IN  = new BsUsuaGetmAreaIn();
	public BsUsuaGetmAreaOut OUT = new BsUsuaGetmAreaOut();
	
	public class BsUsuaGetmAreaIn {
		public String mail;
	}
	
	public class BsUsuaGetmAreaOut {
		public Usua usua;
	}
}
