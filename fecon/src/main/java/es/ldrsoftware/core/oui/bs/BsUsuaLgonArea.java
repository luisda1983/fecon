package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Usua;

public class BsUsuaLgonArea extends BaseBSArea {

	public BsUsuaLgonAreaIn  IN  = new BsUsuaLgonAreaIn();
	public BsUsuaLgonAreaOut OUT = new BsUsuaLgonAreaOut();
	
	public class BsUsuaLgonAreaIn {
		public String iden;
		public String pass;
	}
	
	public class BsUsuaLgonAreaOut {
		public long sesi;
		public Usua usua;
	}
}
