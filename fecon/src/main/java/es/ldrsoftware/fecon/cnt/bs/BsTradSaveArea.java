package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Trad;

public class BsTradSaveArea extends BaseBSArea {

	public BsTradSaveAreaIn  IN  = new BsTradSaveAreaIn();
	public BsTradSaveAreaOut OUT = new BsTradSaveAreaOut();
	
	public class BsTradSaveAreaIn {
		public Trad trad;
	}
	
	public class BsTradSaveAreaOut {
		public Trad trad;
	}
}
