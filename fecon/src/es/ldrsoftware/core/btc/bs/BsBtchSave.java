package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Btch;
import es.ldrsoftware.core.btc.entity.BtchDAO;

@Component
public class BsBtchSave extends BaseBS {

	@Autowired
	BtchDAO btchDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsBtchSaveArea area = (BsBtchSaveArea)a;
		
		Btch btch = btchDao.save(area.IN.btch);
		
		area.OUT.btch = btch;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsBtchSaveArea area = (BsBtchSaveArea)a;
		
		if (area.IN.btch == null) { notify(CoreNotify.BTCH_SAVE_RQRD); }
		
		Btch btch = area.IN.btch;
		
		if (btch.getIden() == null || "".equals(btch.getIden())) { notify(CoreNotify.BTCH_SAVE_IDEN_RQRD); }
		if (btch.getDesc() == null || "".equals(btch.getDesc())) { notify(CoreNotify.BTCH_SAVE_DESC_RQRD); }
		if (btch.getFeal() == 0) { notify(CoreNotify.BTCH_SAVE_FEAL_RQRD); }
		if (btch.getOrde() == 0) { notify(CoreNotify.BTCH_SAVE_ORDE_RQRD); }
		if (btch.getTipo() == null || "".equals(btch.getTipo())) { notify(CoreNotify.BTCH_SAVE_TIPO_RQRD); }
		
		if (!"DI".equals(btch.getTipo()) &&
			!"SM".equals(btch.getTipo()) &&
			!"QU".equals(btch.getTipo()) &&
			!"MS".equals(btch.getTipo()) &&
			!"TR".equals(btch.getTipo()) &&
			!"AN".equals(btch.getTipo()) &&
			!"PT".equals(btch.getTipo())) {
			notify(CoreNotify.BTCH_SAVE_TIPO_ERRO);
		}
		
		if (btch.getPlan() == null || "".equals(btch.getPlan())) { notify(CoreNotify.BTCH_SAVE_PLAN_RQRD); }
		
		if (!"S".equals(btch.getPlan()) &&
			!"N".equals(btch.getPlan())) {
			notify(CoreNotify.BTCH_SAVE_PLAN_ERRO);
		}
		
		if ("S".equals(btch.getPlan())) {
			if (btch.getFein() == 0) { notify(CoreNotify.BTCH_SAVE_PLAN_FEIN_RQRD); }
		}
		
		if (!"PT".equals(btch.getTipo())) {
			if (btch.getFeul() != 0) {
				if (btch.getFepr() == 0) { notify(CoreNotify.BTCH_SAVE_PERI_FEPR_RQRD); }
			}
		}
	}

}
