package es.ldrsoftware.core.mnu.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.mnu.entity.Ctmn;

public class BsMenuGetArea extends BaseBSArea {

	public BsMenuGetAreaIn  IN  = new BsMenuGetAreaIn();
	public BsMenuGetAreaOut OUT = new BsMenuGetAreaOut();
	
	public class BsMenuGetAreaIn {
		public String perf;
	}
	
	public class BsMenuGetAreaOut {
		public List<Ctmn> ctmnList;
	}
}
