package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Sesi;

public class BsSesiGetuArea extends BaseBSArea {

	public BsSesiGetuAreaIn  IN  = new BsSesiGetuAreaIn();
	public BsSesiGetuAreaOut OUT = new BsSesiGetuAreaOut();
	
	public class BsSesiGetuAreaIn {
		public String usua;
	}
	
	public class BsSesiGetuAreaOut {
		public Sesi sesi;
	}
}
