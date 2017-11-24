package es.ldrsoftware.core.btc.bs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Ejec;
import es.ldrsoftware.core.btc.entity.EjecDAO;

@Component
public class BsEjecGetList extends BaseBS {

	@Autowired
	EjecDAO ejecDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsEjecGetListArea area = (BsEjecGetListArea)a;
		
		List<Ejec> ejecList = new ArrayList<Ejec>();
		
		if (area.IN.esta == null || "".equals(area.IN.esta)) {
			ejecList = ejecDao.getListByFech(area.IN.fech);
		} else {
			ejecList = ejecDao.getListByFechEsta(area.IN.fech, area.IN.esta);
		}
		
		area.OUT.ejecList = ejecList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsEjecGetListArea area = (BsEjecGetListArea)a;
		
		if (area.IN.fech == 0) { notify(CoreNotify.EJEC_GETL_FECH_RQRD); }
		
	}

}
