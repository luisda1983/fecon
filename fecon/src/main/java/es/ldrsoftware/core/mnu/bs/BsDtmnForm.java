package es.ldrsoftware.core.mnu.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.mnu.entity.Dtmn;

@Component
public class BsDtmnForm extends BaseBS {

	@Autowired
	BsDtmnGetk bsDtmnGetk;
	
	@Autowired
	BsDtmnSave bsDtmnSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDtmnFormArea area = (BsDtmnFormArea)a;

		//Obtenemos el detalle de menú
		BsDtmnGetkArea bsDtmnGetkArea = new BsDtmnGetkArea();
		bsDtmnGetkArea.IN.ctmn = area.IN.ctmn;
		bsDtmnGetkArea.IN.iden = area.IN.iden;
		bsDtmnGetk.executeBS(bsDtmnGetkArea);
		
		Dtmn dtmn = bsDtmnGetkArea.OUT.dtmn;
		
		//Validamos que exista el detalle de menú
		validateDtoRequired(dtmn, CoreNotify.DTMN_FORM_DTMN_NF);
		
		//Se permite modificar: descripción e icono
		dtmn.setDesc(area.IN.desc);
		dtmn.setIcon(area.IN.icon);
		
		//Guardamos el detalle de menú
		BsDtmnSaveArea bsDtmnSaveArea = new BsDtmnSaveArea();
		bsDtmnSaveArea.IN.dtmn = dtmn;
		bsDtmnSave.executeBS(bsDtmnSaveArea);
				
		area.OUT.dtmn = bsDtmnSaveArea.OUT.dtmn;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDtmnFormArea area = (BsDtmnFormArea)a;

		//Se valida que el identificador de categoría esté informado
		validateIntRequired(area.IN.ctmn, CoreNotify.DTMN_FORM_CTMN_RQRD);
		//Se valida que el identificador de detalle de menú esté informado
		validateIntRequired(area.IN.iden, CoreNotify.DTMN_FORM_IDEN_RQRD);
	}

}
