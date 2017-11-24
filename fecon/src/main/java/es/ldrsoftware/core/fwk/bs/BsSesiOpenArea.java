package es.ldrsoftware.core.fwk.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Sesi;

public class BsSesiOpenArea extends BaseBSArea {

	public BsSesiOpenAreaIn  IN  = new BsSesiOpenAreaIn();
	public BsSesiOpenAreaOut OUT = new BsSesiOpenAreaOut();
	
	public class BsSesiOpenAreaIn {
		public String usua;
		public String perf;
		public long   inst;
	}
	
	public class BsSesiOpenAreaOut {
		public Sesi sesi;
	}
}
