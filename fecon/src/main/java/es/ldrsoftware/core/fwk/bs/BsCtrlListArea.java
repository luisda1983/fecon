package es.ldrsoftware.core.fwk.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Ctrl;

public class BsCtrlListArea extends BaseBSArea {

	public BsCtrlListAreaIn  IN  = new BsCtrlListAreaIn();
	public BsCtrlListAreaOut OUT = new BsCtrlListAreaOut();
	
	public class BsCtrlListAreaIn {
		public String tipo;
	}
	
	public class BsCtrlListAreaOut {
		public List<Ctrl> ctrlList;
	}
}
