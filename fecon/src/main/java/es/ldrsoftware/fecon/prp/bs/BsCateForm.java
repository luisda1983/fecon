package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.prp.entity.Cate;

@Component
public class BsCateForm extends BaseBS {

	@Autowired
	BsCateGetd bsCateGetd;

	@Autowired
	BsCateGetk bsCateGetk;

	@Autowired
	BsCateGeto bsCateGeto;
	
	@Autowired
	BsCateSave bsCateSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCateFormArea area = (BsCateFormArea)a;
		
		//Nueva categoria
		if (!testInt(area.IN.iden)) {
			
			//Validamos si existe categoria con la misma descripci�n larga o corta
			BsCateGetdArea bsCateGetdArea = new BsCateGetdArea();
			bsCateGetdArea.IN.desl = area.IN.desl;
			bsCateGetdArea.IN.desc = area.IN.desc;
			bsCateGetd.execute(bsCateGetdArea);
			
			if (bsCateGetdArea.OUT.cateDesl != null) {
				notify(AppNotify.CATE_FORM_DESL_DP);
			}
			
			if (bsCateGetdArea.OUT.cateDesc != null) {
				notify(AppNotify.CATE_FORM_DESC_DP);
			}
			Cate cate = new Cate();
			
			cate.setInst(SESSION.get().inst);
			cate.setDesl(area.IN.desl);
			cate.setDesc(area.IN.desc);
			cate.setPres(area.IN.pres);
			
			//Obtener el orden que toque
			BsCateGetoArea bsCateGetoArea = new BsCateGetoArea();
			bsCateGeto.execute(bsCateGetoArea);
			
			cate.setOrde(bsCateGetoArea.OUT.orde);
			
			BsCateSaveArea bsCateSaveArea = new BsCateSaveArea();
			bsCateSaveArea.IN.cate = cate;
			bsCateSave.executeBS(bsCateSaveArea);
			
			area.OUT.cate = bsCateSaveArea.OUT.cate;
			
		//Edici�n de categoria
		} else {
			BsCateGetkArea bsCateGetkArea = new BsCateGetkArea();
			bsCateGetkArea.IN.iden = area.IN.iden;
			bsCateGetk.executeBS(bsCateGetkArea);
			
			Cate cate = bsCateGetkArea.OUT.cate;
			
			validateDtoRequired(cate, AppNotify.CATE_FORM_CATE_NF);

			cate.setDesl(area.IN.desl);
			cate.setDesc(area.IN.desc);


			if (!area.IN.pres.equals(cate.getPres())) {
				//TODO: para permitir cambiar el indicar de permitir presupuesto a nivel de categoria
				//      se debe validar que no haya ninguna partida abierta a nivel de categoria
				//				
			}
			
			BsCateSaveArea bsCateSaveArea = new BsCateSaveArea();
			bsCateSaveArea.IN.cate = cate;
			bsCateSave.executeBS(bsCateSaveArea);
			
			area.OUT.cate = bsCateSaveArea.OUT.cate;
		}
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCateFormArea area = (BsCateFormArea)a;
		
		validateStringRequired(area.IN.desl, AppNotify.CATE_FORM_DESL_RQRD);
		validateStringRequired(area.IN.desc, AppNotify.CATE_FORM_DESC_RQRD);
		validateStringRequired(area.IN.pres, AppNotify.CATE_FORM_PRES_RQRD);
	}

}
