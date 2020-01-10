package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Logp;
import es.ldrsoftware.core.btc.entity.LogpDAO;
import es.ldrsoftware.core.fwk.data.LiteData;

@Component
public class BsLogpSave extends BaseBS {

	@Autowired
	LogpDAO logpDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsLogpSaveArea area = (BsLogpSaveArea)a;
		
		//Grabamos el log de proceso
		Logp logp = logpDao.save(area.IN.logp);
		
		area.OUT.logp = logp;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsLogpSaveArea area = (BsLogpSaveArea)a;
		
		//Validamos que está informado el Log
		Logp logp = (Logp)validateDto(area.IN.logp, LiteData.LT_EL_DTO_LOGP);
		logp.validate();
	}
}
