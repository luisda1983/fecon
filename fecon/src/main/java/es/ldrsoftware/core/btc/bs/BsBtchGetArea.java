package es.ldrsoftware.core.btc.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Btch;

public class BsBtchGetArea extends BaseBSArea {

	public BsBtchGetAreaIn  IN  = new BsBtchGetAreaIn();
	public BsBtchGetAreaOut OUT = new BsBtchGetAreaOut();
	
	public class BsBtchGetAreaIn {
		public String iden;
	}
	
	public class BsBtchGetAreaOut {
		public Btch btch;
	}
}
