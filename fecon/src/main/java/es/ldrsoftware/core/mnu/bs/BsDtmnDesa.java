package es.ldrsoftware.core.mnu.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.mnu.entity.Dtmn;

@Component
public class BsDtmnDesa extends BaseBS {

	@Autowired
	BsDtmnGetk bsDtmnGetk;
	
	@Autowired
	BsDtmnSave bsDtmnSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDtmnDesaArea area = (BsDtmnDesaArea)a;

		//Obtenemos el detalle de menú
		BsDtmnGetkArea bsDtmnGetkArea = new BsDtmnGetkArea();
		bsDtmnGetkArea.IN.ctmn = area.IN.ctmn;
		bsDtmnGetkArea.IN.iden = area.IN.iden;
		bsDtmnGetk.executeBS(bsDtmnGetkArea);
		
		Dtmn dtmn = bsDtmnGetkArea.OUT.dtmn;
		
		//Validamos que exista el detalle de menú
		validateDtoNotFound(dtmn, LiteData.LT_EL_DTO_DTMN, Dtmn.key(area.IN.ctmn, area.IN.iden));
		
		//Validamos que el detalle de menú no se encuentre desactivado
		test(true, dtmn.getActi(), LiteData.LT_EL_BOOL_NO, CoreNotify.DTMN_DESA_DTMN_ACTI_NO);
		
		//Desactivamos el detalle de menú
		dtmn.setActi(LiteData.LT_EL_BOOL_NO);
		
		//Guardamos el detalle de menú
		BsDtmnSaveArea bsDtmnSaveArea = new BsDtmnSaveArea();
		bsDtmnSaveArea.IN.dtmn = dtmn;
		bsDtmnSave.executeBS(bsDtmnSaveArea);
				
		area.OUT.dtmn = bsDtmnSaveArea.OUT.dtmn;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDtmnDesaArea area = (BsDtmnDesaArea)a;

		//Se valida que el identificador de categoría de menú esté informado
		validateInputField(area.IN.ctmn, Dtmn.CTMN);
		//Se valida que el identificador de detalle de menú esté informado
		validateInputField(area.IN.iden, Dtmn.IDEN);
	}

}
