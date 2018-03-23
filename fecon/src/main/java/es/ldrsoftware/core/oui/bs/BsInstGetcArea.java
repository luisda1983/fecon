package es.ldrsoftware.core.oui.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.oui.entity.Inst;

public class BsInstGetcArea extends BaseBSArea {

	public BsInstGetcAreaIn  IN  = new BsInstGetcAreaIn();
	public BsInstGetcAreaOut OUT = new BsInstGetcAreaOut();
	
	public class BsInstGetcAreaIn {
		public String codi;
	}
	
	public class BsInstGetcAreaOut {
		public Inst inst;
	}
}
