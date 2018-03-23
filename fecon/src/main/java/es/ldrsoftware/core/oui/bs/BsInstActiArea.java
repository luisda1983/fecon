package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Inst;

public class BsInstActiArea extends BaseBSArea {

	public BsInstActiAreaIn  IN  = new BsInstActiAreaIn();
	public BsInstActiAreaOut OUT = new BsInstActiAreaOut();
	
	public class BsInstActiAreaIn {
		public long iden;
	}
	
	public class BsInstActiAreaOut {
		public Inst inst;
	}
}
