package es.ldrsoftware.core.fwk.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Sesi;

public class BsSesiListArea extends BaseBSArea {

	public BsSesiListAreaIn  IN  = new BsSesiListAreaIn();
	public BsSesiListAreaOut OUT = new BsSesiListAreaOut();
	
	public class BsSesiListAreaIn {
		public String esta;
	}
	
	public class BsSesiListAreaOut {
		public List<Sesi> sesiList;
	}
}
