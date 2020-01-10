package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.entity.Lite;
import es.ldrsoftware.core.fwk.entity.LiteDAO;

@Component
public class BsLiteGetk extends BaseBS {

	@Autowired
	LiteDAO liteDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsLiteGetkArea area = (BsLiteGetkArea)a;

		//Obtenemos el literal de la BB.DD.
		Lite lite = liteDao.getByTblaClav(area.IN.tbla, area.IN.clav);
		
		//Validamos que el literal exista
		validateDtoNotFound(lite, LiteData.LT_EL_DTO_LITE, Lite.key(area.IN.tbla, area.IN.clav));

		//Devolemos el literal en la salida
		area.OUT.lite = lite;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsLiteGetkArea area = (BsLiteGetkArea)a;
	
		//Validamos la entrada: tbla y clav obligatorios
		validateInputField(area.IN.tbla, Lite.TBLA);
		validateInputField(area.IN.clav, Lite.CLAV);
	}

}
