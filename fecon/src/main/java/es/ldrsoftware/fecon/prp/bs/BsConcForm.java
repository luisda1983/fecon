package es.ldrsoftware.fecon.prp.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.bs.BsHconList;
import es.ldrsoftware.fecon.cnt.bs.BsHconListArea;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.LiteData;
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

	@Autowired
	BsHconList bsHconList;
	
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
				switch (area.IN.tipo) {
				case LiteData.LT_EL_CONCTIPO_AMBOS:
					 break;
				case LiteData.LT_EL_CONCTIPO_INGRESO:
					 BsHconListArea bsHconListArea_1 = new BsHconListArea();
					 bsHconListArea_1.IN.tipo = BsHconList.LT_TIPO_CONC_NEGA;
					 bsHconListArea_1.IN.cate = conc.getCate();
					 bsHconListArea_1.IN.conc = conc.getIden();
					 bsHconList.executeBS(bsHconListArea_1);
					 
					 if (bsHconListArea_1.OUT.hconList != null && bsHconListArea_1.OUT.hconList.size() > 0) {
						 notify(AppNotify.CONC_FORM_TIPO_DIFF);
					 }
					 break;
				case LiteData.LT_EL_CONCTIPO_GASTO:
					 BsHconListArea bsHconListArea_2 = new BsHconListArea();
					 bsHconListArea_2.IN.tipo = BsHconList.LT_TIPO_CONC_POSI;
					 bsHconListArea_2.IN.cate = conc.getCate();
					 bsHconListArea_2.IN.conc = conc.getIden();
					 bsHconList.executeBS(bsHconListArea_2);
					 
					 if (bsHconListArea_2.OUT.hconList != null && bsHconListArea_2.OUT.hconList.size() > 0) {
						 notify(AppNotify.CONC_FORM_TIPO_DIFF);
					 }
					 break;
				}
			}
			
			conc.setTipo(area.IN.tipo);
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
