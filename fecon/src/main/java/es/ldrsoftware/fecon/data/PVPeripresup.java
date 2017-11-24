package es.ldrsoftware.fecon.data;

import es.ldrsoftware.core.fwk.data.PV;

public class PVPeripresup extends PV {

	public    int anin;
	public    int msin;
	public    int anac;
	public    int msac;
	   
	public void parse(String valo) {
		String s = extend(valo, 12);
				
		String sAnin = s.substring(0, 4);
		anin = toInt(sAnin);
		
		String sMsin = s.substring(4, 6);
		msin = toInt(sMsin);
		
		String sAnac = s.substring(6, 10);
		anac = toInt(sAnac);
		
		String sMsac = s.substring(10, 12);
		msac = toInt(sMsac);
	}
	
	public String format() {
		return format(anin, 4) + 
			   format(msin, 2) + 
			   format(anac, 4) +
			   format(msac, 2);
	}
	
}