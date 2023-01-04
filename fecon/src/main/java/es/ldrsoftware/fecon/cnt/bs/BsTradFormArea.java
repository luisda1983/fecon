package es.ldrsoftware.fecon.cnt.bs;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Trad;

public class BsTradFormArea extends BaseBSArea {

	public BsTradFormAreaIn  IN  = new BsTradFormAreaIn();
	public BsTradFormAreaOut OUT = new BsTradFormAreaOut();
	
	public class BsTradFormAreaIn {
		public long   iden;
		public String nomb;
		public String tip1;
		public long   dom1;
		public String ide1;
		public String obl1;
		public String tip2;
		public long   dom2;
		public String ide2;
		public String obl2;
		public String tip3;
		public long   dom3;
		public String ide3;
		public String obl3;
		public String desc;
	}
	
	public class BsTradFormAreaOut {
		public Trad trad;
	}
}
