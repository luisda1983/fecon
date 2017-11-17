package es.ldrsoftware.fecon.cnt.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;

public class BsCuenListArea extends BaseBSArea {

	public BsCuenListAreaIn  IN  = new BsCuenListAreaIn();
	public BsCuenListAreaOut OUT = new BsCuenListAreaOut();
	
	public class BsCuenListAreaIn {
		
	}
	
	public class BsCuenListAreaOut {
		public List<Cuen> cuenList;
	}
}
