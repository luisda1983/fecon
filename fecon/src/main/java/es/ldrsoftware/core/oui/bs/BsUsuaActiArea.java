package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Usua;

public class BsUsuaActiArea extends BaseBSArea {

	public BsUsuaActiAreaIn  IN  = new BsUsuaActiAreaIn();
	public BsUsuaActiAreaOut OUT = new BsUsuaActiAreaOut();
	
	public class BsUsuaActiAreaIn {
		public String iden;
		public long   inst;
	}
	
	public class BsUsuaActiAreaOut {
		public Usua usua;
	}
}
