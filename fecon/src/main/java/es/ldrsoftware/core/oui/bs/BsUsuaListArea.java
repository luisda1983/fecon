package es.ldrsoftware.core.oui.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Usua;

public class BsUsuaListArea extends BaseBSArea {

	public BsUsuaListAreaIn  IN  = new BsUsuaListAreaIn();
	public BsUsuaListAreaOut OUT = new BsUsuaListAreaOut();
	
	public class BsUsuaListAreaIn {
		public String tipo;
		public long   inst;
	}
	
	public class BsUsuaListAreaOut {
		public List<Usua> usuaList;
	}
}
