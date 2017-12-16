package es.ldrsoftware.fecon.cnt.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Hcon;

public class BsHconListArea extends BaseBSArea {

	public BsHconListAreaIn  IN  = new BsHconListAreaIn();
	public BsHconListAreaOut OUT = new BsHconListAreaOut();
	
	public class BsHconListAreaIn {
		public String tipo;
		public int    anua;
		public int    mesh;
		public long   cate;
		public long   conc;
	}
	
	public class BsHconListAreaOut {
		public List<Hcon> hconList;
	}
}
