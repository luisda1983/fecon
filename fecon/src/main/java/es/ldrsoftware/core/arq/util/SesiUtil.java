package es.ldrsoftware.core.arq.util;

import es.ldrsoftware.core.fwk.entity.Sesi;

public class SesiUtil {

	public final static boolean isSesiActi(Sesi sesi) {
		boolean b = false;
		int date = DateTimeUtil.getFeop();
		int time = DateTimeUtil.getHoop();
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
