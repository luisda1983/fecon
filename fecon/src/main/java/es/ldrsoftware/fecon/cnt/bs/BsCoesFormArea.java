package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Coes;

public class BsCoesFormArea extends BaseBSArea {

	public BsCoesFormAreaIn  IN  = new BsCoesFormAreaIn();
	public BsCoesFormAreaOut OUT = new BsCoesFormAreaOut();
	
	public class BsCoesFormAreaIn {
		public long   iden;
		public String tipo;
		public String desc;
		public String favo;
		//public long   trad;
		public long   cate;
		public long   conc;
	}
	
	public class BsCoesFormAreaOut {
		public Coes coes;
	}
}
