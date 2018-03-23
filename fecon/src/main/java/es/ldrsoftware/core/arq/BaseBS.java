package es.ldrsoftware.core.arq;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;

public abstract class BaseBS extends BaseNotifyManager {
	
	protected abstract void execute(BaseBSArea a) throws Exception;
	protected abstract void validateInput(BaseBSArea a) throws Exception;
	
	public void executeBS(BaseBSArea area) throws Exception {
		
		validateInput(area);
		
		execute(area);
	}
	
	protected void validateDtoRequired(Object o, String notf) throws Exception {
		if (o == null) {
			notify(notf);
		}
	}
	
	protected void validateDtoEmpty(Object o, String notf) throws Exception {
		if (o != null) {
			notify(notf);
		}
	}
	
	protected void validateStringRequired(String s, String notf) throws Exception {
		if (s == null || "".equals(s)) {
			notify(notf);
		}
	}
	
	protected void validateStringMaxLength(String s, int length, String notf) throws Exception {
		if (s.length() > length) {
			notify(notf);
		}
	}
	
	protected void validateStringEmpty(String s, String notf) throws Exception {
		if (s == null) {
			s = "";
		}
		if (!"".equals(s)) {
			notify(notf);
		}
	}

	protected void validateStringEqual(String s1, String s2, String notf) throws Exception {
		if (s1 == null) {
			if (s2 != null) {
				notify(notf);
			}
		} else if (s2 == null) {
			notify(notf);
		} else {
			if (!s1.equals(s2)) {
				notify(notf);
			}
		}
	}

	protected void validateStringDomain(String notf, String s, String...strings) throws Exception {
		if (strings.length == 0 || s == null || "".equals(s)) {
			notify(notf);
		} else {
			boolean found = false;
			int i = 0;
			while (i < strings.length && !found) {
				if (s.equals(strings[i])) {
					found = true;
				}
				i++;
			}
			if (!found) {
				notify(notf);
			}
		}
	}
	
	protected void validateIntRequired(long i, String notf) throws Exception {
		if (i == 0) {
			notify(notf);
		}
	}
	
	protected void validateIntRange(long i, int min, int max, String notf) throws Exception {
		if (i < min || i > max) {
			notify(notf);
		}
	}
	
	protected void validateIntEmpty(long i, String notf) throws Exception {
		if (i != 0) {
			notify(notf);
		}
	}
	
	protected void validateDecRequired(double d, String notf) throws Exception {
		if (d == 0) {
			notify(notf);
		}
	}
	
	protected void validateDecEmpty(double d, String notf) throws Exception {
		if (d != 0) {
			notify(notf);
		}
	}
	
	protected void validateListRequired(@SuppressWarnings("rawtypes") List l, String notf) throws Exception {
		if (l == null || l.size() == 0) {
			notify(notf);
		}
	}

	protected void validateListSize(@SuppressWarnings("rawtypes") List l, int size, String notf) throws Exception {
		if (l == null || l.size() != size) {
			notify(notf);
		}
	}
	
	protected void validateListEmpty(@SuppressWarnings("rawtypes") List l, String notf) throws Exception {
		if (!(l == null || l.size() == 0)) {
			notify(notf);
		}
	}
	
	protected boolean testString(String s) {
		if (s == null || "".equals(s)) {
			return false;
		} else {
			return true;
		}
	}

	protected boolean testStringEqual(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return false;
		}
		return s1.equals(s2);
	}
	
	protected boolean testInt(long i) {
		if (i == 0) {
			return false;
		} else {
			return true;
		}
	}
}
