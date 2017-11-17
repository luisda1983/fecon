package ldrsoftware.app.util;

public class Fmt {
	
	public static int stringToInt(String s) {
		try {
			return new Integer(s).intValue();
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public static int dateStringToInt(String date) {
		int dia = 0;
		int mes = 0;
		int anyo = 0;
		String s = date.toString();
		int i = date.indexOf("/");
		if (i < 0) {
			return 0;
		} 
		dia = new Integer(s.substring(0, i)).intValue();
		s = s.substring(i + 1);
		
		i = date.indexOf("/");
		if (i < 0) {
			return 0;
		}
		mes = new Integer(s.substring(0, i)).intValue();
		s = s.substring(i + 1);
		
		anyo = new Integer(s).intValue();
		return anyo * 10000 + mes * 100 + dia;
	}
	
	public static int timeStringToInt(String time) {
		int hora = 0;
		int minuto = 0;
		int segundo = 0;
		String s = time.toString();
		int i = s.indexOf(":");
		if (i < 0) {
			return 0;
		}
		hora = new Integer(s.substring(0, i)).intValue();
		s = s.substring(i + 1);
		
		i = s.indexOf(":");
		if (i < 0) {
			return 0;
		}
		minuto = new Integer(s.substring(0, i)).intValue();
		s = s.substring(i + 1);
		
		segundo = new Integer(s.substring(0, i)).intValue();
		
		return hora * 10000 + minuto * 100 + segundo;
	}

	public static float stringToFloat(String sFloat) {
		float f = 0;
		String s = sFloat.toString();
		int i = s.indexOf(",");
		int entero = new Integer(s.substring(0, i)).intValue();
		s = s.substring(i + 1);
		int posDecimal = s.length();
		int decimal = new Integer(s).intValue();
		f = f + entero;
		f = f + decimal / (10 * posDecimal);
		return f;
	}

	
	public static String getDateFmt(int date) {
		String sDate = (new Integer(date)).toString();
		while (sDate.length() < 8 ) {
			sDate = "0" + sDate;
		}
		return sDate.substring(6, 8) + "/" + sDate.substring(4, 6) + "/" + sDate.substring(0, 4);
	}

	public static String getTimeFmt(int time) {
		String sTime = (new Integer(time)).toString();
		while (sTime.length() < 6) {
			sTime = "0" + sTime;
		}
		return sTime.substring(0, 2) + ":" + sTime.substring(2, 4) + ":" + sTime.substring(4, 6);
	}
}
