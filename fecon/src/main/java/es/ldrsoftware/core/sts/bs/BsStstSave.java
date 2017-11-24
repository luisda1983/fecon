package es.ldrsoftware.core.sts.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.sts.entity.Stst;
import es.ldrsoftware.core.sts.entity.StstDao;

@Component
public class BsStstSave extends BaseBS {

	@Autowired
	private StstDao ststDao;

	protected void execute(BaseBSArea a) throws Exception {
		BsStstSaveArea area = (BsStstSaveArea)a;
		
		Stst stst = new Stst();
		stst.setCtrl(area.IN.ctrl);
		stst.setInst(SESSION.get().inst);
		stst.setUsua(SESSION.get().usua);
		stst.setFeej(SESSION.get().feop);
		stst.setHoej(SESSION.get().hoop);
		stst.setTiej(new Long(area.IN.fiej - area.IN.inej).intValue());
		stst.setReej(area.IN.reej);
		stst.setNotf(area.IN.notf);
		
		ststDao.save(stst);
		
		area.OUT.stst = stst;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsStstSaveArea area = (BsStstSaveArea)a;
		
		if (area.IN.ctrl == null || "".equals(area.IN.ctrl)) { notify(CoreNotify.STST_SAVE_CTRL_RQRD); }
		if (area.IN.inej == 0) { notify(CoreNotify.STST_SAVE_INEJ_RQRD); }
		if (area.IN.fiej == 0) { notify(CoreNotify.STST_SAVE_FIEJ_RQRD); }
		if (area.IN.inej > area.IN.fiej) { notify(CoreNotify.STST_SAVE_INEJ_GT_FIEJ); }
		
		if (area.IN.reej == null ) { area.IN.reej = ""; }
		
		if ("".equals(area.IN.reej)) {
			if (area.IN.notf == null || "".equals(area.IN.notf)) {
				notify(CoreNotify.STST_SAVE_NOTF_RQRD); 
			}
		}
	}

}
