package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.InstDAO;

@Component
public class BsInstGet extends BaseBS {
	
	@Autowired
	public InstDAO instDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInstGetArea area = (BsInstGetArea)a;
		
		Inst inst = instDao.getByIden(area.IN.iden);
		
		area.OUT.inst = inst;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInstGetArea area = (BsInstGetArea)a;
		
		if (area.IN.iden == 0) { notify(CoreNotify.INST_GETI_IDEN_RQRD); }
		
	}
}