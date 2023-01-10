package es.ldrsoftware.core.arq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.arq.data.Session;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.entity.Lite;
import es.ldrsoftware.core.fwk.entity.LiteDAO;
import es.ldrsoftware.core.fwk.entity.Notf;
import es.ldrsoftware.core.fwk.entity.NotfDAO;

@Component
public class BaseNotifyManager {

	@Autowired
	public NotfDAO notfDao;

	@Autowired
	public LiteDAO liteDao;
	
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
		} else {
			System.out.println(code);
			System.out.println(notfDao);
			System.out.println(liteDao);
			notf = notfDao.getByIden(code);
			
			if (notf == null) {
				notf = new Notf();
				notf.setIden(CoreNotify.CORE_NOTF_NF);
				notf.setDesc(CoreNotify.CORE_NOTF_NF_DESC + " " + code);
				notf.setTipo(Session.EXEC_STATE_VOID);
			} else {
				//FIXME: creo que si fuera Info (o auto en confirmacion), se persistir�an los cambios en notf
				if (data != null && LiteData.LT_EL_NOTFTIPO_VOID.equals(notf.getTipo())) {
					int i = 0;
					
					while (i<data.length) {
						int j = notf.getDesc().indexOf("$" + i + "$");
						if (j>0) {
							notf.setDesc(notf.getDesc().replace("$" + i + "$", data[i]));
						}
						i++;
					}
				}
			}
		}
		
		switch(notf.getTipo()) {
		case LiteData.LT_EL_NOTFTIPO_VOID:
			SESSION.get().EXEC_STATE = Session.EXEC_STATE_VOID;
			SESSION.get().EXEC_VOID  = notf;
			throw new ArqException();
		case LiteData.LT_EL_NOTFTIPO_AUTORIZACION:
			if ("I".equals(SESSION.get().EXEC_STATE) || " ".equals(SESSION.get().EXEC_STATE)) {
				SESSION.get().EXEC_STATE = Session.EXEC_STATE_OVER;
			}
			SESSION.get().EXEC_OVER_LIST.add(notf);
			break;
		case LiteData.LT_EL_NOTFTIPO_INFORMATIVO:
			if (" ".equals(SESSION.get().EXEC_STATE = " ")) {
				SESSION.get().EXEC_STATE = Session.EXEC_STATE_INFO;
			}
			SESSION.get().EXEC_INFO_LIST.add(notf);
			break;
		default:
			break;
		}
	}
	
	//M�todo que permite obtener literales en la elevaci�n de notificaciones
	protected String translate(String tbla, String elem) {
		Lite lite = liteDao.getByTblaClav(tbla, elem);
		
		if (lite == null) {
			return elem;
		} else {
			return lite.getDesc();
		}
	}
}
