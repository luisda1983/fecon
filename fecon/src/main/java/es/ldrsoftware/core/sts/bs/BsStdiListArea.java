package es.ldrsoftware.core.sts.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.sts.entity.Stdi;

public class BsStdiListArea extends BaseBSArea {

	public BsStdiListAreaIn  IN  = new BsStdiListAreaIn();
	public BsStdiListAreaOut OUT = new BsStdiListAreaOut();
	
	public class BsStdiListAreaIn {
		public String tipo;
		public    int fech;
		public    int fein;
		public    int fefi;
	}
	
	public class BsStdiListAreaOut {
		public List<Stdi> stdiList;
	}
}
