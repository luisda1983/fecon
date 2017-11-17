package ldrsoftware.app.util;

import java.util.Calendar;

public class DateTimeUtil {

	public final static int getFope() {
		Calendar calendar = Calendar.getInstance();
		int date = (calendar.get(Calendar.YEAR) * 10000) + 
				   ((calendar.get(Calendar.MONTH) + 1) * 100)  + 
				   (calendar.get(Calendar.DAY_OF_MONTH));
		return date;
	}
	
	public final static int getHope() {
		Calendar calendar = Calendar.getInstance();
		int time = (calendar.get(Calendar.HOUR_OF_DAY) * 10000) + 
				   (calendar.get(Calendar.MINUTE) * 100) +
				   (calendar.get(Calendar.SECOND));
		return time;
	}
	
	public final static int getYear(int date) {
		int year = 0;
		
		if (date < 10000000) {
			date = 10000101;
		}
		String s = new Integer(date).toString();
		String sYear = s.substring(0, 4);
		year = new Integer(sYear);
		return year;
	}
	
	public final static int getMonth(int date) {
		int month = 0;
		
		if (date < 10000000) {
			date = 10000101;
		}
		String s = new Integer(date).toString();
		String sMonth = s.substring(4, 6);
		System.out.println(s + "||" + sMonth);
		month = new Integer(sMonth);
		return month;
	}
}