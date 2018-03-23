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
	public BsHconGet bsHconGet;
	
	@Autowired
	public BsHconSave bsHconSave;

	protected void execute(BaseBSArea a) throws Exception {
		BsHconModiArea area = (BsHconModiArea)a;
		
		BsHconGetArea bsHconGetArea = new BsHconGetArea();
		bsHconGetArea.IN.iden = area.IN.iden;
		bsHconGet.execute(bsHconGetArea);
		
		Hcon hcon = bsHconGetArea.OUT.hcon;
		
		validateDtoRequired(hcon, AppNotify.HCON_MODI_HCON_NF);
		
		if (LiteData.LT_EL_HCONMDTIPO_FECHA.equals(area.IN.tipo)) {
			if (hcon.getFeva() == area.IN.feva) {
				notify(AppNotify.HCON_MODI_CHGN_NO);
			}
			if (DateTimeUtil.getYear(hcon.getFeva()) != DateTimeUtil.getYear(area.IN.feva) ||
				DateTimeUtil.getMonth(hcon.getFeva()) != DateTimeUtil.getMonth(area.IN.feva)) {
				notify(AppNotify.HCON_MODI_ERRO);
			}
			hcon.setFeva(area.IN.feva);
			
			BsHconSaveArea bsHconSaveArea = new BsHconSaveArea();
			bsHconSaveArea.IN.hcon = hcon;
			bsHconSave.execute(bsHconSaveArea);
			area.OUT.hcon = bsHconSaveArea.OUT.hcon;
		}

	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsHconModiArea area = (BsHconModiArea)a;
		
		validateStringRequired(area.IN.tipo, AppNotify.HCON_MODI_TIPO_RQRD);
		
		validateIntRequired(area.IN.iden, AppNotify.HCON_MODI_IDEN_RQRD);
		
		if (LiteData.LT_EL_HCONMDTIPO_FECHA.equals(area.IN.tipo)) {
			validateIntRequired(area.IN.feva, AppNotify.HCON_MODI_FEVA_RQRD);
		} else {
			notify(AppNotify.HCON_MODI_TIPO_ERRO);
		}
		
	}
}