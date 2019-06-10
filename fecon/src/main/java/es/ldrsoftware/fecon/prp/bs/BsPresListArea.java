package es.ldrsoftware.fecon.prp.bs;

import java.util.List;
import java.util.Map;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Pres;

public class BsPresListArea extends BaseBSArea {

	public final static String LIST_TIPO_RESUMEN   = "LT01";
	public final static String LIST_TIPO_ANUAL     = "LT02";
	public final static String LIST_TIPO_MENSUAL   = "LT03";
	public final static String LIST_TIPO_CONC_ANUA = "LT04";
	public final static String LIST_TIPO_MENSUAL_PRESUP = "LT05";
	public final static String LIST_TIPO_PART_ANUALES   = "LT06";
	
	public BsPresListAreaIn  IN  = new BsPresListAreaIn();
	public BsPresListAreaOut OUT = new BsPresListAreaOut();
	
	public class BsPresListAreaIn {
		public String tipo;
		public    int anua;
		public    int mesp;
		public   long cate;
	}
	
	public class BsPresListAreaOut {
		public List<Pres> presList;
		public List<Pres> presListAnua;
		public Map<Long, List<Pres>> presListMap;
	}
}
