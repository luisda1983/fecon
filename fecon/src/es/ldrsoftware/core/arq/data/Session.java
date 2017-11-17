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

	//Variables de identificación de ejecución
	public int    feop;
	public int    hoop;
	public String diip;
	
	//Variable de perfil de sesión
	public String perf;
	//Variable de código de instalación
	public long   inst;
	//Variable de usuario de sesión
	public String usua;

	//Variables de uso Batch
	//Fecha Batch
	public int    fbtc;
	
	public void initializeSession(HttpServletRequest servletRqt) {
		
		//Inicializa estado de ejecucion
		EXEC_STATE     = " ";
		EXEC_VOID      = new Notf();
		EXEC_OVER_LIST = new ArrayList<Notf>();
		EXEC_INFO_LIST = new ArrayList<Notf>();
	
		//Inicializa la identificación de ejecución
		feop = DateTimeUtil.getFeop();
		hoop = DateTimeUtil.getHoop();
		diip = servletRqt.getRemoteAddr();
		
		//Inicializa perfil de sesión
		perf = "OFF";
		//Inicializa código de instalación
		inst = 0;
		//Inicializa usuario de sesión
		usua = "";
		
		//Inicializamos la fecha Batch
		fbtc = 0;
	}
	
	public void initializeBtcSession() {
		//Inicializa estado de ejecucion
		EXEC_STATE     = " ";
		EXEC_VOID      = new Notf();
		EXEC_OVER_LIST = new ArrayList<Notf>();
		EXEC_INFO_LIST = new ArrayList<Notf>();
			
		//Inicializa la identificación de ejecución
		feop = DateTimeUtil.getFeop();
		hoop = DateTimeUtil.getHoop();
		diip = "";
				
		//Inicializa perfil de sesión
		perf = "";
		//Inicializa código de instalación
		inst = 0;
		//Inicializa usuario de sesión
		usua = "BATCH";
		
		//Inicializamos la fecha Batch
		fbtc = 0;
	}
	
	//Convivencia para ejecutar servicios nuevos desde servicios antiguos
	public void initializeSession(String ip) {
		
		//Inicializa estado de ejecucion
		EXEC_STATE     = " ";
		EXEC_VOID      = new Notf();
		EXEC_OVER_LIST = new ArrayList<Notf>();
		EXEC_INFO_LIST = new ArrayList<Notf>();
	
		//Inicializa la identificación de ejecución
		feop = DateTimeUtil.getFeop();
		hoop = DateTimeUtil.getHoop();
		diip = ip;
		
		//Inicializa perfil de sesión
		perf = "OFF";

	}
}
