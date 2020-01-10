package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.LiteData;

@Component
public class BsCuenForm extends BaseBS {

	@Autowired
	BsCuenGetk bsCuenGetk;

	@Autowired
	BsCuenSave bsCuenSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCuenFormArea area = (BsCuenFormArea)a;
		
		//Nueva cuenta
		if (!data(area.IN.iden)) {
			Cuen cuen = new Cuen();
			
			cuen.setInst(SESSION.get().inst);
			cuen.setTipo(area.IN.tipo);
			cuen.setDesc(area.IN.desc);
			cuen.setSald(area.IN.sald);
			cuen.setFeal(SESSION.get().feop);
			cuen.setHoal(SESSION.get().hoop);
			cuen.setUsal(SESSION.get().usua);
			cuen.setFemo(SESSION.get().feop);
			cuen.setHomo(SESSION.get().hoop);
			cuen.setUsmo(SESSION.get().usua);
			
			BsCuenSaveArea bsCuenSaveArea = new BsCuenSaveArea();
			bsCuenSaveArea.IN.cuen = cuen;
			bsCuenSave.executeBS(bsCuenSaveArea);
			
			area.OUT.cuen = bsCuenSaveArea.OUT.cuen;
			
		//Edici�n de cuenta
		} else {
			BsCuenGetkArea bsCuenGetkArea = new BsCuenGetkArea();
			bsCuenGetkArea.IN.iden = area.IN.iden;
			bsCuenGetk.executeBS(bsCuenGetkArea);
			
			Cuen cuen = bsCuenGetkArea.OUT.cuen;
			
			validateDtoNotFound(cuen, LiteData.LT_EL_DTO_CUEN, Cuen.key(area.IN.iden));
			
			cuen.setTipo(area.IN.tipo);
			cuen.setDesc(area.IN.desc);
			
			if (cuen.getSald() != area.IN.sald) {
				System.out.println(cuen.getSald() + " " + area.IN.sald);
				notify(AppNotify.CUEN_FORM_SALD_MODI_NPER);
			}
			
			cuen.setFemo(SESSION.get().feop);
			cuen.setHomo(SESSION.get().hoop);
			cuen.setUsmo(SESSION.get().usua);
			
			BsCuenSaveArea bsCuenSaveArea = new BsCuenSaveArea();
			bsCuenSaveArea.IN.cuen = cuen;
			bsCuenSave.executeBS(bsCuenSaveArea);
			
			area.OUT.cuen = bsCuenSaveArea.OUT.cuen;
		}
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCuenFormArea area = (BsCuenFormArea)a;
		
		validateInputField(area.IN.tipo, Cuen.TIPO);
		validateInputField(area.IN.desc, Cuen.DESC);
	}

}
