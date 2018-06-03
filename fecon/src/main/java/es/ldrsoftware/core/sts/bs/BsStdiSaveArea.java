package es.ldrsoftware.core.sts.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.sts.entity.Stdi;

public class BsStdiSaveArea extends BaseBSArea {

	public BsStdiSaveAreaIn  IN  = new BsStdiSaveAreaIn();
	public BsStdiSaveAreaOut OUT = new BsStdiSaveAreaOut();
	
	public class BsStdiSaveAreaIn {
		public Stdi stdi;
	}
	
	public class BsStdiSaveAreaOut {
		public Stdi stdi;
	}
}
