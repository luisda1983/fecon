package es.ldrsoftware.core.oui.bs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Usua;
import es.ldrsoftware.core.oui.entity.UsuaDAO;

@Component
public class BsUsuaList extends BaseBS {

	@Autowired
	UsuaDAO usuaDao;

	public final static String USUA_LIST_FULL = "LT-01";
	public final static String USUA_LIST_INST = "LT-02";
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaListArea area = (BsUsuaListArea)a;

		List<Usua> usuaList = new ArrayList<Usua>();

		switch(area.IN.tipo) {
		case USUA_LIST_FULL:
			//Obtenemos la lista de usuarios
			usuaList = usuaDao.getList();
			break;
		case USUA_LIST_INST:
			//Obtenemos la lista de usuarios por instalación
			usuaList = usuaDao.getListByInst(area.IN.inst);
			break;
		}
		
		area.OUT.usuaList = usuaList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaListArea area = (BsUsuaListArea)a;

		//Validamos el tipo de listado, y validamos según el tipo
		validateStringRequired(area.IN.tipo, CoreNotify.USUA_LIST_TIPO_RQRD);
		
		switch(area.IN.tipo) {
		case USUA_LIST_FULL:
			//La consulta completa de usuarios sólo está permitida para APM
			if (!LiteData.LT_EL_USUAPERF_APM.equals(SESSION.get().perf)) {
				notify(CoreNotify.USUA_LIST_GRNT_NO);
			}
			break;
		case USUA_LIST_INST:
			//Para el APM es obligatorio que la instalación esté informada
			if (LiteData.LT_EL_USUAPERF_APM.equals(SESSION.get().perf)) {
				validateIntRequired(area.IN.inst, CoreNotify.USUA_LIST_INST_RQRD);
			//Para el ADM se toma la instalación de la Sesión
			} else if (LiteData.LT_EL_USUAPERF_ADM.equals(SESSION.get().perf)) {
				area.IN.inst = SESSION.get().inst;
			//Para el USR la opción no está permitida
			} else {
				notify(CoreNotify.USUA_LIST_GRNT_NO);
			}
			break;
		default:
			notify(CoreNotify.USUA_LIST_TIPO_ERRO);
			break;
		}
		
	}

}
