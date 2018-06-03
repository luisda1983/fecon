package es.ldrsoftware.core.sts.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.sts.entity.Stst;

public class BsStstListArea extends BaseBSArea {

	public BsStstListAreaIn  IN  = new BsStstListAreaIn();
	public BsStstListAreaOut OUT = new BsStstListAreaOut();
	
	public class BsStstListAreaIn {
		public String tipo;
		public    int fech;
	}
	
	public class BsStstListAreaOut {
		public List<Stst> ststList;
	}
}
