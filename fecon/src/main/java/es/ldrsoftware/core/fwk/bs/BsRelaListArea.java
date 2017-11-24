package es.ldrsoftware.core.fwk.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Rela;

public class BsRelaListArea extends BaseBSArea {

	public BsRelaListAreaIn  IN  = new BsRelaListAreaIn();
	public BsRelaListAreaOut OUT = new BsRelaListAreaOut();
	
	public class BsRelaListAreaIn {
		public String rela;
		public long   clnu;
		public String clca;
	}
	
	public class BsRelaListAreaOut {
		public List<Rela> relaList;
	}
}
