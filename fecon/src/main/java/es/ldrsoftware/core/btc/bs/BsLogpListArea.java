package es.ldrsoftware.core.btc.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Logp;

public class BsLogpListArea extends BaseBSArea {

	public BsLogpListAreaIn  IN  = new BsLogpListAreaIn();
	public BsLogpListAreaOut OUT = new BsLogpListAreaOut();
	
	public class BsLogpListAreaIn {
		public String tipo;
		public String iden;
		public int    fech;
		public int    hora;
	}
	
	public class BsLogpListAreaOut {
		public List<Logp> logpList;
	}
}
