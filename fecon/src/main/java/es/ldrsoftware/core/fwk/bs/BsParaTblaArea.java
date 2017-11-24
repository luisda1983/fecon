package es.ldrsoftware.core.fwk.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Para;

public class BsParaTblaArea extends BaseBSArea {

	public BsParaGetAreaIn  IN  = new BsParaGetAreaIn();
	public BsParaGetAreaOut OUT = new BsParaGetAreaOut();
	
	public class BsParaGetAreaIn {
		public String tbla;
	}
	
	public class BsParaGetAreaOut {
		public List<Para> paraList;
	}
}
