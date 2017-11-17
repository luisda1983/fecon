package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Hcon;

public class BsHconSaveArea extends BaseBSArea {

	public BsHconSaveAreaIn  IN  = new BsHconSaveAreaIn();
	public BsHconSaveAreaOut OUT = new BsHconSaveAreaOut();
	
	public class BsHconSaveAreaIn {
		public Hcon hcon;
	}
	
	public class BsHconSaveAreaOut {
		public Hcon hcon;
	}
}
