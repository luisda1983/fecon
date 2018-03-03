package es.ldrsoftware.core.mnu.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.mnu.entity.Ctmn;

@Component
public class BsCtmnForm extends BaseBS {

	@Autowired
	BsCtmnGetk bsCtmnGetk;
	
	@Autowired
	BsCtmnSave bsCtmnSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCtmnFormArea area = (BsCtmnFormArea)a;

		//Obtenemos la categoría de menú
		BsCtmnGetkArea bsCtmnGetkArea = new BsCtmnGetkArea();
		bsCtmnGetkArea.IN.iden = area.IN.iden;
		bsCtmnGetk.executeBS(bsCtmnGetkArea);
		
		Ctmn ctmn = bsCtmnGetkArea.OUT.ctmn;
		
		//Validamos que exista la categoria de menú
		validateDto(ctmn, CoreNotify.CTMN_FORM_CTMN_NF);
		
		//El único campo modificable es la descripción
		ctmn.setDesc(area.IN.desc);
		
		//Guardamos la categoría de menú
		BsCtmnSaveArea bsCtmnSaveArea = new BsCtmnSaveArea();
		bsCtmnSaveArea.IN.ctmn = ctmn;
		bsCtmnSave.executeBS(bsCtmnSaveArea);
				
		area.OUT.ctmn = bsCtmnSaveArea.OUT.ctmn;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCtmnFormArea area = (BsCtmnFormArea)a;

		//Se valida que el identificador de categoría de menú esté informado
		validateIntRequired(area.IN.iden, CoreNotify.CTMN_FORM_IDEN_RQRD);
	}

}
