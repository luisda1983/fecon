package es.ldrsoftware.core.btc.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Btch;

public class BsBtchListArea extends BaseBSArea {

	public BsBtchListAreaIn  IN  = new BsBtchListAreaIn();
	public BsBtchListAreaOut OUT = new BsBtchListAreaOut();
	
	public class BsBtchListAreaIn {
		public String tipo;
	}
	
	public class BsBtchListAreaOut {
		public List<Btch> btchList;
	}
}
