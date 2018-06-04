package es.ldrsoftware.core.arq;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.BaseRequest;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.arq.data.ResponseArea;
import es.ldrsoftware.core.arq.data.Session;
import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.core.fwk.bs.BsCtrlGetk;
import es.ldrsoftware.core.fwk.bs.BsCtrlGetkArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.entity.Ctrl;
import es.ldrsoftware.core.fwk.entity.Notf;

@Component
public abstract class BaseController extends BaseNotifyManager {

	//Varible que contendr� el identificador del controlador
	private String iden;
	
	//Controlador para la gesti�n de los diferentes par�ntesis transaccionales
	@Autowired
	private TxController txCtrl;
	
	//Servicio para obtener la configuraci�n del controlador
	@Autowired
	private BsCtrlGetk bsCtrlGetk;
	
	public BaseController(String iden) {
		this.iden = iden;
	}
	
	//Inicializamos la sesión
	private void inicSession(HttpServletRequest servletRqt) {
		SESSION.set(new Session());
		SESSION.get().initializeSession(servletRqt);
	}
	
	//Finalizamos la sesión
	private void endSession() {
		SESSION.remove();
	}
		
	//Metodo encargado de la ejecucion del servicio asociado al controlador 
	public abstract void execute(BaseBSArea a) throws Exception;
	//Metodo encargado de gestionar la salida hacia el Front
	public abstract void output(BaseBSArea a, ResponseArea response);
	
