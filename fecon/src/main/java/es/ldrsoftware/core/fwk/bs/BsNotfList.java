package es.ldrsoftware.core.fwk.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.entity.Notf;
import es.ldrsoftware.core.fwk.entity.NotfDAO;

@Component
public class BsNotfList extends BaseBS {

	@Autowired
	NotfDAO notfDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsNotfListArea area = (BsNotfListArea)a;
		
		List<Notf> notfList = notfDao.getListByApli(area.IN.apli);
				
		area.OUT.notfList = notfList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsNotfListArea area = (BsNotfListArea)a;
		
		validateStringRequired(area.IN.apli, CoreNotify.NOTF_LIST_APLI_RQRD);
	}
}
