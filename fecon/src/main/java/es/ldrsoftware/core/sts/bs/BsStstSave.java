package es.ldrsoftware.core.sts.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.sts.entity.Stst;
import es.ldrsoftware.core.sts.entity.StstDao;

@Component
public class BsStstSave extends BaseBS {

	@Autowired
	private StstDao ststDao;

	protected void execute(BaseBSArea a) throws Exception {
		BsStstSaveArea area = (BsStstSaveArea)a;
		
		Stst stst = area.IN.stst;
		
		stst = ststDao.save(stst);
		
		area.OUT.stst = stst;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsStstSaveArea area = (BsStstSaveArea)a;

		validateDtoRequired(area.IN.stst, CoreNotify.STST_SAVE_STST_RQRD);
		
		Stst stst = area.IN.stst;
		
		validateStringRequired(stst.getCtrl(), CoreNotify.STST_SAVE_CTRL_RQRD);
		validateStringMaxLength(stst.getCtrl(), 30, CoreNotify.STST_SAVE_CTRL_MAXL);
		
		validateIntRange(stst.getInst(), 0, 999999999, CoreNotify.STST_SAVE_INST_RNGE);
		
		validateStringRequired(stst.getUsua(), CoreNotify.STST_SAVE_USUA_RQRD);
		validateStringMaxLength(stst.getUsua(), 30, CoreNotify.STST_SAVE_USUA_MAXL);
		
		validateIntRequired(stst.getFeej(), CoreNotify.STST_SAVE_FEEJ_RQRD);
		validateIntRange(stst.getFeej(), 19000101, 29991231, CoreNotify.STST_SAVE_FEEJ_RNGE);
		
		validateIntRange(stst.getHoej(), 000000, 235900, CoreNotify.STST_SAVE_HOEJ_RNGE);
		
		validateIntRequired(stst.getTiej(), CoreNotify.STST_SAVE_TIEJ_RQRD);
		validateIntRange(stst.getTiej(), 0, 99999999, CoreNotify.STST_SAVE_TIEJ_RNGE);
		
		validateStringDomain(CoreNotify.STST_SAVE_REEJ_ERRO, stst.getReej(), LiteData.LT_ST_STSTREEJ);
		
		if (!LiteData.LT_EL_STSTREEJ_OK.equals(stst.getReej())) {
			validateStringRequired(stst.getNotf(), CoreNotify.STST_SAVE_NOTF_RQRD);
			validateStringMaxLength(stst.getNotf(), 10, CoreNotify.STST_SAVE_NOTF_MAXL);
		}
	}

}
