package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;

public class BsCuenCuadArea extends BaseBSArea {

	public BsCuenCuadAreaIn  IN  = new BsCuenCuadAreaIn();
	public BsCuenCuadAreaOut OUT = new BsCuenCuadAreaOut();
	
	public class BsCuenCuadAreaIn {
		public long   cuen;
		public long   cate;
		public long   conc;
		public double impo;
		public double sald;
	}
	
	public class BsCuenCuadAreaOut {
		public Cuen cuen;
	}
}
