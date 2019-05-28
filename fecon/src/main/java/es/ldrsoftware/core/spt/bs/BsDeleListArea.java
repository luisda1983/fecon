package es.ldrsoftware.core.spt.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Dele;

public class BsDeleListArea extends BaseBSArea {

	public BsDeleListAreaIn  IN  = new BsDeleListAreaIn();
	public BsDeleListAreaOut OUT = new BsDeleListAreaOut();
	
	public class BsDeleListAreaIn {
		public String domi;
	}
	
	public class BsDeleListAreaOut {
		public List<Dele> deleList;
	}
}
