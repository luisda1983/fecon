package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Trad;

@Component
public class BsTradForm extends BaseBS {

	//@Autowired
	//BsTradGetk bsTradGetk;

	@Autowired
	BsTradSave bsTradSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsTradFormArea area = (BsTradFormArea)a;
		
		//Nueva traducción
		if (!data(area.IN.iden)) {
			
			//TODO: validar existe nombre
			
			Trad trad = new Trad();
			
			trad.setInst(SESSION.get().inst);
			trad.setNomb(area.IN.nomb);
			trad.setTip1(area.IN.tip1);
			trad.setDom1(area.IN.dom1);
			System.out.println(trad.getDom1());
			trad.setIde1(area.IN.ide1);
			trad.setObl1(area.IN.obl1);
			trad.setTip2(area.IN.tip2);
			trad.setDom2(area.IN.dom2);
			trad.setIde2(area.IN.ide2);
			trad.setObl2(area.IN.obl2);
			trad.setTip3(area.IN.tip3);
			trad.setDom3(area.IN.dom3);
			trad.setIde3(area.IN.ide3);
			trad.setObl3(area.IN.obl3);
			trad.setDesc(area.IN.desc);
			
			BsTradSaveArea bsTradSaveArea = new BsTradSaveArea();
			bsTradSaveArea.IN.trad = trad;
			bsTradSave.executeBS(bsTradSaveArea);
			
			area.OUT.trad = bsTradSaveArea.OUT.trad;
			
		//Ediciï¿½n de cuenta
		} else {
//			BsCoesGetkArea bsCoesGetkArea = new BsCoesGetkArea();
//			bsCoesGetkArea.IN.iden = area.IN.iden;
//			bsCoesGetk.executeBS(bsCoesGetkArea);
//			
//			Coes coes = bsCoesGetkArea.OUT.coes;
//			
//			validateDtoNotFound(coes, LiteData.LT_EL_DTO_COES, Coes.key(area.IN.iden));
//			
//			coes.setTipo(area.IN.tipo);
//			coes.setDesc(area.IN.desc);
//			coes.setFavo(area.IN.favo);
//			
//			coes.setFemo(SESSION.get().feop);
//			coes.setHomo(SESSION.get().hoop);
//			coes.setUsmo(SESSION.get().usua);
//			
//			BsCoesSaveArea bsCoesSaveArea = new BsCoesSaveArea();
//			bsCoesSaveArea.IN.coes = coes;
//			bsCoesSave.executeBS(bsCoesSaveArea);
//			
//			area.OUT.coes = bsCoesSaveArea.OUT.coes;
		}
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsTradFormArea area = (BsTradFormArea)a;

		validateInputField(area.IN.nomb, Trad.NOMB);
		validateInputField(area.IN.desc, Trad.DESC);
		
		if (data(area.IN.tip1)) {
			validateInputField(area.IN.ide1, Trad.IDE1);
			validateInputField(area.IN.obl1, Trad.OBL1);
		}
		
		if (data(area.IN.tip2)) {
			validateInputField(area.IN.ide2, Trad.IDE2);
			validateInputField(area.IN.obl2, Trad.OBL2);
		}
		
		if (data(area.IN.tip3)) {
			validateInputField(area.IN.ide3, Trad.IDE3);
			validateInputField(area.IN.obl3, Trad.OBL3);
		}
	}

}
