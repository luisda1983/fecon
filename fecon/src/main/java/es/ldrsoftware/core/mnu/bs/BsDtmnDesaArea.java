package es.ldrsoftware.core.mnu.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.mnu.entity.Dtmn;

public class BsDtmnDesaArea extends BaseBSArea {

	public BsDtmnDesaAreaIn  IN  = new BsDtmnDesaAreaIn();
	public BsDtmnDesaAreaOut OUT = new BsDtmnDesaAreaOut();
	
	public class BsDtmnDesaAreaIn {
		public long ctmn;
		public long iden;
	}
	
	public class BsDtmnDesaAreaOut {
		public Dtmn dtmn;
	}
}
