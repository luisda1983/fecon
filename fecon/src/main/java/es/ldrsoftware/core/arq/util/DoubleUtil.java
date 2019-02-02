package es.ldrsoftware.core.arq.util;

public class DoubleUtil {

	public final static double round(double d, int precision) {

		double base = 1.0;
		for (int i = 0; i <= precision; i++) {
			base = base * 10.0;
		}
		
		return Math.round(d * base) / base;
	}
	
}