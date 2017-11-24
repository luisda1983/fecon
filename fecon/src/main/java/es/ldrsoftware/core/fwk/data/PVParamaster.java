package es.ldrsoftware.core.fwk.data;

public class PVParamaster extends PV {

	public String cons;
	
	public void parse(String valo) {
		String s = extend(valo, 1);

		cons = s.substring(0, 1);
		if (!"N".equals(cons) && !"S".equals(cons)) {
			cons = "N";
		}
	}
	
	public String format() {
		return format(cons, 1);
	}
	
}