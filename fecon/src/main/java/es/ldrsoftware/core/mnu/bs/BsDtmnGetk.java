package es.ldrsoftware.core.mnu.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.mnu.entity.Dtmn;
import es.ldrsoftware.core.mnu.entity.DtmnDAO;

@Component
public class BsDtmnGetk extends BaseBS {

	@Autowired
	DtmnDAO dtmnDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDtmnGetkArea area = (BsDtmnGetkArea)a;

		//Obtenemos el detalle de menú
		Dtmn dtmn = dtmnDao.getByCtmnIden(area.IN.ctmn, area.IN.iden);
		
		area.OUT.dtmn = dtmn;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDtmnGetkArea area = (BsDtmnGetkArea)a;

		//Se valida que el identificador de la categoría de menú esté informado
		validateIntRequired(area.IN.ctmn, CoreNotify.DTMN_GETK_CTMN_RQRD);
		//Se valida que el identificador de detalle de menú esté informado
		validateIntRequired(area.IN.iden, CoreNotify.DTMN_GETK_IDEN_RQRD);
	}

}
