package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.prp.entity.Cate;
import es.ldrsoftware.fecon.prp.entity.Conc;

@Component
public class BsConcForm extends BaseBS {

	@Autowired
	BsConcGetd bsConcGetd;

	@Autowired
	BsConcGetk bsConcGetk;

	@Autowired
	BsConcGeto bsConcGeto;
	
	@Autowired
	BsConcSave bsConcSave;
	
	@Autowired
	BsCateGetk bsCateGetk;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsConcFormArea area = (BsConcFormArea)a;
		
		//Nuevo concepto
		if (!testInt(area.IN.iden)) {
			
			//Validamos que exista la categor�a
			BsCateGetkArea bsCateGetkArea = new BsCateGetkArea();
			bsCateGetkArea.IN.iden = area.IN.cate;
			bsCateGetk.execute(bsCateGetkArea);
			
			Cate cate = bsCateGetkArea.OUT.cate;
			
			validateDtoRequired(cate, AppNotify.CONC_FORM_CATE_NF);
			
			//Validamos que no exista ningún concepto con la misma descripción y la misma categoría
			BsConcGetdArea bsConcGetdArea = new BsConcGetdArea();
			bsConcGetdArea.IN.cate = area.IN.cate;
			bsConcGetdArea.IN.desl = area.IN.desl;
			bsConcGetdArea.IN.desc = area.IN.desc;
			bsConcGetd.execute(bsConcGetdArea);
			
			if (bsConcGetdArea.OUT.concDesl != null) {
				notify(AppNotify.CONC_FORM_DESL_DP);
			}
			
			if (bsConcGetdArea.OUT.concDesc != null) {
				notify(AppNotify.CONC_FORM_DESC_DP);
			}
			
			Conc conc = new Conc();
			
			conc.setCate(area.IN.cate);
			conc.setInst(SESSION.get().inst);
			conc.setTipo(area.IN.tipo);
			conc.setDesl(area.IN.desl);
			conc.setDesc(area.IN.desc);
			
			//Obtener el orden que toque
			BsConcGetoArea bsConcGetoArea = new BsConcGetoArea();
			bsConcGetoArea.IN.cate = area.IN.cate;
			bsConcGeto.execute(bsConcGetoArea);
			
			int orde = bsConcGetoArea.OUT.orde;
			
			conc.setOrde(orde);
			
			BsConcSaveArea bsConcSaveArea = new BsConcSaveArea();
			bsConcSaveArea.IN.conc = conc;
			bsConcSave.executeBS(bsConcSaveArea);
			
			conc = bsConcSaveArea.OUT.conc;
			
			area.OUT.conc = conc;
			
		//Edición de categoria
		} else {
			BsConcGetkArea bsConcGetkArea = new BsConcGetkArea();
			bsConcGetkArea.IN.iden = area.IN.iden;
			bsConcGetk.executeBS(bsConcGetkArea);
			
			Conc conc = bsConcGetkArea.OUT.conc;
			
			validateDtoRequired(conc, AppNotify.CONC_FORM_CONC_NF);

			if (area.IN.cate != conc.getCate()) {
				notify(AppNotify.CONC_FORM_CATE_DIFF);
			}
			
			if (area.IN.tipo != conc.getTipo()) {
				//TODO: se pueden permitir ciertos cambios:
				// - De Ingreso/Gasto a ambos
				// - De ambos a ingreso/gasto: si no hay ningun apunte para el concepto con signo contrario
				// - De ingreso a gasto (y viceversa): si no hay ning�n apunte para el concepto
				notify(AppNotify.CONC_FORM_TIPO_DIFF);
			}
			
			conc.setDesl(area.IN.desl);
			conc.setDesc(area.IN.desc);
						
			BsConcSaveArea bsConcSaveArea = new BsConcSaveArea();
			bsConcSaveArea.IN.conc = conc;
			bsConcSave.executeBS(bsConcSaveArea);
			
			conc = bsConcSaveArea.OUT.conc;
			
			area.OUT.conc = conc;
		}
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsConcFormArea area = (BsConcFormArea)a;
		
		validateIntRequired(area.IN.cate, AppNotify.CONC_FORM_CATE_RQRD);
		validateStringRequired(area.IN.tipo, AppNotify.CONC_FORM_TIPO_RQRD);
		validateStringRequired(area.IN.desl, AppNotify.CONC_FORM_DESL_RQRD);
		validateStringRequired(area.IN.desc, AppNotify.CONC_FORM_DESC_RQRD);
	}

}
