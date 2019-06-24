package es.ldrsoftware.core.fwk.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Notf;

public class BsNotfListArea extends BaseBSArea {

	public BsNotfListAreaIn  IN  = new BsNotfListAreaIn();
	public BsNotfListAreaOut OUT = new BsNotfListAreaOut();
	
	public class BsNotfListAreaIn {
		public String apli;
	}
	
	public class BsNotfListAreaOut {
		public List<Notf> notfList;
	}
}
