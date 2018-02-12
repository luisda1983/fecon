package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.entity.Lite;
import es.ldrsoftware.core.fwk.entity.LiteDAO;

@Component
public class BsLiteGet extends BaseBS {

	@Autowired
	LiteDAO liteDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsLiteGetArea area = (BsLiteGetArea)a;

		//Obtenemos el literal de la BB.DD.
		Lite lite = liteDao.getByTblaClav(area.IN.tbla, area.IN.clav);
		
		//Validamos que el literal exista
		validateDto(lite, CoreNotify.LITE_NF);

		//Devolemos el literal en la salida
		area.OUT.lite = lite;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsLiteGetArea area = (BsLiteGetArea)a;
	
		//Validamos la entrada: tbla y clav obligatorios
		validateStringRequired(area.IN.tbla, CoreNotify.LITE_GETL_TBLA_RQRD);
		validateStringRequired(area.IN.clav, CoreNotify.LITE_GETL_CLAV_RQRD);

	}

}
