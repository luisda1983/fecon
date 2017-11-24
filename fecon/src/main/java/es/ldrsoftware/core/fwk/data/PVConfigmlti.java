package es.ldrsoftware.core.fwk.data;

public class PVConfigmlti extends PV {

	public String mlti;
	   
	public void parse(String valo) {
		String s = extend(valo, 1);
				
		mlti = s.substring(0, 1);
	}
	
	public String format() {
		return format(mlti, 1);
	}
}