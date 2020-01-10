package es.ldrsoftware.core.arq.util;

import java.util.Random;

public class StringUtil {

	public final static String random(int length) {

		Random random = new Random();
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		
		String s = "";
		
		for (int i = 0; i < length; i++) {
			s = s + chars.charAt(random.nextInt(chars.length() - 1));
		}
		return s;
	}

	public final static String extend(String s, int length) {
		while (s.length() < length) {
			s = s + " ";
		}
		return s;
	}
	
	public final static String extend(long i, int length) {
		Long l = new Long(i);
		String s = l.toString();
		while (s.length() < length) {
			s = "0" + s;
		}
		return s;
	}

	public final static String convert(long i) {
		Long l = new Long(i);
		String s = l.toString();
		return s;
	}
	
	public final static String convert(double i) {
		Double d = new Double(i);
		String s = d.toString();
		return s;
	}
}