package es.ldrsoftware.core.spt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Dele;

public class BsDeleEditArea extends BaseBSArea {

	public BsDeleEditAreaIn  IN  = new BsDeleEditAreaIn();
	public BsDeleEditAreaOut OUT = new BsDeleEditAreaOut();
	
	public class BsDeleEditAreaIn {
		public long   iden;
		public String domi;
		public String valo;
	}
	
	public class BsDeleEditAreaOut {
		public Dele dele;
	}
}
