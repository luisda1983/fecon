package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Coes;

public class BsCoesSaveArea extends BaseBSArea {

	public BsCoesSaveAreaIn  IN  = new BsCoesSaveAreaIn();
	public BsCoesSaveAreaOut OUT = new BsCoesSaveAreaOut();
	
	public class BsCoesSaveAreaIn {
		public Coes coes;
	}
	
	public class BsCoesSaveAreaOut {
		public Coes coes;
	}
}
