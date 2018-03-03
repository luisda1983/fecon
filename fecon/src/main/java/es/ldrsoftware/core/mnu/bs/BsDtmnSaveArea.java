package es.ldrsoftware.core.mnu.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.mnu.entity.Dtmn;

public class BsDtmnSaveArea extends BaseBSArea {

	public BsDtmnSaveAreaIn  IN  = new BsDtmnSaveAreaIn();
	public BsDtmnSaveAreaOut OUT = new BsDtmnSaveAreaOut();
	
	public class BsDtmnSaveAreaIn {
		public Dtmn dtmn;
	}
	
	public class BsDtmnSaveAreaOut {
		public Dtmn dtmn;
	}
}
