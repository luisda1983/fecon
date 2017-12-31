package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Cate;

public class BsCateGetkArea extends BaseBSArea {

	public BsCateGetkAreaIn  IN  = new BsCateGetkAreaIn();
	public BsCateGetkAreaOut OUT = new BsCateGetkAreaOut();
	
	public class BsCateGetkAreaIn {
		public long iden;
	}
	
	public class BsCateGetkAreaOut {
		public Cate cate;
	}
}
