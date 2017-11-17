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
	
	@Autowired
	BsParaGet bsParaGet;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsLiteGetArea area = (BsLiteGetArea)a;
		
		Lite lite = liteDao.getByTblaClav(area.IN.tbla, area.IN.clav);
		
		if (lite == null) {
			notify(CoreNotify.LITE_NF);
		}
		
		area.OUT.lite = lite;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsLiteGetArea area = (BsLiteGetArea)a;
		
		if (area.IN.tbla == null || "".equals(area.IN.tbla)) { notify(CoreNotify.LITE_GETL_TBLA_RQRD); }
		if (area.IN.clav == null || "".equals(area.IN.clav)) { notify(CoreNotify.LITE_GETL_CLAV_RQRD); }
		
	}

}
