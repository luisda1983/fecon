package es.ldrsoftware.core.arq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.btc.bs.BsLogpSave;
import es.ldrsoftware.core.btc.bs.BsLogpSaveArea;
import es.ldrsoftware.core.btc.entity.Logp;
import es.ldrsoftware.core.fwk.data.LiteData;

@Component
public class LoggerManager extends BaseNotifyManager {

	@Autowired
	BsLogpSave bsLogpSave;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void logBatch(int tabs, String text) throws Exception {
		Logp logp = new Logp();
		logp.setTipo(LiteData.LT_EL_LOGPTIPO_PASE);
		logp.setIden("BATCH");
		logp.setFech(SESSION.get().fbop);
		logp.setHora(SESSION.get().hbop);
		logp.setTabs(tabs);
		logp.setDato(text);
		
		BsLogpSaveArea bsLogpSaveArea = new BsLogpSaveArea();
		bsLogpSaveArea.IN.logp = logp;
		bsLogpSave.executeBS(bsLogpSaveArea);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void logProc(String iden, int tabs, String text) throws Exception {
		Logp logp = new Logp();
		logp.setTipo(LiteData.LT_EL_LOGPTIPO_PROCESO);
		logp.setIden(iden);
		logp.setFech(SESSION.get().fbop);
		logp.setHora(SESSION.get().hbop);
		logp.setTabs(tabs);
		logp.setDato(text);
		
		BsLogpSaveArea bsLogpSaveArea = new BsLogpSaveArea();
		bsLogpSaveArea.IN.logp = logp;
		bsLogpSave.executeBS(bsLogpSaveArea);
	}
}
