package es.ldrsoftware.core.sts.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.sts.entity.Stdi;
import es.ldrsoftware.core.sts.entity.StdiDao;

@Component
public class BsStdiSave extends BaseBS {

	@Autowired
	private StdiDao stdiDao;

	protected void execute(BaseBSArea a) throws Exception {
		BsStdiSaveArea area = (BsStdiSaveArea)a;
		
		Stdi stdi = area.IN.stdi;
		
		stdi = stdiDao.save(stdi);
		
		area.OUT.stdi = stdi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsStdiSaveArea area = (BsStdiSaveArea)a;

		validateDtoRequired(area.IN.stdi, CoreNotify.STDI_SAVE_STDI_RQRD);
		
		Stdi stdi = area.IN.stdi;
		
		validateIntRequired(stdi.getFech(), CoreNotify.STDI_SAVE_FECH_RQRD);
		validateIntRange(stdi.getFech(), 19000101, 29991231, CoreNotify.STDI_SAVE_FECH_RNGE);
		
		validateStringRequired(stdi.getCtrl(), CoreNotify.STDI_SAVE_CTRL_RQRD);
		validateStringMaxLength(stdi.getCtrl(), 30, CoreNotify.STDI_SAVE_CTRL_MAXL);
		
		validateIntRequired(stdi.getTota(), CoreNotify.STDI_SAVE_TOTA_RQRD);
		validateIntRange(stdi.getTota(), 0, 99999999, CoreNotify.STDI_SAVE_TOTA_RNGE);
		
		validateDecRequired(stdi.getTime(), CoreNotify.STDI_SAVE_TIME_RQRD);
		validateDecRange(stdi.getTime(), 0, 9999999999.9999, CoreNotify.STDI_SAVE_TIME_RNGE);
		
		validateIntRequired(stdi.getTima(), CoreNotify.STDI_SAVE_TIMA_RQRD);
		validateIntRange(stdi.getTima(), 0, 99999999, CoreNotify.STDI_SAVE_TIMA_RNGE);
		
		validateIntRequired(stdi.getTimi(), CoreNotify.STDI_SAVE_TIMI_RQRD);
		validateIntRange(stdi.getTimi(), 0, 99999999, CoreNotify.STDI_SAVE_TIMI_RNGE);
		
		validateIntRange(stdi.getNuer(), 0, 99999999, CoreNotify.STDI_SAVE_NUER_RNGE);		
	}

}
