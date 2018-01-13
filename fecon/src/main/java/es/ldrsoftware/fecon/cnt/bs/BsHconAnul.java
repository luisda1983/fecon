package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Hcon;
import es.ldrsoftware.fecon.data.AppNotify;

@Component
public class BsHconAnul extends BaseBS {

	@Autowired
	public BsHconGet bsHconGet;

	@Autowired
	public BsHconApunAnul bsHconApunAnul;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsHconAnulArea area = (BsHconAnulArea)a;

		BsHconGetArea bsHconGetArea = new BsHconGetArea();
		bsHconGetArea.IN.iden = area.IN.iden;
		bsHconGet.execute(bsHconGetArea);
		
		Hcon hcon = bsHconGetArea.OUT.hcon;
		
		if ("C".equals(hcon.getTipo())) {
			BsHconApunAnulArea bsHconApunAnulArea = new BsHconApunAnulArea();
			bsHconApunAnulArea.IN.hcon = hcon;
			bsHconApunAnul.execute(bsHconApunAnulArea);
			hcon = bsHconApunAnulArea.OUT.hcon;
		} else {
			notify(AppNotify.HCON_ANUL_TIPO_NO_ANUL);
		}
		
		area.OUT.hcon = hcon;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsHconAnulArea area = (BsHconAnulArea)a;
		
		validateIntRequired(area.IN.iden, AppNotify.HCON_ANUL_IDEN_RQRD);
	}
}