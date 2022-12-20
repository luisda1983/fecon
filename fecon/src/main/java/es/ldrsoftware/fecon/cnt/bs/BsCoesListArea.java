package es.ldrsoftware.fecon.cnt.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Coes;

public class BsCoesListArea extends BaseBSArea {

	public BsCoesListAreaIn  IN  = new BsCoesListAreaIn();
	public BsCoesListAreaOut OUT = new BsCoesListAreaOut();
	
	public class BsCoesListAreaIn {
		
	}
	
	public class BsCoesListAreaOut {
		public List<Coes> coesList;
	}
}
