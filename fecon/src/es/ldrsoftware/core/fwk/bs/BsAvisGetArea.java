package es.ldrsoftware.core.fwk.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Avis;

public class BsAvisGetArea extends BaseBSArea {

	public BsAvisGetAreaIn  IN  = new BsAvisGetAreaIn();
	public BsAvisGetAreaOut OUT = new BsAvisGetAreaOut();
	
	public class BsAvisGetAreaIn {
	}
	
	public class BsAvisGetAreaOut {
		public List<Avis> avisList;
	}
}
