package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.fecon.data.AppNotify;
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
		
		Cate cate = area.IN.cate;
		
		validateDtoRequired(cate, AppNotify.CATE_SAVE_CATE_RQRD);
		
		validateIntRequired(cate.getInst(), AppNotify.CATE_SAVE_INST_RQRD);
		validateIntRange(cate.getInst(), 0, 999999999, AppNotify.CATE_SAVE_INST_RNGE);
		
		validateStringRequired(cate.getDesl(), AppNotify.CATE_SAVE_DESL_RQRD);
		validateStringMaxLength(cate.getDesl(), 30, AppNotify.CATE_SAVE_DESL_MAXL);
		
		validateStringRequired(cate.getDesc(), AppNotify.CATE_SAVE_DESC_RQRD);
		validateStringMaxLength(cate.getDesc(), 10, AppNotify.CATE_SAVE_DESC_MAXL);
		
		validateIntRequired(cate.getOrde(), AppNotify.CATE_SAVE_ORDE_RQRD);
		validateIntRange(cate.getOrde(), 0, 99, AppNotify.CATE_SAVE_ORDE_RNGE);
		
		validateStringRequired(cate.getPres(), AppNotify.CATE_SAVE_PRES_RQRD);
		validateStringDomain(AppNotify.CATE_SAVE_PRES_ERRO, cate.getPres(), LiteData.LT_ST_BOOL);
	}
}