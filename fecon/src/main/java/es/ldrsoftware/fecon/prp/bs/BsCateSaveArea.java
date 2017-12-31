package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Cate;

public class BsCateSaveArea extends BaseBSArea {

	public BsCateSaveAreaIn  IN  = new BsCateSaveAreaIn();
	public BsCateSaveAreaOut OUT = new BsCateSaveAreaOut();
	
	public class BsCateSaveAreaIn {
		public Cate cate;
	}
	
	public class BsCateSaveAreaOut {
		public Cate cate;
	}
}
