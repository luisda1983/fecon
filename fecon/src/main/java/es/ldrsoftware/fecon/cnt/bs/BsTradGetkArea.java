package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Trad;

public class BsTradGetkArea extends BaseBSArea {

	public BsTradGetkAreaIn  IN  = new BsTradGetkAreaIn();
	public BsTradGetkAreaOut OUT = new BsTradGetkAreaOut();
	
	public class BsTradGetkAreaIn {
		public long iden;
	}
	
	public class BsTradGetkAreaOut {
		public Trad trad;
	}
}
