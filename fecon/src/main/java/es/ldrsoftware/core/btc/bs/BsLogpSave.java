package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
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
		validateDtoRequired(area.IN.logp, CoreNotify.LOGP_SAVE_LOGP_RQRD);
		
		Logp logp = area.IN.logp;
		
		//Validamos que el tipo de log esté informado y tenga un valor permitido
		validateStringRequired(logp.getTipo(), CoreNotify.LOGP_SAVE_TIPO_RQRD);
		validateStringDomain(CoreNotify.LOGP_SAVE_TIPO_ERRO, logp.getTipo(), LiteData.LT_ST_LOGPTIPO);
		
		//Validamos que el identificador de log esté informado y no sea demasiado largo
		validateStringRequired(logp.getIden(), CoreNotify.LOGP_SAVE_IDEN_RQRD);
		validateStringMaxLength(logp.getIden(), 30, CoreNotify.LOGP_SAVE_IDEN_MAXL);
		
		//Validamos que la fecha de log esté informada y dentro de rango
		validateIntRequired(logp.getFech(), CoreNotify.LOGP_SAVE_FECH_RQRD);
		validateIntRange(logp.getFech(), 19000101, 29991231, CoreNotify.LOGP_SAVE_FECH_RNGE);
		
		//Validamos que la hora de log esté informado dentro de rango
		validateIntRange(logp.getHora(), 0000, 2359, CoreNotify.LOGP_SAVE_HORA_RNGE);
		
		//Validamos que la tabulación esté dentro de rango
		validateIntRange(logp.getTabs(), 0, 9, CoreNotify.LOGP_SAVE_TABS_RNGE);
		
		//Validamos que la información de log esté informada y no sea demasiado larga
		validateStringRequired(logp.getDato(), CoreNotify.LOGP_SAVE_DATO_RQRD);
		validateStringMaxLength(logp.getDato(), 500, CoreNotify.LOGP_SAVE_DATO_MAXL);		
	}

}
