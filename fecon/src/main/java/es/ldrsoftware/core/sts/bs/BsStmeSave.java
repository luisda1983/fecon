package es.ldrsoftware.core.sts.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.sts.entity.Stme;
import es.ldrsoftware.core.sts.entity.StmeDao;

@Component
public class BsStmeSave extends BaseBS {

	@Autowired
	private StmeDao stmeDao;

	protected void execute(BaseBSArea a) throws Exception {
		BsStmeSaveArea area = (BsStmeSaveArea)a;
		
		Stme stme = area.IN.stme;
		
		stme = stmeDao.save(stme);
		
		area.OUT.stme = stme;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsStmeSaveArea area = (BsStmeSaveArea)a;

		validateDtoRequired(area.IN.stme, CoreNotify.STME_SAVE_STME_RQRD);
		
		Stme stme = area.IN.stme;
		
		validateIntRequired(stme.getAnyo(), CoreNotify.STME_SAVE_ANYO_RQRD);
		validateIntRange(stme.getAnyo(), 1900, 2999, CoreNotify.STME_SAVE_ANYO_RNGE);
		
		validateIntRequired(stme.getMess(), CoreNotify.STME_SAVE_MESS_RQRD);
		validateIntRange(stme.getMess(), 01, 12, CoreNotify.STME_SAVE_MESS_RNGE);
		
		validateStringRequired(stme.getCtrl(), CoreNotify.STME_SAVE_CTRL_RQRD);
		validateStringMaxLength(stme.getCtrl(), 30, CoreNotify.STME_SAVE_CTRL_MAXL);
		
		validateIntRequired(stme.getTota(), CoreNotify.STME_SAVE_TOTA_RQRD);
		validateIntRange(stme.getTota(), 0, 99999999, CoreNotify.STME_SAVE_TOTA_RNGE);
		
		validateDecRequired(stme.getTime(), CoreNotify.STME_SAVE_TIME_RQRD);
		validateDecRange(stme.getTime(), 0, 9999999999.9999, CoreNotify.STME_SAVE_TIME_RNGE);
		
		validateIntRequired(stme.getTima(), CoreNotify.STME_SAVE_TIMA_RQRD);
		validateIntRange(stme.getTima(), 0, 99999999, CoreNotify.STME_SAVE_TIMA_RNGE);
		
		validateIntRequired(stme.getTimi(), CoreNotify.STME_SAVE_TIMI_RQRD);
		validateIntRange(stme.getTimi(), 0, 99999999, CoreNotify.STME_SAVE_TIMI_RNGE);
		
		validateIntRange(stme.getNuer(), 0, 99999999, CoreNotify.STME_SAVE_NUER_RNGE);		
	}

}
