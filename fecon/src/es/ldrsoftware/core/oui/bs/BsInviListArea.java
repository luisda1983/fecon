package es.ldrsoftware.core.oui.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Invi;

public class BsInviListArea extends BaseBSArea {

	public BsInviListAreaIn  IN  = new BsInviListAreaIn();
	public BsInviListAreaOut OUT = new BsInviListAreaOut();
	
	public class BsInviListAreaIn {
		public String tipo;
		public long   inst;
		public String esta;
		public String mail;
	}
	
	public class BsInviListAreaOut {
		public List<Invi> inviList;
	}
}
