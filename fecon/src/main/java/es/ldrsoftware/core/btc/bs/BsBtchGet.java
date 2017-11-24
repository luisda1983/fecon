package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Btch;
import es.ldrsoftware.core.btc.entity.BtchDAO;

@Component
public class BsBtchGet extends BaseBS {

	@Autowired
	BtchDAO btchDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsBtchGetArea area = (BsBtchGetArea)a;
		
		Btch btch = btchDao.getByIden(area.IN.iden);
		
		area.OUT.btch = btch;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsBtchGetArea area = (BsBtchGetArea)a;
		
		if (area.IN.iden == null || "".equals(area.IN.iden)) { notify(CoreNotify.BTCH_GETB_IDEN_RQRD); }
	}

}
