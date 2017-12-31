package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Cate;

public class BsCateFormArea extends BaseBSArea {

	public BsCateFormAreaIn  IN  = new BsCateFormAreaIn();
	public BsCateFormAreaOut OUT = new BsCateFormAreaOut();
	
	public class BsCateFormAreaIn {
		public long   iden;
		public String desl;
		public String desc;
		public String pres;
	}
	
	public class BsCateFormAreaOut {
		public Cate cate;
	}
}
