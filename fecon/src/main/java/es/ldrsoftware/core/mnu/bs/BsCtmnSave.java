package es.ldrsoftware.core.mnu.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.mnu.entity.Ctmn;
import es.ldrsoftware.core.mnu.entity.CtmnDAO;

@Component
public class BsCtmnSave extends BaseBS {

	@Autowired
	CtmnDAO ctmnDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCtmnSaveArea area = (BsCtmnSaveArea)a;

		//Guardamos la categoría de menú
		Ctmn ctmn = ctmnDao.save(area.IN.ctmn);
		
		area.OUT.ctmn = ctmn;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCtmnSaveArea area = (BsCtmnSaveArea)a;

		Ctmn ctmn = area.IN.ctmn;
		
		//Validamos que la categoría de menú está informada
		validateDtoRequired(ctmn, CoreNotify.CTMN_SAVE_CTMN_RQRD);
		
		//Se valida que el identificador de categoría de menú esté informado y dentro de rango
		validateIntRequired(ctmn.getIden(), CoreNotify.CTMN_SAVE_IDEN_RQRD);
		validateIntRange(ctmn.getIden(), 0, 9999, CoreNotify.CTMN_SAVE_IDEN_RNGE);
		
		//Se valida que el perfil esté informado y dentro del dominio permitido
		validateStringRequired(ctmn.getPerf(), CoreNotify.CTMN_SAVE_PERF_RQRD);
		validateStringDomain(CoreNotify.CTMN_SAVE_PERF_ERRO, ctmn.getPerf(), LiteData.LT_ST_MENUPERF);
		
		//Se valida que la descripción esté informada y no exceda la longitud
		validateStringRequired(ctmn.getDesc(), CoreNotify.CTMN_SAVE_DESC_RQRD);
		validateStringMaxLength(ctmn.getDesc(), 40, CoreNotify.CTMN_SAVE_DESC_MAXL);
		
		//Se valida que el indicador de activada esté informado y dentro del dominio permitido
		validateStringRequired(ctmn.getActi(), CoreNotify.CTMN_SAVE_ACTI_RQRD);
		validateStringDomain(CoreNotify.CTMN_SAVE_ACTI_ERRO, ctmn.getActi(), LiteData.LT_ST_BOOL);
		
		//Se valida que el orden esté informado y dentro de rango
		validateIntRequired(ctmn.getOrde(), CoreNotify.CTMN_SAVE_ORDE_RQRD);
		validateIntRange(ctmn.getOrde(), 0, 9999, CoreNotify.CTMN_SAVE_ORDE_RNGE);
	}

}
