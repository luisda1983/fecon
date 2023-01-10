package es.ldrsoftware.core.arq;

import java.util.List;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;

public abstract class BaseBS extends BaseNotifyManager {
	
	protected abstract void execute(BaseBSArea a) throws Exception;
	protected abstract void validateInput(BaseBSArea a) throws Exception;
	
	public void executeBS(BaseBSArea area) throws Exception {
		
		validateInput(area);
		
		execute(area);
	}
	
	protected BaseDTO validateDto(BaseDTO dto, String name) throws Exception {
		if (dto == null) { 
			notify(CoreNotify.CORE_NOTF_DTO_RQRD, translate(LiteData.LT_TB_DTO, name));
		}
		dto.validate();
		
		if (dto.dtoError) {
			notify(dto.dtoNotf, dto.dtoData);
		}
		return dto;
	}
	
	protected void validateDtoNotFound(BaseDTO dto, String name, String key) throws Exception {
		if (dto == null) {
			notify(CoreNotify.CORE_NOTF_DTO_NF, translate(LiteData.LT_TB_DTO, name), key);
		}
	}

	protected void validateInputField(String s, String name) throws Exception {
		if (s == null || "".equals(s)) {
			notify(CoreNotify.CORE_NOTF_INPT_FILD, name);
		}
	}

	protected void validateInputField(long l, String name) throws Exception {
		if (l == 0) {
			notify(CoreNotify.CORE_NOTF_INPT_FILD, name);
		}
	}
	
	protected void validateInputField(double d, String name) throws Exception {
		if (d == 0) {
			notify(CoreNotify.CORE_NOTF_INPT_FILD, name);
		}
	}
	
	protected void test(boolean equal, String s1, String s2, String notf) throws Exception {
		if (s1 == null) {
			s1 = "";
		}
		if (s2 == null) {
			s2 = "";
		}
		if (equal) {
			if (s1.equals(s2)) {
				notify(notf);
			}
		} else {
			if (!s1.equals(s2)) {
				notify(notf);
			}
		}
	}

	protected void test(boolean equal, long l1, long l2, String notf) throws Exception {
		if (equal) {
			if (l1 == l2) {
				notify(notf);
			}
		} else {
			if (l1 != l2) {
				notify(notf);
			}
		}
	}

	protected void testEmpty(double d, String notf) throws Exception {
		if (d == 0) {
			notify(notf);
		}
	}
	
	protected void testEmpty(@SuppressWarnings("rawtypes") List l, String notf) throws Exception {
		if (l == null || l.size() == 0) {
			notify(notf);
		}
	}

	//FIXME: cambiar por validateNF o usar validateDtoNotFound
	protected void testEmpty(BaseDTO dto, String notf) throws Exception {
		if (dto == null) {
			notify(notf);
		}
	}

	//FIXME: cambiar por validateDP o hace validateDtoDuplicate
	protected void testData(BaseDTO dto, String notf) throws Exception {
		if (dto != null) {
			notify(notf);
		}
	}
	
	protected void testData(double d, String notf) throws Exception {
		if (d != 0) {
			notify(notf);
		}
	}
	
	protected boolean data(String s) {
		if (s == null || "".equals(s)) {
			return false;
		} else {
			return true;
		}
	}
	
	protected boolean data(long l) {
		if (l == 0) {
			return false;
		} else {
			return true;
		}
	}

	protected boolean equal(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return false;
		}
		return s1.equals(s2);
	}
	
	protected void validateStringRequired2(String s, String notf) throws Exception {
		if (s == null || "".equals(s)) {
			notify(notf);
		}
	}

	//TODO: eliminar. Reemplazar por validaciones simples de tipo test + notf. El uso no es de notificacion genérica
	protected void validateDtoEmpty2(Object o, String notf) throws Exception {
		if (o != null) {
			notify(notf);
		}
	}
		
	protected void validateStringNotNull2(String s, String notf) throws Exception {
		if (s == null) {
			notify(notf);
		}
	}
	
	protected void validateStringMaxLength2(String s, int length, String notf) throws Exception {
		if (s.length() > length) {
			notify(notf);
		}
	}
	
	protected void validateStringEmpty2(String s, String notf) throws Exception {
		if (s == null) {
			s = "";
		}
		if (!"".equals(s)) {
			notify(notf);
		}
	}

	protected void validateStringEqual2(String s1, String s2, String notf) throws Exception {
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

	protected void validateStringDomain2(String notf, String s, String...strings) throws Exception {
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
	
	protected void validateIntRequired2(long i, String notf) throws Exception {
		if (i == 0) {
			notify(notf);
		}
	}
	
	protected void validateIntRange2(long i, long min, long max, String notf) throws Exception {
		if (i < min || i > max) {
			notify(notf);
		}
	}
	
	protected void validateIntEmpty2(long i, String notf) throws Exception {
		if (i != 0) {
			notify(notf);
		}
	}
	
	protected void validateDecRequired2(double d, String notf) throws Exception {
		if (d == 0) {
			notify(notf);
		}
	}
	
	protected void validateDecEmpty2(double d, String notf) throws Exception {
		if (d != 0) {
			notify(notf);
		}
	}
	
	protected void validateDecRange2(double d, double min, double max, String notf) throws Exception {
		if (d < min || d > max) {
			notify(notf);
		}
	}
	
	protected void validateListRequired2(@SuppressWarnings("rawtypes") List l, String notf) throws Exception {
		if (l == null || l.size() == 0) {
			notify(notf);
		}
	}

	protected void validateListSize2(@SuppressWarnings("rawtypes") List l, int size, String notf) throws Exception {
		if (l == null || l.size() != size) {
			notify(notf);
		}
	}
	
	protected void validateListEmpty2(@SuppressWarnings("rawtypes") List l, String notf) throws Exception {
		if (!(l == null || l.size() == 0)) {
			notify(notf);
		}
	}
	
	protected boolean testString2(String s) {
		if (s == null || "".equals(s)) {
			return false;
		} else {
			return true;
		}
	}

	protected boolean testStringEqual2(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return false;
		}
		return s1.equals(s2);
	}
	
	protected boolean testInt2(long i) {
		if (i == 0) {
			return false;
		} else {
			return true;
		}
	}
}
