package es.ldrsoftware.core.arq.data;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.core.fwk.data.LiteData;
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
//	public String diip;
	
	//Variable de perfil de sesión
	public String perf;
	//Variable de código de instalación
	public long   inst;
	//Variable de usuario de sesión
	public String usua;

	public AreaCont AREA_CONT = new AreaCont();
	
	public class AreaCont {
		public String CONT_GRTN = LiteData.LT_EL_BOOL_NO;
		public int    MAXM_REGS = 0;
		public String CONT_DAO  = "";
		
		public String MORE_DATA = LiteData.LT_EL_BOOL_NO;
		
		public String ACTV_CONT = LiteData.LT_EL_BOOL_NO;
		public int    CONT_NUMB = 0;
	}
	
	public AreaSrce AREA_SRCE = new AreaSrce();
	
	public class AreaSrce {
		public String DEVICE = "";
		public String DIIP   = "";
	}
	
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
	
		//Inicializa la identificación de ejecución
		feop = DateTimeUtil.getFeop();
		hoop = DateTimeUtil.getHoop();
		String proxyIp   = servletRqt.getHeader("X-FORWARDED-FOR");
		String noProxyIp = servletRqt.getRemoteAddr();
		
		if (proxyIp == null) {
			AREA_SRCE.DIIP = noProxyIp;
		} else {
			AREA_SRCE.DIIP = proxyIp;
		}
		
		//Inicializa perfil de sesión
		perf = "OFF";
		//Inicializa código de instalación
		inst = 0;
		//Inicializa usuario de sesión
		usua = "";
		
		//Inicializa datos de continuación
		//cont = LiteData.LT_EL_BOOL_NO;
		//acco = LiteData.LT_EL_BOOL_NO;
		//nreg = 0;
		//idao = "";
		//ixco = 0;
		
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
			
		//Inicializa la identificación de ejecución
		feop = DateTimeUtil.getFeop();
		hoop = DateTimeUtil.getHoop();
				
		//Inicializa perfil de sesión
		perf = "";
		//Inicializa código de instalación
		inst = 0;
		//Inicializa usuario de sesión
		usua = "BATCH";

		//Inicializamos datos de ejecución Batch
		fbop = 0;
		hbop = 0;
		fbtc = 0;
	}
}
