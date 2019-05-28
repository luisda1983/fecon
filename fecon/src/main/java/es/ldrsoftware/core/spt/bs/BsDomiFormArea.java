package es.ldrsoftware.core.spt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Domi;

public class BsDomiFormArea extends BaseBSArea {

	public BsDomiFormAreaIn  IN  = new BsDomiFormAreaIn();
	public BsDomiFormAreaOut OUT = new BsDomiFormAreaOut();
	
	public class BsDomiFormAreaIn {
		public long   iden;
		public String nomb;
		public String desc;
	}
	
	public class BsDomiFormAreaOut {
		public Domi domi;
	}
}
