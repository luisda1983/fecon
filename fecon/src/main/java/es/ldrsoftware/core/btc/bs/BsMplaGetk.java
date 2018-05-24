package es.ldrsoftware.core.btc.bs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Mpla;
import es.ldrsoftware.core.btc.entity.MplaDAO;

@Component
public class BsMplaGetk extends BaseBS {

	@Autowired
	MplaDAO mplaDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsMplaGetkArea area = (BsMplaGetkArea)a;
		
		//Obtenemos el maestro de planificación
		Mpla mpla = mplaDao.getByIden(area.IN.hora);
		
		area.OUT.mpla = mpla;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsMplaGetkArea area = (BsMplaGetkArea)a;
	}

}
