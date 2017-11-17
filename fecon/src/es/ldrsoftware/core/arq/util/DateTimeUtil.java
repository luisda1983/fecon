package es.ldrsoftware.core.arq.util;

import java.util.Calendar;
import java.util.TimeZone;

import es.ldrsoftware.core.fwk.data.PVFechperiod;

public class DateTimeUtil {

	public final static int getFeop() {
		Calendar calendar = Calendar.getInstance();
		//TODO: se puede obtener el timezone del request desde el javascript. Analizar y tratar de implementar
		calendar.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		int date = (calendar.get(Calendar.YEAR) * 10000) + 
				   ((calendar.get(Calendar.MONTH) + 1) * 100)  + 
				   (calendar.get(Calendar.DAY_OF_MONTH));
		return date;
	}
	
	public final static int getHoop() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		int time = (calendar.get(Calendar.HOUR_OF_DAY) * 10000) + 
				   (calendar.get(Calendar.MINUTE) * 100) +
				   (calendar.get(Calendar.SECOND));
		//Eliminamos del sistema la hora 00:00:00 para que no genere problemas en validaciones
		if (time == 0) { time++; }
		return time;
	}
	
	public final static long getTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		return calendar.getTimeInMillis();
	}
	
	public final static String getNope() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		
		int yyyy = calendar.get(Calendar.YEAR);
		int mm = calendar.get(Calendar.MONTH);
		int dd = calendar.get(Calendar.DAY_OF_MONTH);
		int HH = calendar.get(Calendar.HOUR_OF_DAY);
		int MM = calendar.get(Calendar.MINUTE);
		int SS = calendar.get(Calendar.SECOND);
		int ml = calendar.get(Calendar.MILLISECOND);
		
		return format(yyyy, 4) +
			   format(mm, 2) +
			   format(dd, 2) +
			   format(HH, 2) + 
			   format(MM, 2) +
			   format(SS, 2) + 
			   format(ml, 3);
	}
	
	private final static String format(int i, int length) {
		String s = "";
		s = new Integer(i).toString();
		if (s.length() > length) {
			s = s.substring(0, length - 1);
		} else {
			while (s.length() < length) {
				s = "0" + s;
			}
		}
		return s;
	}
	
	public final static DateTimeData fechPeriod(DateTimeData dtd, PVFechperiod period) {
		int fech = dtd.fech;
		int hora = dtd.hora;
		
		int YYYY = fech / 10000;
		fech = fech - (YYYY*10000);
		int MM = fech / 100;
		fech = fech - (MM*100);
		int DD = fech;
		
		int hh = hora /10000;
		hora = hora - (hh*10000);
		int mm = hora / 100;
		hora = hora - (mm*100);
		int ss = hora;
		
		if (period.ss != 0) {
			if ("-".equals(period.avre)) {
				ss = ss - period.ss;
			} else {
				ss = ss + period.ss;
			}
		}
		
		if (period.mm != 0) {
			if ("-".equals(period.avre)) {
				mm = mm - period.mm;
			} else {
				mm = mm + period.mm;
			}
		}
		
		if (period.hh != 0) {
			if ("-".equals(period.avre)) {
				hh = hh - period.hh;
			} else {
				hh = hh + period.hh;
			}
		}
		
		if (period.DD != 0) {
			if ("-".equals(period.avre)) {
				DD = DD - period.DD;
			} else {
				DD = DD + period.DD;
			}
		}
		
		if (period.MM != 0) {
			if ("-".equals(period.avre)) {
				MM = MM - period.MM;
			} else {
				MM = MM + period.MM;
			}
		}
		
		if (period.YYYY != 0) {
			if ("-".equals(period.avre)) {
				YYYY = YYYY - period.YYYY;
			} else {
				YYYY = YYYY + period.YYYY;
			}
		}
		
		while (ss<0) {
			ss = ss + 60;
			mm--;
		}
		while (ss>59) {
			ss = ss - 60;
			mm++;
		}
		while (mm<0) {
			mm = mm + 60;
			hh--;
		}
		while (mm>59) {
			mm = mm - 60;
			hh++;
		}
		while (hh<0) {
			hh = hh + 24;
			DD--;
		}
		while (hh>23) {
			hh = hh - 24;
			DD++;
		}
		while (DD<=0) {
			DD = DD + maxDay(MM-1, YYYY);
			MM--;
		}
		while (DD > maxDay(MM, YYYY)) {
			DD = DD - maxDay(MM, YYYY);
			MM++;
		}
		while (MM <= 0) {
			MM = MM + 12;
			YYYY--;
		}
		while (MM > 12) {
			MM = MM - 12;
			YYYY++;
		}
		dtd.fech = YYYY * 10000 + MM * 100 + DD;
		dtd.hora = hh * 10000 + mm * 100 + ss;
		return dtd;
	}
	
	private static int maxDay(int month, int year) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		} else {
			if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
				return 29;
			} else {
				return 28;
			}	
		}
	}
	
	public final static int getYear(long date) {
		int year = 0;
		
		if (date < 10000000) {
			date = 10000101;
		}
		String s = new Long(date).toString();
		String sYear = s.substring(0, 4);
		year = new Integer(sYear);
		return year;
	}
	
	public final static int getMonth(long date) {
		int month = 0;
		
		if (date < 10000000) {
			date = 10000101;
		}
		String s = new Long(date).toString();
		String sMonth = s.substring(4, 6);
		month = new Integer(sMonth);
		return month;
	}
}