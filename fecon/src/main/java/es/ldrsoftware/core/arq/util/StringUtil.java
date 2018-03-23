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
	
}