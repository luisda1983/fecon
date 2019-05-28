package es.ldrsoftware.core.spt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Domi;

public class BsDomiSaveArea extends BaseBSArea {

	public BsDomiSaveAreaIn  IN  = new BsDomiSaveAreaIn();
	public BsDomiSaveAreaOut OUT = new BsDomiSaveAreaOut();
	
	public class BsDomiSaveAreaIn {
		public Domi domi;
	}
	
	public class BsDomiSaveAreaOut {
		public Domi domi;
	}
}
