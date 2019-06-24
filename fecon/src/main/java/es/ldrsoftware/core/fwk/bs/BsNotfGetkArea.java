package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Notf;

public class BsNotfGetkArea extends BaseBSArea {

	public BsNotfGetkAreaIn  IN  = new BsNotfGetkAreaIn();
	public BsNotfGetkAreaOut OUT = new BsNotfGetkAreaOut();
	
	public class BsNotfGetkAreaIn {
		public String iden;
	}
	
	public class BsNotfGetkAreaOut {
		public Notf notf;
	}
}
