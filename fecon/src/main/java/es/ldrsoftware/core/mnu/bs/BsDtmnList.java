package es.ldrsoftware.core.mnu.bs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.mnu.entity.Dtmn;
import es.ldrsoftware.core.mnu.entity.DtmnDAO;

@Component
public class BsDtmnList extends BaseBS {

	@Autowired
	DtmnDAO dtmnDao;

	public final static String DTMN_LIST_CTMN_FULL = "LT-01";
	public final static String DTMN_LIST_CTMN_ACTI = "LT-02";
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDtmnListArea area = (BsDtmnListArea)a;

		List<Dtmn> dtmnList = new ArrayList<Dtmn>();

		switch(area.IN.tipo) {
		case DTMN_LIST_CTMN_FULL:
			//Obtenemos la lista de detalle de menú por categoría de menú
			dtmnList = dtmnDao.getListByCtmn(area.IN.ctmn);
			break;
		case DTMN_LIST_CTMN_ACTI:
			//Obtenemos la lista de detalle de menú por categoría de menú e indicador de activado
			dtmnList = dtmnDao.getListByCtmnActi(area.IN.ctmn, area.IN.acti);
			break;
		}
		
		area.OUT.dtmnList = dtmnList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDtmnListArea area = (BsDtmnListArea)a;

		//Validamos el tipo de listado, y validamos según el tipo
		validateInputField(area.IN.tipo, Dtmn.TIPO_LIST);
		
		switch(area.IN.tipo) {
		case DTMN_LIST_CTMN_FULL:
			//Se valida que el identificador de la categoría de menú esté informado
			validateInputField(area.IN.ctmn, Dtmn.CTMN);
			break;
		case DTMN_LIST_CTMN_ACTI:
			//Se valida que el identificador de la categoría de menú esté informado
			validateInputField(area.IN.ctmn, Dtmn.CTMN);
			//Se valida que el indicador de activado esté informado
			validateInputField(area.IN.acti, Dtmn.ACTI);
			break;
		default:
			notify(CoreNotify.DTMN_LIST_TIPO_ERRO);
			break;
		}
	}
}
