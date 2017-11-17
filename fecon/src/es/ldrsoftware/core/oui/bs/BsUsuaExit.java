package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.bs.BsSesiExit;
import es.ldrsoftware.core.fwk.bs.BsSesiExitArea;

@Component
public class BsUsuaExit extends BaseBS {

	@Autowired
	private BsSesiExit bsSesiExit;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaExitArea area = (BsUsuaExitArea)a;
		
		BsSesiExitArea bsSesiExitArea = new BsSesiExitArea();
		bsSesiExitArea.IN.iden = area.IN.sesi;
		bsSesiExit.executeBS(bsSesiExitArea);
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaExitArea area = (BsUsuaExitArea)a;
		
		if (area.IN.sesi == 0) { notify(CoreNotify.USUA_EXIT_SESI_RQRD); }
		
	}
}