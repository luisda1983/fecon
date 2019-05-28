package es.ldrsoftware.core.spt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Dele;

public class BsDeleGetvArea extends BaseBSArea {

	public BsDeleGetvAreaIn  IN  = new BsDeleGetvAreaIn();
	public BsDeleGetvAreaOut OUT = new BsDeleGetvAreaOut();
	
	public class BsDeleGetvAreaIn {
		public String domi;
		public String valo;
	}
	
	public class BsDeleGetvAreaOut {
		public Dele dele;
	}
}
