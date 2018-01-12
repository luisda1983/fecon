package es.ldrsoftware.fecon.prp.bs;

import java.util.List;
import java.util.Map;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Conc;

public class BsConcListArea extends BaseBSArea {

	public final static String LIST_TIPO_CATE = "LT-01";
	public final static String LIST_TIPO_FULL = "LT-02";
	
	public BsConcListAreaIn  IN  = new BsConcListAreaIn();
	public BsConcListAreaOut OUT = new BsConcListAreaOut();
	
	public class BsConcListAreaIn {
		public String tipo;
		public long cate;
	}
	
	public class BsConcListAreaOut {
		public List<Conc> concList;
		public Map<Long, Conc> concListMap;
	}
}
