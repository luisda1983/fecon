package es.ldrsoftware.core.mnu.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.mnu.entity.Dtmn;

public class BsDtmnGetkArea extends BaseBSArea {

	public BsDtmnGetkAreaIn  IN  = new BsDtmnGetkAreaIn();
	public BsDtmnGetkAreaOut OUT = new BsDtmnGetkAreaOut();
	
	public class BsDtmnGetkAreaIn {
		public long ctmn;
		public long iden;
	}
	
	public class BsDtmnGetkAreaOut {
		public Dtmn dtmn;
	}
}
