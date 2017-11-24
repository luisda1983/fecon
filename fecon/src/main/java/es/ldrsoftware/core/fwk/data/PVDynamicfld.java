package es.ldrsoftware.core.fwk.data;

public class PVDynamicfld extends PV {

	public String show;
	public String name;
	   
	public void parse(String valo) {
		String s = extend(valo, 31);
				
		show = s.substring(0, 1);
		
		name = s.substring(1, 31).trim();
	}
	
	public String format() {
		return format(show, 1) +
			   format(name, 30);
	}
}