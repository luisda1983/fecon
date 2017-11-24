package es.ldrsoftware.core.arq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.arq.data.Session;
import es.ldrsoftware.core.fwk.entity.Notf;
import es.ldrsoftware.core.fwk.entity.NotfDAO;

@Component
public class BaseNotifyManager {

	@Autowired
	public NotfDAO notfDao;

	//Variable que almacena el entorno de la ejecucion
	public static final ThreadLocal<Session> SESSION = new ThreadLocal<Session>();

	//Metodo encargado de gestionar las notificaciones al usuario
	public void notify(String code, String... data) throws Exception {
		
		Notf notf;
		
		if (code == null) { 
			notf = new Notf();
			notf.setIden(CoreNotify.CORE_NOTF_IDEN_RQRD);
			notf.setDesc(CoreNotify.CORE_NOTF_IDEN_RQRD_DESC);
			notf.setTipo(Session.EXEC_STATE_VOID);
		}
		
		notf = notfDao.getByIden(code);
		
		if (notf == null) {
			notf = new Notf();
			notf.setIden(CoreNotify.CORE_NOTF_NF);
			notf.setDesc(CoreNotify.CORE_NOTF_NF_DESC);
			notf.setTipo(Session.EXEC_STATE_VOID);
		}
		
		//FIXME: tipos de notificacion no literales
		switch(notf.getTipo()) {
		case "V":
			SESSION.get().EXEC_STATE = Session.EXEC_STATE_VOID;
			SESSION.get().EXEC_VOID  = notf;
			throw new ArqException();
		case "O":
			if ("I".equals(SESSION.get().EXEC_STATE) || " ".equals(SESSION.get().EXEC_STATE)) {
				SESSION.get().EXEC_STATE = Session.EXEC_STATE_OVER;
			}
			SESSION.get().EXEC_OVER_LIST.add(notf);
			break;
		case "I":
			if (" ".equals(SESSION.get().EXEC_STATE = " ")) {
				SESSION.get().EXEC_STATE = Session.EXEC_STATE_INFO;
			}
			SESSION.get().EXEC_INFO_LIST.add(notf);
			break;
		default:
			break;
		}
	}
}
