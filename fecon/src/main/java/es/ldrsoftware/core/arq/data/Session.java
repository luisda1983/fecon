package es.ldrsoftware.core.arq.data;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.core.fwk.entity.Notf;

@Component
public class Session {
		
	//Variables de estado de ejecucion
	public String EXEC_STATE;
		public final static String EXEC_STATE_OK   = " ";
		public final static String EXEC_STATE_VOID = "V";
		public final static String EXEC_STATE_OVER = "O";
		public final static String EXEC_STATE_INFO = "I";
		
	public Notf       EXEC_VOID;
	public List<Notf> EXEC_OVER_LIST;
	public List<Notf> EXEC_INFO_LIST;

	//Variables de identificaci�n de ejecuci�n
	public int    feop;
	public int    hoop;
	public String diip;
	
	//Variable de perfil de sesi�n
	public String perf;
	//Variable de c�digo de instalaci�n
	public long   inst;
	//Variable de usuario de sesi�n
	public String usua;

	//Variables de uso Batch
	//Fecha Batch
	public int    fbtc;
	//Fecha batch de operación
	public int    fbop;
	//Hora batch de operación
	public int    hbop;
	
	public void initializeSession(HttpServletRequest servletRqt) {
		
		//Inicializa estado de ejecucion
		EXEC_STATE     = " ";
		EXEC_VOID      = new Notf();
		EXEC_OVER_LIST = new ArrayList<Notf>();
		EXEC_INFO_LIST = new ArrayList<Notf>();
	
		//Inicializa la identificaci�n de ejecuci�n
		feop = DateTimeUtil.getFeop();
		hoop = DateTimeUtil.getHoop();
		String proxyIp   = servletRqt.getHeader("X-FORWARDED-FOR");
		String noProxyIp = servletRqt.getRemoteAddr();
		
		if (proxyIp == null) {
			diip = noProxyIp;
		} else {
			diip = proxyIp;
		}
		
		//Inicializa perfil de sesi�n
		perf = "OFF";
		//Inicializa c�digo de instalaci�n
		inst = 0;
		//Inicializa usuario de sesi�n
		usua = "";
		
		//Inicializamos datos de ejecución Batch
		fbop = 0;
		hbop = 0;
		fbtc = 0;
	}
	
	public void initializeBtcSession() {
		//Inicializa estado de ejecucion
		EXEC_STATE     = " ";
		EXEC_VOID      = new Notf();
		EXEC_OVER_LIST = new ArrayList<Notf>();
		EXEC_INFO_LIST = new ArrayList<Notf>();
			
		//Inicializa la identificaci�n de ejecuci�n
		feop = DateTimeUtil.getFeop();
		hoop = DateTimeUtil.getHoop();
		diip = "";
				
		//Inicializa perfil de sesi�n
		perf = "";
		//Inicializa c�digo de instalaci�n
		inst = 0;
		//Inicializa usuario de sesi�n
		usua = "BATCH";
		
		//Inicializamos datos de ejecución Batch
		fbop = 0;
		hbop = 0;
		fbtc = 0;
	}
	
	//Convivencia para ejecutar servicios nuevos desde servicios antiguos
	public void initializeSession(String ip) {
		
		//Inicializa estado de ejecucion
		EXEC_STATE     = " ";
		EXEC_VOID      = new Notf();
		EXEC_OVER_LIST = new ArrayList<Notf>();
		EXEC_INFO_LIST = new ArrayList<Notf>();
	
		//Inicializa la identificaci�n de ejecuci�n
		feop = DateTimeUtil.getFeop();
		hoop = DateTimeUtil.getHoop();
		diip = ip;
		
		//Inicializa perfil de sesi�n
		perf = "OFF";

	}
}
