package es.ldrsoftware.core.mnu.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.mnu.entity.Dtmn;

public class BsDtmnActiArea extends BaseBSArea {

	public BsDtmnActiAreaIn  IN  = new BsDtmnActiAreaIn();
	public BsDtmnActiAreaOut OUT = new BsDtmnActiAreaOut();
	
	public class BsDtmnActiAreaIn {
		public long ctmn;
		public long iden;
	}
	
	public class BsDtmnActiAreaOut {
		public Dtmn dtmn;
	}
}
