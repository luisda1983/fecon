package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;

public class BsCuenGetkArea extends BaseBSArea {

	public BsCuenGetkAreaIn  IN  = new BsCuenGetkAreaIn();
	public BsCuenGetkAreaOut OUT = new BsCuenGetkAreaOut();
	
	public class BsCuenGetkAreaIn {
		public long iden;
	}
	
	public class BsCuenGetkAreaOut {
		public Cuen cuen;
	}
}
