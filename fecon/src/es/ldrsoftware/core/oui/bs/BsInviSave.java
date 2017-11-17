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
	private InviDAO inviDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInviSaveArea area = (BsInviSaveArea)a;
		
		Invi invi = inviDao.save(area.IN.invi);
		
		area.OUT.invi = invi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviSaveArea area = (BsInviSaveArea)a;
		
		validateDto(area.IN.invi, CoreNotify.INVI_SAVE_INVI_RQRD);
		
		Invi invi = area.IN.invi;
		
		validateStringRequired(invi.getIden(), CoreNotify.INVI_SAVE_IDEN_RQRD);
		validateStringMaxLength(invi.getIden(), 20, CoreNotify.INVI_SAVE_IDEN_MAXL);
		
		validateStringRequired(invi.getTipo(), CoreNotify.INVI_SAVE_TIPO_RQRD);
		
		//if (!"I".equals(invi.getTipo()) &&
		//	!"U".equals(invi.getTipo())) {
		if (!LiteData.LT_EL_INVITIPO_INSTALACION.equals(invi.getTipo())) {
			notify(CoreNotify.INVI_SAVE_TIPO_ERRO);
		}
		
		validateStringRequired(invi.getEsta(), CoreNotify.INVI_SAVE_ESTA_RQRD);
		
//		if (!"S".equals(invi.getEsta()) &&
//			!"E".equals(invi.getEsta()) &&
//			!"A".equals(invi.getEsta()) &&
//			!"R".equals(invi.getEsta()) &&
//			!"C".equals(invi.getEsta()) &&
//			!"F".equals(invi.getEsta())) {
		if (!LiteData.LT_EL_INVIESTA_SOLICITADA.equals(invi.getEsta()) &&
			!LiteData.LT_EL_INVIESTA_ACEPTADA.equals(invi.getEsta())   &&
			!LiteData.LT_EL_INVIESTA_RECHAZADA.equals(invi.getEsta())  &&
			!LiteData.LT_EL_INVIESTA_FINALIZADA.equals(invi.getEsta())) {
			notify(CoreNotify.INVI_SAVE_ESTA_ERRO);
		}
		
		validateStringRequired(invi.getMail(), CoreNotify.INVI_SAVE_MAIL_RQRD);
		validateStringMaxLength(invi.getMail(), 100, CoreNotify.INVI_SAVE_MAIL_MAXL);
		
		if (LiteData.LT_EL_INVIESTA_FINALIZADA.equals(invi.getEsta())) {
			validateIntRequired(invi.getInst(), CoreNotify.INVI_SAVE_INST_RQRD);
			//FIXME: Longitud del 99999...
			validateIntRange(invi.getInst(), 1, 999999999, CoreNotify.INVI_SAVE_INST_RNGE);
			validateStringRequired(invi.getUsua(), CoreNotify.INVI_SAVE_USUA_RQRD);
			validateStringMaxLength(invi.getUsua(), 30, CoreNotify.INVI_SAVE_USUA_MAXL);
		} else {
			validateIntEmpty(invi.getInst(), CoreNotify.INVI_SAVE_INST_MPTY);
			validateStringEmpty(invi.getUsua(), CoreNotify.INVI_SAVE_USUA_MPTY);
		}
		
		
//		if ("U".equals(invi.getTipo())) {
//			validateIntRequired(invi.getInst(), CoreNotify.INVI_SAVE_INST_RQRD);
//			validateIntRange(invi.getInst(), 1, 9999999999, CoreNotify.INVI_SAVE_INST_RNGE);
//		}

		
		validateIntRequired(invi.getFeal(), CoreNotify.INVI_SAVE_FEAL_RQRD);
		validateIntRange(invi.getFeal(), 19000101, 29991231, CoreNotify.INVI_SAVE_FEAL_RNGE);
		
		validateIntRange(invi.getHoal(), 000000, 235959, CoreNotify.INVI_SAVE_HOAL_RNGE);
		
		
		if (LiteData.LT_EL_INVIESTA_SOLICITADA.equals(invi.getEsta()) ||
			LiteData.LT_EL_INVIESTA_ENVIADA.equals(invi.getEsta())) {
			validateIntEmpty(invi.getFemo(), CoreNotify.INVI_SAVE_FEMO_MPTY);
			validateIntEmpty(invi.getHomo(), CoreNotify.INVI_SAVE_HOMO_MPTY);
		} else {
			validateIntRequired(invi.getFemo(), CoreNotify.INVI_SAVE_FEMO_RQRD);
			validateIntRange(invi.getFemo(), 19000101, 29991231, CoreNotify.INVI_SAVE_FEMO_RNGE);
			validateIntRange(invi.getHomo(), 000000, 235959, CoreNotify.INVI_SAVE_HOMO_RNGE);
		}
	}
}