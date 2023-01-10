package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.btc.entity.Plan;
import es.ldrsoftware.core.btc.entity.PlanDAO;
import es.ldrsoftware.core.fwk.data.LiteData;

@Component
public class BsPlanSave extends BaseBS {

	@Autowired
	PlanDAO planDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsPlanSaveArea area = (BsPlanSaveArea)a;
		
		//Guardamos la planificación
		Plan plan = planDao.save(area.IN.plan);
		
		area.OUT.plan = plan;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsPlanSaveArea area = (BsPlanSaveArea)a;
		
		//Validamos que la planificación esté informada
		Plan plan = (Plan)validateDto(area.IN.plan, LiteData.LT_EL_DTO_PLAN);
		
	}

}
