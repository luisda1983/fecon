package es.ldrsoftware.core.mnu.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.mnu.entity.Ctmn;

public class BsMenuMakeArea extends BaseBSArea {

	public BsMenuMakeAreaIn  IN  = new BsMenuMakeAreaIn();
	public BsMenuMakeAreaOut OUT = new BsMenuMakeAreaOut();
	
	public class BsMenuMakeAreaIn {
		public String perf;
	}
	
	public class BsMenuMakeAreaOut {
		public List<Ctmn> ctmnList;
	}
}
