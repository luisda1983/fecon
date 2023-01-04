package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.util.StringUtil;
import es.ldrsoftware.fecon.cnt.entity.Coes;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.LiteData;

@Component
public class BsCoesForm extends BaseBS {

	@Autowired
	BsCoesGetk bsCoesGetk;

	@Autowired
	BsCoesSave bsCoesSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCoesFormArea area = (BsCoesFormArea)a;
		
		//Nuevo codigo especifico
		if (!data(area.IN.iden)) {
			
			//TODO: validar existe desc
			
			Coes coes = new Coes();
			
			coes.setInst(SESSION.get().inst);
			coes.setAnin(1960);
			coes.setAnfi(2999);
			coes.setTipo(area.IN.tipo);
			coes.setDesc(area.IN.desc);
			coes.setFavo(area.IN.favo);
			coes.setUsad(0);
			coes.setTrad(area.IN.trad);
			coes.setCate(area.IN.cate);
			coes.setConc(area.IN.conc);
			coes.setFeal(SESSION.get().feop);
			coes.setHoal(SESSION.get().hoop);
			coes.setUsal(SESSION.get().usua);
			coes.setFemo(SESSION.get().feop);
			coes.setHomo(SESSION.get().hoop);
			coes.setUsmo(SESSION.get().usua);
			
			BsCoesSaveArea bsCoesSaveArea = new BsCoesSaveArea();
			bsCoesSaveArea.IN.coes = coes;
			bsCoesSave.executeBS(bsCoesSaveArea);
			
			area.OUT.coes = bsCoesSaveArea.OUT.coes;
			
		//Edici�n de cuenta
		} else {
			BsCoesGetkArea bsCoesGetkArea = new BsCoesGetkArea();
			bsCoesGetkArea.IN.iden = area.IN.iden;
			bsCoesGetk.executeBS(bsCoesGetkArea);
			
			Coes coes = bsCoesGetkArea.OUT.coes;
			
			validateDtoNotFound(coes, LiteData.LT_EL_DTO_COES, Coes.key(area.IN.iden));

			if (area.IN.trad != coes.getTrad()) {
				notify(AppNotify.COES_FORM_TRAD_CHNG_NO, StringUtil.convert(area.IN.trad), StringUtil.convert(coes.getTrad()));
				//TODO: permitir cambiar si no hay apuntes
			}

			if (!area.IN.tipo.equals(coes.getTipo())) {
				notify(AppNotify.COES_FORM_TIPO_CHNG_NO, area.IN.tipo, coes.getTipo());
				//TODO: permitir cambiar si no hay apuntes
			}

			if (area.IN.cate != coes.getCate() || area.IN.conc != coes.getConc()) {
				notify(AppNotify.COES_FORM_TRAD_CHNG_NO);
				//TODO: Se debe versionar el coes validando adecuadamente el anin, actualizando el anfi del existente
			}
			
			coes.setTipo(area.IN.tipo);
			coes.setDesc(area.IN.desc);
			coes.setFavo(area.IN.favo);
			
			coes.setFemo(SESSION.get().feop);
			coes.setHomo(SESSION.get().hoop);
			coes.setUsmo(SESSION.get().usua);
			
			BsCoesSaveArea bsCoesSaveArea = new BsCoesSaveArea();
			bsCoesSaveArea.IN.coes = coes;
			bsCoesSave.executeBS(bsCoesSaveArea);
			
			area.OUT.coes = bsCoesSaveArea.OUT.coes;
		}
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCoesFormArea area = (BsCoesFormArea)a;

		validateInputField(area.IN.tipo, Coes.TIPO);
		validateInputField(area.IN.desc, Coes.DESC);
		validateInputField(area.IN.favo, Coes.FAVO);
		validateInputField(area.IN.cate, Coes.CATE);
		validateInputField(area.IN.conc, Coes.CONC);
		validateInputField(area.IN.trad, Coes.TRAD);
	}

}
