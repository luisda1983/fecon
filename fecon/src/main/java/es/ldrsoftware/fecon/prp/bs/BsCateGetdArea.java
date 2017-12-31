package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Cate;

public class BsCateGetdArea extends BaseBSArea {

	public BsCateGetdAreaIn  IN  = new BsCateGetdAreaIn();
	public BsCateGetdAreaOut OUT = new BsCateGetdAreaOut();
	
	public class BsCateGetdAreaIn {
		public String desl;
		public String desc;
	}
	
	public class BsCateGetdAreaOut {
		public Cate cateDesl;
		public Cate cateDesc;
	}
}
