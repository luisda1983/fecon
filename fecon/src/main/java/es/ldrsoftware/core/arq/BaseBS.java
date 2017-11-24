package es.ldrsoftware.core.arq;

import es.ldrsoftware.core.arq.data.BaseBSArea;

public abstract class BaseBS extends BaseNotifyManager {
	
	protected abstract void execute(BaseBSArea a) throws Exception;
	protected abstract void validateInput(BaseBSArea a) throws Exception;
	
	public void executeBS(BaseBSArea area) throws Exception {
		
		validateInput(area);
		
		execute(area);
	}
	
	protected void validateDto(Object o, String notf) throws Exception {
		if (o == null) {
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
	
	protected boolean testString(String s) {
		if (s == null || "".equals(s)) {
			return false;
		} else {
			return true;
		}
	}
	
	protected boolean testInt(long i) {
		if (i == 0) {
			return false;
		} else {
			return true;
		}
	}
}
