package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.ConcDAO;

@Component
public class BsConcGeto extends BaseBS {

	@Autowired
	ConcDAO concDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsConcGetoArea area = (BsConcGetoArea)a;
		
		int orde = concDao.getOrde(SESSION.get().inst, area.IN.cate);
		area.OUT.orde = orde;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsConcGetoArea area = (BsConcGetoArea)a;
	}

}
