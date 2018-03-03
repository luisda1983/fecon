package es.ldrsoftware.core.mnu.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.mnu.entity.Ctmn;

public class BsCtmnListArea extends BaseBSArea {

	public BsCtmnListAreaIn  IN  = new BsCtmnListAreaIn();
	public BsCtmnListAreaOut OUT = new BsCtmnListAreaOut();
	
	public class BsCtmnListAreaIn {
		public String tipo;
		public String perf;
		public String acti;
	}
	
	public class BsCtmnListAreaOut {
		public List<Ctmn> ctmnList;
	}
}
