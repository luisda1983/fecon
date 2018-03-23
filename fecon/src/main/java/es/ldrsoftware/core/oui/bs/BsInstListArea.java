package es.ldrsoftware.core.oui.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Inst;

public class BsInstListArea extends BaseBSArea {

	public BsInstListAreaIn  IN  = new BsInstListAreaIn();
	public BsInstListAreaOut OUT = new BsInstListAreaOut();
	
	public class BsInstListAreaIn {
		public String tipo;
		public String esta;
	}
	
	public class BsInstListAreaOut {
		public List<Inst> instList;
	}
}
