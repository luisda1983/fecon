package es.ldrsoftware.core.spt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Dele;

public class BsDeleFormArea extends BaseBSArea {

	public BsDeleFormAreaIn  IN  = new BsDeleFormAreaIn();
	public BsDeleFormAreaOut OUT = new BsDeleFormAreaOut();
	
	public class BsDeleFormAreaIn {
		public long   iden;
		public String domi;
		public String valo;
	}
	
	public class BsDeleFormAreaOut {
		public Dele dele;
	}
}
