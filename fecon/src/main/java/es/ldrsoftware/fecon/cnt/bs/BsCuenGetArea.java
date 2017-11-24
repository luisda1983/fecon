package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;

public class BsCuenGetArea extends BaseBSArea {

	public BsCuenGetAreaIn  IN  = new BsCuenGetAreaIn();
	public BsCuenGetAreaOut OUT = new BsCuenGetAreaOut();
	
	public class BsCuenGetAreaIn {
		public long iden;
	}
	
	public class BsCuenGetAreaOut {
		public Cuen cuen;
	}
}
