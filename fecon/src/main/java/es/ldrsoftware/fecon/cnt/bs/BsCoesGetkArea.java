package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Coes;

public class BsCoesGetkArea extends BaseBSArea {

	public BsCoesGetkAreaIn  IN  = new BsCoesGetkAreaIn();
	public BsCoesGetkAreaOut OUT = new BsCoesGetkAreaOut();
	
	public class BsCoesGetkAreaIn {
		public long iden;
	}
	
	public class BsCoesGetkAreaOut {
		public Coes coes;
	}
}
