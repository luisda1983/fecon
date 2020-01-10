package es.ldrsoftware.core.btc.bs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Plan;
import es.ldrsoftware.core.btc.entity.PlanDAO;

@Component
public class BsPlanList extends BaseBS {

	@Autowired
	PlanDAO planDao;
	
	public final static String PLAN_LIST_FECH_PROC = "LT-01";
	public final static String PLAN_LIST_FECH      = "LT-02";
	
	protected void execute(BaseBSArea a) throws Exception {
		BsPlanListArea area = (BsPlanListArea)a;
		
		List<Plan> planList = new ArrayList<Plan>();
		
		switch(area.IN.tipo) {
		case PLAN_LIST_FECH:
			 planList = planDao.getListByFech(area.IN.fech);
			 break;
		case PLAN_LIST_FECH_PROC:
			 planList = planDao.getListByFechProc(area.IN.fech);
			 break;
		}
		
		area.OUT.planList = planList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsPlanListArea area = (BsPlanListArea)a;

		validateInputField(area.IN.tipo, Plan.TIPO_LIST);
		
		switch(area.IN.tipo) {
		case PLAN_LIST_FECH_PROC:
		case PLAN_LIST_FECH:
			 validateInputField(area.IN.fech, Plan.FECH);
			 break;
		default:
			 notify(CoreNotify.PLAN_LIST_TIPO_ERRO);
			 break;
		}
	}

}
