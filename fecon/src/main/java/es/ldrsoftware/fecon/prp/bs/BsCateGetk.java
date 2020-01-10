package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Cate;
import es.ldrsoftware.fecon.prp.entity.CateDAO;

@Component
public class BsCateGetk extends BaseBS {

	@Autowired
	CateDAO cateDao;;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCateGetkArea area = (BsCateGetkArea)a;
		
		Cate cate = cateDao.getByIden(SESSION.get().inst, area.IN.iden);
		
		area.OUT.cate = cate;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCateGetkArea area = (BsCateGetkArea)a;
		
		validateInputField(area.IN.iden, Cate.IDEN);
	}

}
