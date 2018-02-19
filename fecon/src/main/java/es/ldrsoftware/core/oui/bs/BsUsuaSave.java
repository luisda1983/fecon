//Normalizado
package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Usua;
import es.ldrsoftware.core.oui.entity.UsuaDAO;

@Component
public class BsUsuaSave extends BaseBS {

	@Autowired
	private UsuaDAO usuaDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaSaveArea area = (BsUsuaSaveArea)a;
		
		Usua usua = usuaDao.save(area.IN.usua);
		
		area.OUT.usua = usua;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaSaveArea area = (BsUsuaSaveArea)a;
		
		validateDto(area.IN.usua, CoreNotify.USUA_SAVE_USUA_RQRD);
		
		Usua usua = area.IN.usua;
		
		validateStringRequired(usua.getIden(), CoreNotify.USUA_SAVE_IDEN_RQRD);
		validateStringMaxLength(usua.getIden(), 30, CoreNotify.USUA_SAVE_IDEN_MAXL);
		
		validateStringRequired(usua.getPerf(), CoreNotify.USUA_SAVE_PERF_RQRD);
		validateStringDomain(CoreNotify.USUA_SAVE_PERF_ERRO, usua.getPerf(), LiteData.LT_ST_USUAPERF);
		
		validateStringRequired(usua.getMail(), CoreNotify.USUA_SAVE_MAIL_RQRD);
		validateStringMaxLength(usua.getMail(), 100, CoreNotify.USUA_SAVE_MAIL_MAXL);
		
		validateStringRequired(usua.getPass(), CoreNotify.USUA_SAVE_PASS_RQRD);
		validateStringMaxLength(usua.getPass(), 30, CoreNotify.USUA_SAVE_PASS_MAXL);
		
		validateStringRequired(usua.getActi(), CoreNotify.USUA_SAVE_ACTI_RQRD);
		validateStringDomain(CoreNotify.USUA_SAVE_ACTI_ERRO, usua.getActi(), LiteData.LT_ST_BOOL);
				
		validateIntRequired(usua.getFeal(), CoreNotify.USUA_SAVE_FEAL_RQRD);
		validateIntRange(usua.getFeal(), 19000101, 29991231, CoreNotify.USUA_SAVE_FEAL_RNGE);
		
		validateIntRange(usua.getHoal(), 000000, 235959, CoreNotify.USUA_SAVE_HOAL_RNGE);
		
		validateIntRequired(usua.getFeul(), CoreNotify.USUA_SAVE_FEUL_RQRD);
		validateIntRange(usua.getFeul(), 19000101, 29991231, CoreNotify.USUA_SAVE_FEUL_RNGE);
		
		validateIntRange(usua.getHoul(), 000000, 235959, CoreNotify.USUA_SAVE_HOUL_RNGE);

	}
}