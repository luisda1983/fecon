package es.ldrsoftware.core.btc.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Ejec;

public class BsEjecGetListArea extends BaseBSArea {

	public BsEjecGetListAreaIn  IN  = new BsEjecGetListAreaIn();
	public BsEjecGetListAreaOut OUT = new BsEjecGetListAreaOut();
	
	public class BsEjecGetListAreaIn {
		public    int fech;
		public String esta;
	}
	
	public class BsEjecGetListAreaOut {
		public List<Ejec> ejecList;
	}
}
