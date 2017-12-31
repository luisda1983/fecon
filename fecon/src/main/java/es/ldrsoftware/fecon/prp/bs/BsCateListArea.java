package es.ldrsoftware.fecon.prp.bs;

import java.util.List;
import java.util.Map;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Cate;

public class BsCateListArea extends BaseBSArea {

	public BsCateListAreaIn  IN  = new BsCateListAreaIn();
	public BsCateListAreaOut OUT = new BsCateListAreaOut();
	
	public class BsCateListAreaIn {
	}
	
	public class BsCateListAreaOut {
		public List<Cate> cateList;
		public Map<Long, Cate> cateListMap;
	}
}
