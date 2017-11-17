package es.ldrsoftware.core.arq.util;

import java.util.Calendar;
import java.util.TimeZone;

import es.ldrsoftware.core.fwk.entity.Sesi;

public class SesiUtil {

	public final static boolean isSesiActi(Sesi sesi) {
		boolean b = false;
		//FIXME: deberíamos centralizar la obtención de horas en DateTimeUtil
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		int date = (calendar.get(Calendar.YEAR) * 10000) + 
				   ((calendar.get(Calendar.MONTH) + 1) * 100)  + 
				   (calendar.get(Calendar.DAY_OF_MONTH));
		int time = (calendar.get(Calendar.HOUR_OF_DAY) * 10000) + 
				   (calendar.get(Calendar.MINUTE) * 100) +
				   (calendar.get(Calendar.SECOND));
	    if (sesi.getFeca() < date) {
	    	b = false;
	    } else if (sesi.getFeca() == date) {
	    	if (sesi.getHoca() < time) {
	    		b = false;
	    	} else {
	    		b = true;
	    	}
	    } else {
	    	b = true;
	    }
		return b;
	}
}
