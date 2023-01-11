package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.util.DateTimeUtil;
import es.ldrsoftware.fecon.cnt.entity.Hcon;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.LiteData;

@Component
public class BsHconModi extends BaseBS {


	@Autowired
	public BsHconGetk bsHconGet;
	
	@Autowired
	public BsHconSave bsHconSave;

	protected void execute(BaseBSArea a) throws Exception {
		BsHconModiArea area = (BsHconModiArea)a;
		
		BsHconGetkArea bsHconGetkArea = new BsHconGetkArea();
		bsHconGetkArea.IN.iden = area.IN.iden;
		bsHconGet.execute(bsHconGetkArea);
		
		Hcon hcon = bsHconGetkArea.OUT.hcon;
		
		validateDtoNotFound(hcon, LiteData.LT_EL_DTO_HCON, Hcon.key(area.IN.iden));
		
		if (!LiteData.LT_EL_HCONTIPO_CONTABLE.equals(hcon.getTipo())) {
			notify(AppNotify.HCON_MODI_CONT_NO);
		}
		
		if (LiteData.LT_EL_HCONMDTIPO_FECHA.equals(area.IN.tipo)) {
			if (hcon.getFeva() == area.IN.feva) {
				notify(AppNotify.HCON_MODI_CHGN_NO);
			}
			
			if (DateTimeUtil.getYear(hcon.getFeva()) != DateTimeUtil.getYear(area.IN.feva) ||
				DateTimeUtil.getMonth(hcon.getFeva()) != DateTimeUtil.getMonth(area.IN.feva)) {
				notify(AppNotify.HCON_MODI_FECH_MES);
			}
			hcon.setFeva(area.IN.feva);
			
			BsHconSaveArea bsHconSaveArea = new BsHconSaveArea();
			bsHconSaveArea.IN.hcon = hcon;
			bsHconSave.execute(bsHconSaveArea);
			
			area.OUT.hcon = bsHconSaveArea.OUT.hcon;
		}
		
		if (LiteData.LT_EL_HCONMDTIPO_DESCRIPCION.equals(area.IN.tipo)) {
			if (area.IN.desc.equals(hcon.getDesc())) {
				notify(AppNotify.HCON_MODI_CHGN_NO);
			}
			
			hcon.setDesc(area.IN.desc);
			
			BsHconSaveArea bsHconSaveArea = new BsHconSaveArea();
			bsHconSaveArea.IN.hcon = hcon;
			bsHconSave.execute(bsHconSaveArea);
			
			area.OUT.hcon = bsHconSaveArea.OUT.hcon;
		}
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsHconModiArea area = (BsHconModiArea)a;
		
		validateInputField(area.IN.tipo, Hcon.TIMO);
		
		validateInputField(area.IN.iden, Hcon.IDEN);
		
//		if (LiteData.LT_EL_HCONMDTIPO_FECHA.equals(area.IN.tipo)) {
//			validateInputField(area.IN.feva, Hcon.FEVA);
//		} else {
//			notify(AppNotify.HCON_MODI_TIPO_ERRO);
//		}
		
		switch(area.IN.tipo) {
		case LiteData.LT_EL_HCONMDTIPO_FECHA:
			 validateInputField(area.IN.feva, Hcon.FEVA);
			 break;
		case LiteData.LT_EL_HCONMDTIPO_DESCRIPCION:
			 validateInputField(area.IN.desc, Hcon.FEVA);
			 break;
		default:
			notify(AppNotify.HCON_MODI_TIPO_ERRO);
			break;
		}
	}
}