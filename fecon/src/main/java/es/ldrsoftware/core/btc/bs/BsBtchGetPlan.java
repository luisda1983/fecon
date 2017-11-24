package es.ldrsoftware.core.btc.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Btch;
import es.ldrsoftware.core.btc.entity.BtchDAO;

@Component
public class BsBtchGetPlan extends BaseBS {

	@Autowired
	BtchDAO btchDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsBtchGetPlanArea area = (BsBtchGetPlanArea)a;
		
		List<Btch> btchList = btchDao.getListByPlan("N");
		
		area.OUT.btchList = btchList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsBtchGetListArea area = (BsBtchGetListArea)a;
	}

}
