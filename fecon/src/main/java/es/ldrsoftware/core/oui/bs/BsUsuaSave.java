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
	UsuaDAO usuaDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsUsuaSaveArea area = (BsUsuaSaveArea)a;
		
		//Guardamos el usuario
		Usua usua = usuaDao.save(area.IN.usua);
		
		area.OUT.usua = usua;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsUsuaSaveArea area = (BsUsuaSaveArea)a;
		
		Usua usua = area.IN.usua;
		
		//Validamos que el usuario está informado
		validateDtoRequired(usua, CoreNotify.USUA_SAVE_USUA_RQRD);
		
		//Validamos que el identificador de usuario esté informado y no exceda la longitud
		validateStringRequired(usua.getIden(), CoreNotify.USUA_SAVE_IDEN_RQRD);
		validateStringMaxLength(usua.getIden(), 30, CoreNotify.USUA_SAVE_IDEN_MAXL);
		
		//Validamos que el Email esté informado y no exceda la longitud
		validateStringRequired(usua.getMail(), CoreNotify.USUA_SAVE_MAIL_RQRD);
		validateStringMaxLength(usua.getMail(), 100, CoreNotify.USUA_SAVE_MAIL_MAXL);
		
		//Validamos que el password esté informado y no exceda la longitud
		validateStringRequired(usua.getPass(), CoreNotify.USUA_SAVE_PASS_RQRD);
		validateStringMaxLength(usua.getPass(), 30, CoreNotify.USUA_SAVE_PASS_MAXL);
		
		//Validamos que el indicador de Activo esté informado y dentro del dominio permitido
		validateStringRequired(usua.getActi(), CoreNotify.USUA_SAVE_ACTI_RQRD);
		validateStringDomain(CoreNotify.USUA_SAVE_ACTI_ERRO, usua.getActi(), LiteData.LT_ST_BOOL);
		
		//Validamos que la fecha de alta esté informada y dentro del rango permitido
		validateIntRequired(usua.getFeal(), CoreNotify.USUA_SAVE_FEAL_RQRD);
		validateIntRange(usua.getFeal(), 19000101, 29991231, CoreNotify.USUA_SAVE_FEAL_RNGE);
		
		//Validamos que la hora de alta esté dentro del rango permitido
		validateIntRange(usua.getHoal(), 000000, 235959, CoreNotify.USUA_SAVE_HOAL_RNGE);
		
		//Validamos que la fecha de última actividad esté informada y dentro del rango permitido
		validateIntRequired(usua.getFeul(), CoreNotify.USUA_SAVE_FEUL_RQRD);
		validateIntRange(usua.getFeul(), 19000101, 29991231, CoreNotify.USUA_SAVE_FEUL_RNGE);
		
		//Validamos que la hora de última actividad esté dentro del rango permitido
		validateIntRange(usua.getHoul(), 000000, 235959, CoreNotify.USUA_SAVE_HOUL_RNGE);

	}
}