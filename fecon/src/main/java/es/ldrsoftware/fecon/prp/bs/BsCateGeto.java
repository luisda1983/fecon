package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.CateDAO;

@Component
public class BsCateGeto extends BaseBS {

	@Autowired
	CateDAO cateDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCateGetoArea area = (BsCateGetoArea)a;
		
		int orde = cateDao.getOrde(SESSION.get().inst);
		area.OUT.orde = orde;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsCateGetoArea area = (BsCateGetoArea)a;
	}

}
