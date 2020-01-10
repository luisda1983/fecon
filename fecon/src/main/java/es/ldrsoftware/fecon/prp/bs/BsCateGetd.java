package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Cate;
import es.ldrsoftware.fecon.prp.entity.CateDAO;

@Component
public class BsCateGetd extends BaseBS {

	@Autowired
	CateDAO cateDao;;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCateGetdArea area = (BsCateGetdArea)a;
		
		Cate cate;
		cate = cateDao.getByDesc(SESSION.get().inst, area.IN.desc);
		area.OUT.cateDesc = cate;
		
		cate = cateDao.getByDesl(SESSION.get().inst, area.IN.desc);
		area.OUT.cateDesl = cate;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCateGetdArea area = (BsCateGetdArea)a;
		
		validateInputField(area.IN.desl, Cate.DESL);
		validateInputField(area.IN.desc, Cate.DESC);
	}

}
