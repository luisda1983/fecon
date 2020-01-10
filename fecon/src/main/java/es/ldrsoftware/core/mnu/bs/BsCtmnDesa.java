package es.ldrsoftware.core.mnu.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.mnu.entity.Ctmn;

@Component
public class BsCtmnDesa extends BaseBS {

	@Autowired
	BsCtmnGetk bsCtmnGetk;
	
	@Autowired
	BsCtmnSave bsCtmnSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCtmnDesaArea area = (BsCtmnDesaArea)a;

		//Obtenemos la categoría de menú
		BsCtmnGetkArea bsCtmnGetkArea = new BsCtmnGetkArea();
		bsCtmnGetkArea.IN.iden = area.IN.iden;
		bsCtmnGetk.executeBS(bsCtmnGetkArea);
		
		Ctmn ctmn = bsCtmnGetkArea.OUT.ctmn;
		
		//Validamos que exista la categoria de menú
		validateDtoNotFound(ctmn, LiteData.LT_EL_DTO_CTMN, Ctmn.key(area.IN.iden));
		
		//Validamos que la categoría de menú no se encuentre desactivada
		test(true, ctmn.getActi(), LiteData.LT_EL_BOOL_NO, CoreNotify.CTMN_DESA_CTMN_ACTI_NO);
		
		//Desactivamos la categoría de menú
		ctmn.setActi(LiteData.LT_EL_BOOL_NO);
		
		//Guardamos la categoría de menú
		BsCtmnSaveArea bsCtmnSaveArea = new BsCtmnSaveArea();
		bsCtmnSaveArea.IN.ctmn = ctmn;
		bsCtmnSave.executeBS(bsCtmnSaveArea);
				
		area.OUT.ctmn = bsCtmnSaveArea.OUT.ctmn;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCtmnDesaArea area = (BsCtmnDesaArea)a;

		//Se valida que el identificador de categoría de menú esté informado
		validateInputField(area.IN.iden, Ctmn.IDEN);
	}

}
