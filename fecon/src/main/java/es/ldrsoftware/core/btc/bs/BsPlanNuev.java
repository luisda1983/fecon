package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Plan;
import es.ldrsoftware.core.fwk.data.LiteData;

@Component
public class BsPlanNuev extends BaseBS {

	@Autowired
	BsMplaVent bsMplaVent;

	@Autowired
	BsPlanSave bsPlanSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsPlanNuevArea area = (BsPlanNuevArea)a;
		
		//Obtenemos la ventana de ejecución
		BsMplaVentArea bsMplaVentArea = new BsMplaVentArea();
		bsMplaVentArea.IN.hora = area.IN.hora;
		bsMplaVent.executeBS(bsMplaVentArea);
		
		int vent = bsMplaVentArea.OUT.vent;
		
		//Creamos la planificación y la grabamos
		Plan plan = new Plan();
		plan.setFech(area.IN.fech);
		plan.setHora(area.IN.hora);
		plan.setFbtc(area.IN.fbtc);
		plan.setEsta(LiteData.LT_EL_PLANESTA_PENDIENTE);
		plan.setFein(0);
		plan.setHoin(0);
		plan.setFefi(0);
		plan.setHofi(0);
		plan.setProc(0);
		plan.setVent(vent);
		plan.setPorc(0);
		
		BsPlanSaveArea bsPlanSaveArea = new BsPlanSaveArea();
		bsPlanSaveArea.IN.plan = plan;
		bsPlanSave.executeBS(bsPlanSaveArea);
		
		area.OUT.plan = bsPlanSaveArea.OUT.plan;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsPlanNuevArea area = (BsPlanNuevArea)a;
		
		//Validamos que la fecha de planifición esté informada
		validateIntRequired(area.IN.fech, CoreNotify.PLAN_NUEV_FECH_RQRD);
	}
}
