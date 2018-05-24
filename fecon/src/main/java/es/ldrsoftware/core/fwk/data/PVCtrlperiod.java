package es.ldrsoftware.core.fwk.data;

public class PVCtrlperiod extends PV {

	public    int feul;
	public    int houl;
	public    int fepr;
	public    int hopr;
	public    int fbtc;
	   
	public void parse(String valo) {
		String s = extend(valo, 36);
				
		String sFeul = s.substring(0, 8);
		feul = toInt(sFeul);
		
		String sHoul = s.substring(8, 14);
		houl = toInt(sHoul);
		
		String sFepr = s.substring(14, 22);
		fepr = toInt(sFepr);
		
		String sHopr = s.substring(22, 28);
		hopr = toInt(sHopr);
		
		String sFbtc = s.substring(28, 36);
		fbtc = toInt(sFbtc);
	}
	
	public String format() {
		return format(feul, 8) + 
			   format(houl, 6) + 
			   format(fepr, 8) +
			   format(hopr, 6) +
			   format(fbtc, 8);
	}
}