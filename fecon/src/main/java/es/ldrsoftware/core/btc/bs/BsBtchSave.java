package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Btch;
import es.ldrsoftware.core.btc.entity.BtchDAO;
import es.ldrsoftware.core.fwk.data.LiteData;

@Component
public class BsBtchSave extends BaseBS {

	@Autowired
	BtchDAO btchDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsBtchSaveArea area = (BsBtchSaveArea)a;
		
		//Grabamos el proceso Batch
		Btch btch = btchDao.save(area.IN.btch);
		
		area.OUT.btch = btch;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsBtchSaveArea area = (BsBtchSaveArea)a;
		
		//Validamos que está informado el proceso Batch
		Btch btch = (Btch)validateDto(area.IN.btch, LiteData.LT_EL_DTO_BTCH);		
	}

}
