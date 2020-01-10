package es.ldrsoftware.core.arq;

import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.arq.util.StringUtil;
import es.ldrsoftware.core.fwk.data.LiteData;

public abstract class BaseDTO extends BaseNotifyManager {
	
	public final static String TIPO_LIST = "Tipo de consulta";
	public final static String APLI = "Aplicación";
	public final static String ACCI = "Acción";
	
	protected abstract String key();
	protected abstract void validate() throws Exception;
	
	protected void validateFieldString(String s, long maxLength, String name) throws Exception {
		if (s == null || "".equals(s)) {
			notify(CoreNotify.CORE_NOTF_INPT_FILD, name);
		}
		if (s.length() > maxLength) {
			notify(CoreNotify.CORE_NOTF_MAXL, name, StringUtil.convert(maxLength));
		}
	}
	
	protected void validateFieldLong(long l, long min, long max, String name) throws Exception {
		if (l < min || l > max) {
			notify(CoreNotify.CORE_NOTF_RNGE, name, StringUtil.convert(min), StringUtil.convert(max));
		}
	}
	
	protected void validateFieldDouble(double d, double min, double max, String name) throws Exception {
		if (d < min || d > max) {
			notify(CoreNotify.CORE_NOTF_RNGE, name, StringUtil.convert(min), StringUtil.convert(max));
		}
	}
	protected void validateFieldBool(String s, String name) throws Exception {
		validateFieldDomain(s, name, LiteData.LT_ST_BOOL);
	}
	
	protected void validateFieldDate(long d, String name) throws Exception {
		if (d < 19000101 || d > 29991231) {
			notify(CoreNotify.CORE_NOTF_DATE_RNGE, name);
		}
	}
	
	protected void validateFieldYear(long y, String name) throws Exception {
		if (y < 1900 || y > 2999) {
			notify(CoreNotify.CORE_NOTF_YEAR_RNGE, name);
		}
	}
	
	protected void validateFieldMonth(long m, String name) throws Exception {
		if (m < 1 || m > 12) {
			notify(CoreNotify.CORE_NOTF_MONT_RNGE, name);
		}
	}
	
	protected void validateFieldTime(long t, String name) throws Exception {
		if (t < 000000 || t > 235959) {
			notify(CoreNotify.CORE_NOTF_TIME_RNGE, name);
		}
	}

	protected void validateFieldShortTime(long t, String name) throws Exception {
		if (t < 0000 || t > 2359) {
			notify(CoreNotify.CORE_NOTF_TIME_RNGE, name);
		}
	}
	protected void validateFieldDomain(String s, String name, String... domain) throws Exception {
		if (domain.length == 0) {
			notify(CoreNotify.CORE_NOTF_DOMN_RQRD, name);
		}
		if (s == null || "".equals(s)) {
			notify(CoreNotify.CORE_NOTF_INPT_FILD, name);
		}
		boolean found = false;
		int i = 0;
		while (i < domain.length && !found) {
			if (s.equals(domain[i])) {
				found = true;
			}
			i++;
		}
		if (!found) {
			notify(CoreNotify.CORE_NOTF_DOMN_NF, name, s);
		}
	}
	
	protected void validateFieldEmpty(String s, String name) throws Exception {
		if (s == null) {
			s = "";
		}
		if (!"".equals(s)) {
			notify(CoreNotify.CORE_NOTF_FILD_EMPT, name, s);
		}
	}
	
	protected void validateFieldEmpty(long l, String name) throws Exception {
		if (l != 0) {
			notify(CoreNotify.CORE_NOTF_FILD_EMPT, name, StringUtil.convert(l));
		}
	}
	
	protected void validateFieldEmpty(double d, String name) throws Exception {
		if (d != 0) {
			notify(CoreNotify.CORE_NOTF_FILD_EMPT, name, StringUtil.convert(d));
		}
	}
}
