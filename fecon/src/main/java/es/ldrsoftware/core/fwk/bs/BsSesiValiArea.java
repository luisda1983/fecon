package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Sesi;

public class BsSesiValiArea extends BaseBSArea {

	public BsSesiValiAreaIn  IN  = new BsSesiValiAreaIn();
	public BsSesiValiAreaOut OUT = new BsSesiValiAreaOut();
	
	public class BsSesiValiAreaIn {
		public long   iden;
		public long   clav;
	}
	
	public class BsSesiValiAreaOut {
		public Sesi sesi;
	}
}
