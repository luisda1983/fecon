package es.ldrsoftware.core.spt.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Domi;

public class BsDomiListArea extends BaseBSArea {

	public BsDomiListAreaIn  IN  = new BsDomiListAreaIn();
	public BsDomiListAreaOut OUT = new BsDomiListAreaOut();
	
	public class BsDomiListAreaIn {
	}
	
	public class BsDomiListAreaOut {
		public List<Domi> domiList;
	}
}
