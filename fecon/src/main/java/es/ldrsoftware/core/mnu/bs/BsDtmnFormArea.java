package es.ldrsoftware.core.mnu.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.mnu.entity.Dtmn;

public class BsDtmnFormArea extends BaseBSArea {

	public BsDtmnFormAreaIn  IN  = new BsDtmnFormAreaIn();
	public BsDtmnFormAreaOut OUT = new BsDtmnFormAreaOut();
	
	public class BsDtmnFormAreaIn {
		public long   ctmn;
		public long   iden;
		public String desc;
		public String acti;
		public long   orde;
		public String path;
		public String icon;
	}
	
	public class BsDtmnFormAreaOut {
		public Dtmn dtmn;
	}
}
