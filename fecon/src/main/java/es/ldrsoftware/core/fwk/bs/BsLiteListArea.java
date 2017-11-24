package es.ldrsoftware.core.fwk.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Lite;

public class BsLiteListArea extends BaseBSArea {

	public BsLiteListAreaIn  IN  = new BsLiteListAreaIn();
	public BsLiteListAreaOut OUT = new BsLiteListAreaOut();
	
	public class BsLiteListAreaIn {
		public String tbla;
	}
	
	public class BsLiteListAreaOut {
		public List<Lite> liteList;
	}
}
