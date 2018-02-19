//Normalizado
package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.PVParser;
import es.ldrsoftware.core.fwk.entity.Para;
import es.ldrsoftware.core.fwk.entity.ParaDAO;

@Component
public class BsParaGet extends BaseBS {

	@Autowired
	ParaDAO paraDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsParaGetArea area = (BsParaGetArea)a;
		
		Para para = paraDao.getByTblaClav(area.IN.tbla, area.IN.clav);
		
		validateDto(para, CoreNotify.PARA_GETP_PARA_NF);
		
		PVParser.parse(para);
		
		area.OUT.para = para;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsParaGetArea area = (BsParaGetArea)a;
		
		validateStringRequired(area.IN.tbla, CoreNotify.PARA_GETP_TBLA_RQRD);
		validateStringRequired(area.IN.clav, CoreNotify.PARA_GETP_CLAV_RQRD);
		
	}
}
