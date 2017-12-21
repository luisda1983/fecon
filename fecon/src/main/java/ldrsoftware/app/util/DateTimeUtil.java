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
}