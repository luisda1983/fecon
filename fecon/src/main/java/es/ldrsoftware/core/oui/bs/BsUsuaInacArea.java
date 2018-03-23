package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Usua;

public class BsUsuaInacArea extends BaseBSArea {

	public BsUsuaInacAreaIn  IN  = new BsUsuaInacAreaIn();
	public BsUsuaInacAreaOut OUT = new BsUsuaInacAreaOut();
	
	public class BsUsuaInacAreaIn {
		public String iden;
		public long   inst;
	}
	
	public class BsUsuaInacAreaOut {
		public Usua usua;
	}
}
