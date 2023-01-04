package es.ldrsoftware.fecon.cnt.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Trad;

public class BsTradListArea extends BaseBSArea {

	public BsTradListAreaIn  IN  = new BsTradListAreaIn();
	public BsTradListAreaOut OUT = new BsTradListAreaOut();
	
	public class BsTradListAreaIn {
		
	}
	
	public class BsTradListAreaOut {
		public List<Trad> tradList;
	}
}
