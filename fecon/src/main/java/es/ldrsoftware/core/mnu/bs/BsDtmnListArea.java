package es.ldrsoftware.core.mnu.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.mnu.entity.Dtmn;

public class BsDtmnListArea extends BaseBSArea {

	public BsDtmnListAreaIn  IN  = new BsDtmnListAreaIn();
	public BsDtmnListAreaOut OUT = new BsDtmnListAreaOut();
	
	public class BsDtmnListAreaIn {
		public String tipo;
		public long   ctmn;
		public String acti;
	}
	
	public class BsDtmnListAreaOut {
		public List<Dtmn> dtmnList;
	}
}
