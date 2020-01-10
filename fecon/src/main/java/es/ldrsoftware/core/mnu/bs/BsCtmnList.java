package es.ldrsoftware.core.mnu.bs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.mnu.entity.Ctmn;
import es.ldrsoftware.core.mnu.entity.CtmnDAO;

@Component
public class BsCtmnList extends BaseBS {

	@Autowired
	CtmnDAO ctmnDao;

	public final static String CTMN_LIST_PERF_FULL = "LT-01";
	public final static String CTMN_LIST_PERF_ACTI = "LT-02";
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCtmnListArea area = (BsCtmnListArea)a;

		List<Ctmn> ctmnList = new ArrayList<Ctmn>();

		switch(area.IN.tipo) {
		case CTMN_LIST_PERF_FULL:
			//Obtenemos la lista de categorías de menú por perfil
			ctmnList = ctmnDao.getListByPerf(area.IN.perf);
			break;
		case CTMN_LIST_PERF_ACTI:
			//Obtenemos la lista de categorías de menú por perfil e indicador de activada
			ctmnList = ctmnDao.getListByPerfActi(area.IN.perf, area.IN.acti);
			break;
		}
		
		area.OUT.ctmnList = ctmnList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCtmnListArea area = (BsCtmnListArea)a;

		//Validamos el tipo de listado, y validamos según el tipo
		validateInputField(area.IN.tipo, Ctmn.TIPO_LIST);
		
		switch(area.IN.tipo) {
		case CTMN_LIST_PERF_FULL:
			//Se valida que el perfil esté informado
			validateInputField(area.IN.perf, Ctmn.PERF);
			break;
		case CTMN_LIST_PERF_ACTI:
			//Se valida que el perfil y el indicador de activada esté informado
			validateInputField(area.IN.perf, Ctmn.PERF);
			validateInputField(area.IN.acti, Ctmn.ACTI);
			break;
		default:
			notify(CoreNotify.CTMN_LIST_TIPO_ERRO);
			break;
		}
		
	}

}
