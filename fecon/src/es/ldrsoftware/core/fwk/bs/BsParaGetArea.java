package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Para;

public class BsParaGetArea extends BaseBSArea {

	public BsParaGetAreaIn  IN  = new BsParaGetAreaIn();
	public BsParaGetAreaOut OUT = new BsParaGetAreaOut();
	
	public class BsParaGetAreaIn {
		public String tbla;
		public String clav;
	}
	
	public class BsParaGetAreaOut {
		public Para para;
	}
}
