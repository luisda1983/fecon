package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Conc;

public class BsConcFormArea extends BaseBSArea {

	public BsConcFormAreaIn  IN  = new BsConcFormAreaIn();
	public BsConcFormAreaOut OUT = new BsConcFormAreaOut();
	
	public class BsConcFormAreaIn {
		public long   iden;
		public long   cate;
		public String desl;
		public String desc;
	}
	
	public class BsConcFormAreaOut {
		public Conc conc;
	}
}
