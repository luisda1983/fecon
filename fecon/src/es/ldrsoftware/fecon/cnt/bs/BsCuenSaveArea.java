package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;

public class BsCuenSaveArea extends BaseBSArea {

	public BsCuenSaveAreaIn  IN  = new BsCuenSaveAreaIn();
	public BsCuenSaveAreaOut OUT = new BsCuenSaveAreaOut();
	
	public class BsCuenSaveAreaIn {
		public Cuen cuen;
	}
	
	public class BsCuenSaveAreaOut {
		public Cuen cuen;
	}
}
