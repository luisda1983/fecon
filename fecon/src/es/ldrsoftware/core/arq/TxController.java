package es.ldrsoftware.core.arq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.Session;
import es.ldrsoftware.core.fwk.bs.BsSesiGet;
import es.ldrsoftware.core.fwk.bs.BsSesiGetArea;
import es.ldrsoftware.core.fwk.entity.Sesi;
import es.ldrsoftware.core.sts.bs.BsStstSave;
import es.ldrsoftware.core.sts.bs.BsStstSaveArea;

@Component
public class TxController extends BaseNotifyManager {

	@Autowired
	private BsSesiGet bsSesiGet;

	@Autowired
	private BsStstSave bsStstSave;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseController ctrl, BaseBSArea a) throws Exception {
		ctrl.execute(a);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void loadSession(long iden) throws Exception {
		
		BsSesiGetArea area = new BsSesiGetArea();
		area.IN.iden = iden;
		bsSesiGet.executeBS(area);
		Sesi sesi = area.OUT.sesi;
		SESSION.get().perf = sesi.getPerf();
		SESSION.get().inst = sesi.getInst();
		SESSION.get().usua = sesi.getUsua();
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void doStst(String ctrl, long inej, long fiej) throws Exception {
		BsStstSaveArea area = new BsStstSaveArea();
		area.IN.ctrl = ctrl;
		area.IN.inej = inej;
		area.IN.fiej = fiej;
		area.IN.reej = SESSION.get().EXEC_STATE;
				
		if (SESSION.get().EXEC_STATE == Session.EXEC_STATE_VOID) {
			area.IN.notf = SESSION.get().EXEC_VOID.getIden();
		} else if (SESSION.get().EXEC_STATE == Session.EXEC_STATE_OVER) {
			area.IN.notf = SESSION.get().EXEC_OVER_LIST.get(0).getIden();
		} else if (SESSION.get().EXEC_STATE == Session.EXEC_STATE_INFO) {
			area.IN.notf = SESSION.get().EXEC_INFO_LIST.get(0).getIden();
		} else {
			area.IN.notf = "";
		}
			
		bsStstSave.executeBS(area);
	}
}
