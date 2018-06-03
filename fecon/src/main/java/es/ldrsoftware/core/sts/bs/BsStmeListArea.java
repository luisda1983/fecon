package es.ldrsoftware.core.sts.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.sts.entity.Stme;

public class BsStmeListArea extends BaseBSArea {

	public BsStmeListAreaIn  IN  = new BsStmeListAreaIn();
	public BsStmeListAreaOut OUT = new BsStmeListAreaOut();
	
	public class BsStmeListAreaIn {
		public String tipo;
		public    int anyo;
		public    int mess;
	}
	
	public class BsStmeListAreaOut {
		public List<Stme> stmeList;
	}
}
