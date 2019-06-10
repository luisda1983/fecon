package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;

public class BsPresCierArea extends BaseBSArea {

	public BsPresCierAreaIn  IN  = new BsPresCierAreaIn();
	public BsPresCierAreaOut OUT = new BsPresCierAreaOut();
	
	public class BsPresCierAreaIn {
		public int anua;
		public int mesp;
	}
	
	public class BsPresCierAreaOut {
		public int anua;
		public int mesp;
	}
}
