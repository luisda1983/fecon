package es.ldrsoftware.core.btc.bs;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Ejec;

public class BsEjecListArea extends BaseBSArea {

	public BsEjecListAreaIn  IN  = new BsEjecListAreaIn();
	public BsEjecListAreaOut OUT = new BsEjecListAreaOut();
	
	public class BsEjecListAreaIn {
		public String tipo;
		public    int fech;
		public    int hora;
		public String esta;
	}
	
	public class BsEjecListAreaOut {
		public List<Ejec> ejecList;
	}
}
