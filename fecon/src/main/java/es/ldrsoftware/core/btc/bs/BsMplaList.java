package es.ldrsoftware.core.btc.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Mpla;
import es.ldrsoftware.core.btc.entity.MplaDAO;

@Component
public class BsMplaList extends BaseBS {

	@Autowired
	MplaDAO mplaDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsMplaListArea area = (BsMplaListArea)a;
		
		//Obtenemos la lista completa del maestro de planificación
		List<Mpla> mplaList = mplaDao.getList();
		
		area.OUT.mplaList = mplaList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsMplaListArea area = (BsMplaListArea)a;
	}

}
