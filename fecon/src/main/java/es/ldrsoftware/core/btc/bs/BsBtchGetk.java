package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Btch;
import es.ldrsoftware.core.btc.entity.BtchDAO;

@Component
public class BsBtchGetk extends BaseBS {

	@Autowired
	BtchDAO btchDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsBtchGetkArea area = (BsBtchGetkArea)a;
		
		Btch btch = btchDao.getByIden(area.IN.iden);
		
		area.OUT.btch = btch;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsBtchGetkArea area = (BsBtchGetkArea)a;

		validateStringRequired(area.IN.iden, CoreNotify.BTCH_GETK_IDEN_RQRD); 
	}

}
