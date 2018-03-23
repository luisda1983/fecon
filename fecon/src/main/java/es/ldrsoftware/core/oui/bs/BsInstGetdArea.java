package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Inst;

public class BsInstGetdArea extends BaseBSArea {

	public BsInstGetdAreaIn  IN  = new BsInstGetdAreaIn();
	public BsInstGetdAreaOut OUT = new BsInstGetdAreaOut();
	
	public class BsInstGetdAreaIn {
		public String desc;
	}
	
	public class BsInstGetdAreaOut {
		public Inst inst;
	}
}
