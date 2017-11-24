package es.ldrsoftware.core.btc.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Btch;

public class BsBtchGetPlanArea extends BaseBSArea {

	public BsBtchGetPlanAreaIn  IN  = new BsBtchGetPlanAreaIn();
	public BsBtchGetPlanAreaOut OUT = new BsBtchGetPlanAreaOut();
	
	public class BsBtchGetPlanAreaIn {
	}
	
	public class BsBtchGetPlanAreaOut {
		public List<Btch> btchList;
	}
}
