package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.entity.Ctrl;
import es.ldrsoftware.core.fwk.entity.CtrlDAO;

@Component
public class BsCtrlGet extends BaseBS {

	@Autowired
	CtrlDAO ctrlDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsCtrlGetArea area = (BsCtrlGetArea)a;
		
		Ctrl ctrl = ctrlDao.getByIden(area.IN.iden);
		
		if (ctrl == null) {
			notify(CoreNotify.CTRL_NF);
		}
		
		area.OUT.ctrl = ctrl;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCtrlGetArea area = (BsCtrlGetArea)a;
		
		if (area.IN.iden == null || "".equals(area.IN.iden)) { notify(CoreNotify.CTRL_IDEN_RQRD); }
		
	}

}
