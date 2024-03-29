package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Cate;
import es.ldrsoftware.fecon.prp.entity.CateDAO;

@Component
public class BsCateSave extends BaseBS {
	
	@Autowired
	public CateDAO cateDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCateSaveArea area = (BsCateSaveArea)a;

		Cate cate = area.IN.cate;
		
		cate = cateDao.save(cate);
		
		area.OUT.cate = cate;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCateSaveArea area = (BsCateSaveArea)a;
		
		Cate cate = (Cate)validateDto(area.IN.cate, es.ldrsoftware.fecon.data.LiteData.LT_EL_DTO_CATE);
	}
}