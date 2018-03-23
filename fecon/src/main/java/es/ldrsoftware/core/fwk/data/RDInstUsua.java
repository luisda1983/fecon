package es.ldrsoftware.core.fwk.data;

public class RDInstUsua extends RD {

	public String perf;
	   
	public void parse(String data) {
		String s = extend(data, 3);
				
		perf = s.substring(0, 3);
	}
	
	public String format() {
		return format(perf, 3);
	}
}