package es.ldrsoftware.core.fwk.data;

public class PVFechperiod extends PV {

	public String avre;
	public    int YYYY;
	public    int MM;
	public    int DD;
	public    int hh;
	public    int mm;
	public    int ss;
	   
	public void parse(String valo) {
		String s = extend(valo, 15);
		
		avre = s.substring(0, 1);
		if (!"+".equals(avre) && !"-".equals(avre)) {
			avre = "+";
		}
		
		String sYYYY = s.substring(1, 3);
		YYYY = toInt(sYYYY);
		
		String sMM = s.substring(3, 5);
		MM = toInt(sMM);
		
		String sDD = s.substring(5, 9);
		DD = toInt(sDD);
		
		String shh = s.substring(9, 11);
		hh = toInt(shh);
		
		String smm = s.substring(11, 13);
		mm = toInt(smm);
		
		String sss = s.substring(13, 15);
		ss = toInt(sss);
	}
	
	public String format() {
		return format(avre, 1) + 
			   format(YYYY, 2) + 
			   format(MM, 2)   +
			   format(DD, 4)   +
			   format(hh, 2)   + 
			   format(mm, 2)   +
			   format(ss, 2);
	}
	
}