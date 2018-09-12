package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.entity.Rela;
import es.ldrsoftware.core.fwk.entity.RelaDAO;

@Component
public class BsRelaSave extends BaseBS {

	@Autowired
	public RelaDAO relaDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsRelaSaveArea area = (BsRelaSaveArea)a;
	
		Rela rela = relaDao.save(area.IN.rela);
		
		area.OUT.rela = rela;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsRelaSaveArea area = (BsRelaSaveArea)a;
		
		validateDtoRequired(area.IN.rela, CoreNotify.RELA_SAVE_RELA_RQRD);
		
		Rela rela = area.IN.rela;
		
		validateStringRequired(rela.getMae1(), CoreNotify.RELA_SAVE_MAE1_RQRD);
		validateStringDomain(CoreNotify.RELA_SAVE_MAE1_ERRO, rela.getMae1(), LiteData.LT_ST_RELAMAES);
		
		//FIXME: admite 18
		validateIntRange(rela.getCln1(), 0,  999999999, CoreNotify.RELA_SAVE_CLN1_RNGE);
		
		validateStringNotNull(rela.getClc1(), CoreNotify.RELA_SAVE_CLC1_NULL);
		validateStringMaxLength(rela.getClc1(), 30, CoreNotify.RELA_SAVE_CLC1_MAXL);
		
		validateStringRequired(rela.getMae2(), CoreNotify.RELA_SAVE_MAE2_RQRD);
		validateStringDomain(CoreNotify.RELA_SAVE_MAE2_ERRO, rela.getMae2(), LiteData.LT_ST_RELAMAES);
		
		//FIXME: admite 18
		validateIntRange(rela.getCln2(), 0, 999999999, CoreNotify.RELA_SAVE_CLN2_RNGE);
		
		validateStringNotNull(rela.getClc2(), CoreNotify.RELA_SAVE_CLC2_NULL);
		validateStringMaxLength(rela.getClc2(), 30, CoreNotify.RELA_SAVE_CLC2_MAXL);
		
		validateStringRequired(rela.getEsta(), CoreNotify.RELA_SAVE_ESTA_RQRD);
		validateStringDomain(CoreNotify.RELA_SAVE_ESTA_ERRO, rela.getEsta(), LiteData.LT_ST_RELAESTA);
		
		validateIntRequired(rela.getFeal(), CoreNotify.RELA_SAVE_FEAL_RQRD);
		validateIntRange(rela.getFeal(), 19000101, 29991231, CoreNotify.RELA_SAVE_FEAL_RNGE);
		
		validateStringNotNull(rela.getData(), CoreNotify.RELA_SAVE_DATA_NULL);
		validateStringMaxLength(rela.getData(), 100, CoreNotify.RELA_SAVE_DATA_MAXL);
		
	}
}