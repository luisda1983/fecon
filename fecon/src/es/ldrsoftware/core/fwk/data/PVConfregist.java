package es.ldrsoftware.core.fwk.data;

public class PVConfregist extends PV {

	public String esta;
	   
	public void parse(String valo) {
		String s = extend(valo, 1);
				
		esta = s.substring(0, 1);
	}
	
	public String format() {
		return format(esta, 1);
	}
}