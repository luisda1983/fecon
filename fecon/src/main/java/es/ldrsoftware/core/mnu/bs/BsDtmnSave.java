package es.ldrsoftware.core.mnu.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.mnu.entity.Dtmn;
import es.ldrsoftware.core.mnu.entity.DtmnDAO;

@Component
public class BsDtmnSave extends BaseBS {

	@Autowired
	DtmnDAO dtmnDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDtmnSaveArea area = (BsDtmnSaveArea)a;

		//Guardamos el detalle de menú
		Dtmn dtmn = dtmnDao.save(area.IN.dtmn);
		
		area.OUT.dtmn = dtmn;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDtmnSaveArea area = (BsDtmnSaveArea)a;

		Dtmn dtmn = area.IN.dtmn;
		
		//Validamos que el detalle de menú esté informado
		validateDtoRequired(dtmn, CoreNotify.DTMN_SAVE_DTMN_RQRD);
		
		//Se valida que el identificador de categoría de menú esté informado y dentro de rango
		validateIntRequired(dtmn.getCtmn(), CoreNotify.DTMN_SAVE_CTMN_RQRD);
		validateIntRange(dtmn.getCtmn(), 0, 9999, CoreNotify.DTMN_SAVE_CTMN_RNGE);
		
		//Se valida que el identificador de detalle de menú esté informado y dentro de rango
		validateIntRequired(dtmn.getIden(), CoreNotify.DTMN_SAVE_IDEN_RQRD);
		validateIntRange(dtmn.getIden(), 0, 9999, CoreNotify.DTMN_SAVE_IDEN_RNGE);
		
		//Se valida que la descripción esté informada y no exceda la longitud
		validateStringRequired(dtmn.getDesc(), CoreNotify.DTMN_SAVE_DESC_RQRD);
		validateStringMaxLength(dtmn.getDesc(), 40, CoreNotify.DTMN_SAVE_DESC_MAXL);
		
		//Se valida que el indicador de activado esté informado y dentro del dominio permitido
		validateStringRequired(dtmn.getActi(), CoreNotify.DTMN_SAVE_ACTI_RQRD);
		validateStringDomain(CoreNotify.DTMN_SAVE_ACTI_ERRO, dtmn.getActi(), LiteData.LT_ST_BOOL);
		
		//Se valida que el orden esté informado y dentro de rango
		validateIntRequired(dtmn.getOrde(), CoreNotify.DTMN_SAVE_ORDE_RQRD);
		validateIntRange(dtmn.getOrde(), 0, 9999, CoreNotify.DTMN_SAVE_ORDE_RNGE);
		
		//Se valida que el path esté informado y no exceda la longitud
		validateStringRequired(dtmn.getPath(), CoreNotify.DTMN_SAVE_PATH_RQRD);
		validateStringMaxLength(dtmn.getPath(), 30, CoreNotify.DTMN_SAVE_PATH_MAXL);
		
		//Se valida que el icono esté informado y no exceda la longitud
		validateStringRequired(dtmn.getIcon(), CoreNotify.DTMN_SAVE_ICON_RQRD);
		validateStringMaxLength(dtmn.getIcon(), 50, CoreNotify.DTMN_SAVE_ICON_MAXL);
	}

}
