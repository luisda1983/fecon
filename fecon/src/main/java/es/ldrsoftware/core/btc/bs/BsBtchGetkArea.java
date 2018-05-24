package es.ldrsoftware.core.btc.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Btch;

public class BsBtchGetkArea extends BaseBSArea {

	public BsBtchGetkAreaIn  IN  = new BsBtchGetkAreaIn();
	public BsBtchGetkAreaOut OUT = new BsBtchGetkAreaOut();
	
	public class BsBtchGetkAreaIn {
		public String iden;
	}
	
	public class BsBtchGetkAreaOut {
		public Btch btch;
	}
}
