package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Plan;
import es.ldrsoftware.core.btc.entity.PlanDAO;

@Component
public class BsPlanGetk extends BaseBS {

	@Autowired
	PlanDAO planDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsPlanGetkArea area = (BsPlanGetkArea)a;
		
		//Obtenemos la planificación
		Plan plan = planDao.getByFechHora(area.IN.fech, area.IN.hora);
		
		area.OUT.plan = plan;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsPlanGetkArea area = (BsPlanGetkArea)a;
	}

}
