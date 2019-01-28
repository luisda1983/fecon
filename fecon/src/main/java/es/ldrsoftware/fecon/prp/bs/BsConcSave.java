package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.LiteData;
import es.ldrsoftware.fecon.prp.entity.Conc;
import es.ldrsoftware.fecon.prp.entity.ConcDAO;

@Component
public class BsConcSave extends BaseBS {
	
	@Autowired
	public ConcDAO concDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsConcSaveArea area = (BsConcSaveArea)a;

		Conc conc = area.IN.conc;
		
		conc = concDao.save(conc);
		
		area.OUT.conc = conc;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsConcSaveArea area = (BsConcSaveArea)a;
		
		Conc conc = area.IN.conc;
		validateDtoRequired(conc, AppNotify.CONC_SAVE_CONC_RQRD);
		
		validateIntRequired(conc.getCate(), AppNotify.CONC_SAVE_CATE_RQRD);
		validateIntRange(conc.getCate(), 0, 999999999, AppNotify.CONC_SAVE_CATE_RNGE);
		
		validateIntRequired(conc.getInst(), AppNotify.CONC_SAVE_INST_RQRD);
		validateIntRange(conc.getInst(), 0, 999999999, AppNotify.CONC_SAVE_INST_RNGE);
		
		validateStringRequired(conc.getTipo(), AppNotify.CONC_SAVE_TIPO_RQRD);
		validateStringDomain(AppNotify.CONC_SAVE_TIPO_ERRO, conc.getTipo(), LiteData.LT_ST_CONCTIPO);
		
		validateStringRequired(conc.getDesl(), AppNotify.CONC_SAVE_DESL_RQRD);
		validateStringMaxLength(conc.getDesl(), 30, AppNotify.CONC_SAVE_DESL_MAXL);
		
		validateStringRequired(conc.getDesc(), AppNotify.CONC_SAVE_DESC_RQRD);
		validateStringMaxLength(conc.getDesc(), 10, AppNotify.CONC_SAVE_DESC_MAXL);
		
		validateIntRequired(conc.getOrde(), AppNotify.CONC_SAVE_ORDE_RQRD);
		validateIntRange(conc.getOrde(), 0, 99, AppNotify.CONC_SAVE_ORDE_RNGE);
	}
}