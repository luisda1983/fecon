package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Usua;

public class BsUsuaSaveArea extends BaseBSArea {

	public BsUsuaSaveAreaIn  IN  = new BsUsuaSaveAreaIn();
	public BsUsuaSaveAreaOut OUT = new BsUsuaSaveAreaOut();
	
	public class BsUsuaSaveAreaIn {
		public Usua usua;
	}
	
	public class BsUsuaSaveAreaOut {
		public Usua usua;
	}
}
