package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;

public class BsCuenFormArea extends BaseBSArea {

	public BsCuenFormAreaIn  IN  = new BsCuenFormAreaIn();
	public BsCuenFormAreaOut OUT = new BsCuenFormAreaOut();
	
	public class BsCuenFormAreaIn {
		public long   iden;
		public String tipo;
		public String desc;
		public double sald;
	}
	
	public class BsCuenFormAreaOut {
		public Cuen cuen;
	}
}
