package es.ldrsoftware.core.mnu.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.mnu.entity.Ctmn;

@Component
public class BsCtmnActi extends BaseBS {

	@Autowired
	BsCtmnGetk bsCtmnGetk;
	
	@Autowired
	BsCtmnSave bsCtmnSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCtmnActiArea area = (BsCtmnActiArea)a;

		//Obtenemos la categoría de menú
		BsCtmnGetkArea bsCtmnGetkArea = new BsCtmnGetkArea();
		bsCtmnGetkArea.IN.iden = area.IN.iden;
		bsCtmnGetk.executeBS(bsCtmnGetkArea);
		
		Ctmn ctmn = bsCtmnGetkArea.OUT.ctmn;
		
		//Validamos que exista la categoria de menú
		validateDtoRequired(ctmn, CoreNotify.CTMN_ACTI_CTMN_NF);
		
		//Validamos que la categoría de menú se encuentre desactivada
		validateStringEqual(ctmn.getActi(), LiteData.LT_EL_BOOL_NO, CoreNotify.CTMN_ACTI_CTMN_DESA_NO);
		
		//Activamos la categoría de menú
		ctmn.setActi(LiteData.LT_EL_BOOL_SI);
		
		//Guardamos la categoría de menú
		BsCtmnSaveArea bsCtmnSaveArea = new BsCtmnSaveArea();
		bsCtmnSaveArea.IN.ctmn = ctmn;
		bsCtmnSave.executeBS(bsCtmnSaveArea);
				
		area.OUT.ctmn = bsCtmnSaveArea.OUT.ctmn;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCtmnActiArea area = (BsCtmnActiArea)a;

		//Se valida que el identificador de categoría de menú esté informado
		validateIntRequired(area.IN.iden, CoreNotify.CTMN_ACTI_IDEN_RQRD);
	}

}
