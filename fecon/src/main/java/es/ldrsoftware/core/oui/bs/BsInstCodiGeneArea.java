package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;

public class BsInstCodiGeneArea extends BaseBSArea {

	public BsInstCodiGeneAreaIn  IN  = new BsInstCodiGeneAreaIn();
	public BsInstCodiGeneAreaOut OUT = new BsInstCodiGeneAreaOut();
	
	public class BsInstCodiGeneAreaIn {
		
	}
	
	public class BsInstCodiGeneAreaOut {
		public String codi;
	}
}
