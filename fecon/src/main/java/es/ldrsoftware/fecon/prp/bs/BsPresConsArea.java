package es.ldrsoftware.fecon.prp.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.prp.entity.Pres;

public class BsPresConsArea extends BaseBSArea {

	public BsPresConsAreaIn  IN  = new BsPresConsAreaIn();
	public BsPresConsAreaOut OUT = new BsPresConsAreaOut();
	
	public class BsPresConsAreaIn {
		public int anua;
		public int mesp;
	}
	
	public class BsPresConsAreaOut {
		public List<Pres> presList;
		public List<Pres> presAnuaList;
		public List<Cuen> cuenList;
		public double     impoNpre;
	}
}
