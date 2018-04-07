//Normalizado
package es.ldrsoftware.core.fwk.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.entity.Sesi;
import es.ldrsoftware.core.fwk.entity.SesiDAO;

@Component
public class BsSesiList extends BaseBS {

	@Autowired
	public SesiDAO sesiDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsSesiListArea area = (BsSesiListArea)a;
		
		List<Sesi> sesiList = sesiDao.getListByEsta(area.IN.esta);
				
		area.OUT.sesiList = sesiList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsSesiListArea area = (BsSesiListArea)a;
		
		validateStringRequired(area.IN.esta, CoreNotify.SESI_LIST_ESTA_RQRD);
		validateStringDomain(CoreNotify.SESI_LIST_ESTA_ERRO, area.IN.esta, LiteData.LT_ST_SESIESTA);
	}
}