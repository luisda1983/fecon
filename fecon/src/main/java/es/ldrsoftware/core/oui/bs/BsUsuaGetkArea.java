package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Usua;

public class BsUsuaGetkArea extends BaseBSArea {

	public BsUsuaGetkAreaIn  IN  = new BsUsuaGetkAreaIn();
	public BsUsuaGetkAreaOut OUT = new BsUsuaGetkAreaOut();
	
	public class BsUsuaGetkAreaIn {
		public String iden;
	}
	
	public class BsUsuaGetkAreaOut {
		public Usua usua;
	}
}
