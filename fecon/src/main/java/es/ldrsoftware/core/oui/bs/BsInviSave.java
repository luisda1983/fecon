package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Invi;
import es.ldrsoftware.core.oui.entity.InviDAO;

@Component
public class BsInviSave extends BaseBS {

	@Autowired
	InviDAO inviDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInviSaveArea area = (BsInviSaveArea)a;
	
		//Guardamos la invitación
		Invi invi = inviDao.save(area.IN.invi);
		
		area.OUT.invi = invi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviSaveArea area = (BsInviSaveArea)a;
		
		Invi invi = area.IN.invi;
		
		//Validamos que la invitación está informada
		validateDtoRequired(invi, CoreNotify.INVI_SAVE_INVI_RQRD);
		
		//Validamos que el identificador de invitación esté informado y no exceda la longitud máxima
		validateStringRequired(invi.getIden(), CoreNotify.INVI_SAVE_IDEN_RQRD);
		validateStringMaxLength(invi.getIden(), 20, CoreNotify.INVI_SAVE_IDEN_MAXL);
		
		//Validamos que el tipo de invitación esté informado y dentro del dominio permitido
		validateStringRequired(invi.getTipo(), CoreNotify.INVI_SAVE_TIPO_RQRD);
		validateStringDomain(CoreNotify.INVI_SAVE_TIPO_ERRO, invi.getTipo(), LiteData.LT_ST_INVITIPO);
		
		//Validamos que el estado de la invitación esté informado y dentro del dominio permitido
		validateStringRequired(invi.getEsta(), CoreNotify.INVI_SAVE_ESTA_RQRD);
		validateStringDomain(CoreNotify.INVI_SAVE_ESTA_ERRO, invi.getEsta(), LiteData.LT_ST_INVIESTA);

		//Validamos que el email de la invitación esté informado y no exceda la longitud máxima
		validateStringRequired(invi.getMail(), CoreNotify.INVI_SAVE_MAIL_RQRD);
		validateStringMaxLength(invi.getMail(), 100, CoreNotify.INVI_SAVE_MAIL_MAXL);
		
		//Si la invitación es de tipo usuario, validamos que el identificador de instalación esté informado y dentro de rango
		if (LiteData.LT_EL_INVITIPO_USUARIO.equals(invi.getTipo())) {
			validateIntRequired(invi.getInst(), CoreNotify.INVI_SAVE_INST_RQRD);
			validateIntRange(invi.getInst(), 1, 999999999, CoreNotify.INVI_SAVE_INST_RNGE);
		}

		//Si la invitación está finalizada, validamos que el identificador de instalación y el usuario estén informado y dentro de rango
		if (LiteData.LT_EL_INVIESTA_FINALIZADA.equals(invi.getEsta())) {
			validateIntRequired(invi.getInst(), CoreNotify.INVI_SAVE_INST_RQRD);
			//FIXME: Longitud del 99999...
			validateIntRange(invi.getInst(), 1, 999999999, CoreNotify.INVI_SAVE_INST_RNGE);
			validateStringRequired(invi.getUsua(), CoreNotify.INVI_SAVE_USUA_RQRD);
			validateStringMaxLength(invi.getUsua(), 30, CoreNotify.INVI_SAVE_USUA_MAXL);
		} else {
			//Si la invitación no está finalizada y es de tipo instalación, no se permite el identificador de instalación
			if (LiteData.LT_EL_INVITIPO_INSTALACION.equals(invi.getTipo())) {
				validateIntEmpty(invi.getInst(), CoreNotify.INVI_SAVE_INST_MPTY);
			}
			//Si la invitación no está finalizada, no se permite el usuario
			validateStringEmpty(invi.getUsua(), CoreNotify.INVI_SAVE_USUA_MPTY);
		}

		//Validamos que la fecha de alta esté informada y dentro de rango
		validateIntRequired(invi.getFeal(), CoreNotify.INVI_SAVE_FEAL_RQRD);
		validateIntRange(invi.getFeal(), 19000101, 29991231, CoreNotify.INVI_SAVE_FEAL_RNGE);
		
		//Validamos el rango de la hora de alta
		validateIntRange(invi.getHoal(), 000000, 235959, CoreNotify.INVI_SAVE_HOAL_RNGE);
		
		//Si la invitación está enviada o solicitada, no se permite fecha y hora de modificación
		if (LiteData.LT_EL_INVIESTA_SOLICITADA.equals(invi.getEsta()) ||
			LiteData.LT_EL_INVIESTA_ENVIADA.equals(invi.getEsta())) {
			validateIntEmpty(invi.getFemo(), CoreNotify.INVI_SAVE_FEMO_MPTY);
			validateIntEmpty(invi.getHomo(), CoreNotify.INVI_SAVE_HOMO_MPTY);
		} else {
		//Si la invitación no está enviada o solicitada, validamos que la fecha y hora de modificación estén informados y dentro de rango
			validateIntRequired(invi.getFemo(), CoreNotify.INVI_SAVE_FEMO_RQRD);
			validateIntRange(invi.getFemo(), 19000101, 29991231, CoreNotify.INVI_SAVE_FEMO_RNGE);
			validateIntRange(invi.getHomo(), 000000, 235959, CoreNotify.INVI_SAVE_HOMO_RNGE);
		}
	}
}