	//Metodo encargado de toda la l�gica de ejecuci�n de los controladores
	public ResponseArea ctrl(HttpServletRequest servletRqt, BaseRequest rqt, BaseBSArea a) {
		ResponseArea response = new ResponseArea();
		
		//Variable para el control de estadisticas de ejecucion
		long inej, fiej = 0;
		String stst = "S";
		
		//Capturamos el inicio de la ejecucion
		inej = DateTimeUtil.getTime();
		
		try {

			//Inicializamos la sesion
			inicSession(servletRqt);

			//Cargamos en Sesión el dispositivo
			SESSION.get().AREA_SRCE.DEVICE = rqt.DEVICE;
			
			//Recuperamos la configuracion del controlador
			BsCtrlGetkArea ctrlGetkArea = new BsCtrlGetkArea();
			ctrlGetkArea.IN.iden = iden;
			bsCtrlGetk.executeBS(ctrlGetkArea);
			Ctrl ctrl = ctrlGetkArea.OUT.ctrl;
			
			//Capturamos el indicador de estadisticas en la variable local (fuera del try/catch)
			stst = ctrl.getStst();
			
			//Cargamos la admisión de continuación del controlador
			//SESSION.get().cont = ctrl.getCont();
			SESSION.get().AREA_CONT.CONT_GRTN = ctrl.getCont();
			
			//Verificamos si el Controlador admite continuaciones para su configuración
			if (LiteData.LT_EL_BOOL_SI.equals(ctrl.getCont())) {
				//Cargamos el número máximo de registros. Si por error de configuración no está cargado, cargamos 50
				//y elevamos un informativo
				if (ctrl.getNreg() <= 0) {
					ctrlInfoNotify(CoreNotify.CORE_CTRL_CONT_NREG, CoreNotify.CORE_CTRL_CONT_NREG_DESC);
					SESSION.get().AREA_CONT.MAXM_REGS = 50;
				} else {
					SESSION.get().AREA_CONT.MAXM_REGS = ctrl.getNreg();
				}
				//Cargamos el identificador del DAO sobre el que aplica la continuación
				SESSION.get().AREA_CONT.CONT_DAO = ctrl.getIdao();
				//Verificamos si estamos ante una continuación
				if (rqt.CONT_NUMB > 0) {
					SESSION.get().AREA_CONT.CONT_NUMB = rqt.CONT_NUMB;
					SESSION.get().AREA_CONT.ACTV_CONT = LiteData.LT_EL_BOOL_SI;
				}
			}
			
			//Validamos que el controlador está activo
			if (!LiteData.LT_EL_CTRLESTA_ACTIVO.equals(ctrl.getEsta())) {
				ctrlNotify(CoreNotify.CORE_CTRL_ESTA, CoreNotify.CORE_CTRL_ESTA_DESC);
			}
				
			//Validamos el tipo de acceso del controlador
			switch (ctrl.getTiac()) {
			case LiteData.LT_EL_CTRLTIAC_USUARIO:
				//Acceso con usuario registrado. Validamos el identificador de sesion en la llamada
				if (rqt.sesi == 0) {
					ctrlNotify(CoreNotify.CORE_CTRL_TIAC_SESI, CoreNotify.CORE_CTRL_TIAC_SESI_DESC);
				}
				txCtrl.loadSession(rqt.sesi);
				break;
			case LiteData.LT_EL_CTRLTIAC_LOGOFF:
				//Acceso sin usuario registrado. Validamos el identificador de sesion en la llamada a cero.
				if (rqt.sesi != 0) {
					ctrlNotify(CoreNotify.CORE_CTRL_TIAC_NO_SESI, CoreNotify.CORE_CTRL_TIAC_NO_SESI_DESC);
				}
				break;
			case LiteData.LT_EL_CTRLTIAC_LIBRE:
				//Acceso libre
				if (rqt.sesi != 0) {
					txCtrl.loadSession(rqt.sesi);
				}
				break;
			case LiteData.LT_EL_CTRLTIAC_USUARIO_PERFIL:
				//Acceso con usuario registrado y perfil determinado. 
				//Validamos el identificador de sesion en la llamada
				if (rqt.sesi == 0) {
					ctrlNotify(CoreNotify.CORE_CTRL_TIAC_SESI, CoreNotify.CORE_CTRL_TIAC_SESI_DESC);
				}
				txCtrl.loadSession(rqt.sesi);
				//Validamos el perfil del usuario
				if (!SESSION.get().perf.equals(ctrl.getPerf())) {
					ctrlNotify(CoreNotify.CORE_CTRL_TIAC_PERF, CoreNotify.CORE_CTRL_TIAC_PERF_DESC);
				}
				break;
			default:
				//Otros: error de configuracion
				ctrlNotify(CoreNotify.CORE_CTRL_TIAC_DOMI, CoreNotify.CORE_CTRL_TIAC_DOMI_DESC);
				break;
			}
			
			//Ejecucuión del servicio asociado al controlador
			txCtrl.execute(this, a);

			//Si se ha activado continuación, enviamos la información necesaria
			if (LiteData.LT_EL_BOOL_SI.equals(SESSION.get().AREA_CONT.MORE_DATA)) {
				response.OUTPUT.put("MORE_DATA", true);
				response.OUTPUT.put("CONT_NUMB", SESSION.get().AREA_CONT.CONT_NUMB);
			}
			
			//Gestionamos la salida hacia el front
			output(a, response);
			
		} catch (ArqException e) {
			//Notificaciones de aplicaci�n, en principio no es necesario realizar nada, a parte de capturarlas.
		} catch (Exception e) {
			e.printStackTrace();
			//FIXME: parche, que ademas se ventila las notificaciones reales del servicio
			//       Implementar las BSException para capturarlas y no perderlas
			SESSION.get().EXEC_STATE = Session.EXEC_STATE_VOID;
			Notf notf = new Notf();
			notf.setIden("ARQC-00001");
			notf.setTipo(Session.EXEC_STATE_VOID);
			notf.setDesc(e.getLocalizedMessage());
			SESSION.get().EXEC_VOID = notf;
			//TODO: tratamiento de excepci�n
		}
		
		//Evaluamos el estado de ejecucion que tenemos en la sesi�n, para trasladarlo al area de salida (response)
		switch (SESSION.get().EXEC_STATE) {
		case (Session.EXEC_STATE_OK):
			response.EXEC_RC = ResponseArea.EXEC_RC_OK;
			break;
		case (Session.EXEC_STATE_INFO):
			response.EXEC_RC = ResponseArea.EXEC_RC_INFO;
			response.EXEC_INFO_LIST = SESSION.get().EXEC_INFO_LIST;
			break;
		case (Session.EXEC_STATE_OVER):
			response.EXEC_RC = ResponseArea.EXEC_RC_OVER;
			response.EXEC_AUTO_LIST = SESSION.get().EXEC_OVER_LIST;
			//TODO: tratamiento de autorizaci�n
			break;
		case (Session.EXEC_STATE_VOID):
			response.EXEC_RC = ResponseArea.EXEC_RC_VOID;
			response.EXEC_VOID = SESSION.get().EXEC_VOID;
			response.OUTPUT = new HashMap<String, Object>();
			break;
		}
		
		//Capturamos el fin de la ejecuci�n
		fiej = DateTimeUtil.getTime();

		//Si el controlador indica que se graben estadisticas, lo hacemos
		if ("S".equals(stst)) {
			try {
				txCtrl.doStst(iden, inej, fiej);
			} catch (Exception e) {}
		}

		//Finalizamos la sesi�n
		endSession();
		
		return response;
	}

	//Método encargado de articular notificaciones de error de arquitectura
	private void ctrlNotify(String iden, String desc) throws Exception {
		SESSION.get().EXEC_STATE = Session.EXEC_STATE_VOID;
		Notf notf = new Notf();
		notf.setIden(iden);
		notf.setTipo(Session.EXEC_STATE_VOID);
		notf.setDesc(desc);
		SESSION.get().EXEC_VOID = notf;
		
		//FIXME: implementar CtrlException
		throw new Exception();
	}
	
	//Método encargado de registrar notificaciones de información de arquitectura
	private void ctrlInfoNotify(String iden, String desc) throws Exception {
		SESSION.get().EXEC_STATE = Session.EXEC_STATE_INFO;
		Notf notf = new Notf();
		notf.setIden(iden);
		notf.setTipo(Session.EXEC_STATE_INFO);
		notf.setDesc(desc);
		SESSION.get().EXEC_INFO_LIST.add(notf);
	}
}
