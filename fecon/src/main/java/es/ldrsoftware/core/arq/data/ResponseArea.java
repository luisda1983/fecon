package es.ldrsoftware.core.arq.data;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import es.ldrsoftware.core.fwk.entity.Notf;

public class ResponseArea {

	//Variables de resultado de ejecucion
	public String EXEC_RC;
		public final static String EXEC_RC_OK   = "N";
		public final static String EXEC_RC_VOID = "V";
		public final static String EXEC_RC_OVER = "O";
		public final static String EXEC_RC_INFO = "I";
	
	public Notf       EXEC_VOID;
	public List<Notf> EXEC_AUTO_LIST;
	public List<Notf> EXEC_INFO_LIST;
	
	//Datos de salida
	public Map<String, Object> OUTPUT = new HashMap<String, Object>();
}
