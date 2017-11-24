package es.ldrsoftware.core.fwk.data;

public abstract class PV {

	public abstract void parse(String valo);
	public abstract String format();
	
	public String extend(String s, int length) {
		while (s.length() < length) {
			s = s + " ";
		}
		return s;
	}
	
	public int toInt(String s) {
		try {
			Integer i = new Integer(s);
			return i.intValue();
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public String format(String s, int length) {
		if (s.length() > length) {
			return s.substring(0,  length - 1);
		} else {
			return extend(s, length);
		}
	}
	
	public String format(int i, int length) {
		String s = new Integer(i).toString();
		
		if (s.length() > length) {
			while(s.length() > length) {
				s = s.substring(1);
			}
			return s;
		} else {
			while (s.length() < length) {
				s = "0" + s;
			}
			return s;
		}
	}
}