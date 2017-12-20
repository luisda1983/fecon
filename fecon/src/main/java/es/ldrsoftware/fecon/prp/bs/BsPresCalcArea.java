package es.ldrsoftware.fecon.prp.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Pres;

public class BsPresCalcArea extends BaseBSArea {

	public BsPresCalcAreaIn  IN  = new BsPresCalcAreaIn();
	public BsPresCalcAreaOut OUT = new BsPresCalcAreaOut();
	
	public class BsPresCalcAreaIn {
		public long fech;
		public long cate;
		public long conc;
	}
	
	public class BsPresCalcAreaOut {
		public Pres pres;
	}
}
