package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Pres;

public class BsPresSaveArea extends BaseBSArea {

	public BsPresSaveAreaIn  IN  = new BsPresSaveAreaIn();
	public BsPresSaveAreaOut OUT = new BsPresSaveAreaOut();
	
	public class BsPresSaveAreaIn {
		public Pres pres;
	}
	
	public class BsPresSaveAreaOut {
		public Pres pres;
	}
}
