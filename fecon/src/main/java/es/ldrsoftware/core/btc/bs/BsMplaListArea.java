package es.ldrsoftware.core.btc.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Mpla;

public class BsMplaListArea extends BaseBSArea {

	public BsMplaListAreaIn  IN  = new BsMplaListAreaIn();
	public BsMplaListAreaOut OUT = new BsMplaListAreaOut();
	
	public class BsMplaListAreaIn {
	}
	
	public class BsMplaListAreaOut {
		public List<Mpla> mplaList;
	}
}
