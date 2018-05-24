package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
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
		
		Plan plan = area.IN.plan;
		
		//Validamos que la planificación esté informada
		validateDtoRequired(plan, CoreNotify.PLAN_SAVE_PLAN_RQRD);
		
		//Validamos que la fecha de la planificación esté informada y dentro de rango
		validateIntRequired(plan.getFech(), CoreNotify.PLAN_SAVE_FECH_RQRD);
		validateIntRange(plan.getFech(), 19000101, 29991231, CoreNotify.PLAN_SAVE_FECH_RNGE);
		
		//Validamos que la hora de la planificación se encuentre dentro de rango
		validateIntRange(plan.getHora(), 0000, 2359, CoreNotify.PLAN_SAVE_HORA_RNGE);
		
		//Validamos que la fecha batch de la planificación esté informada y dentro de rango
		validateIntRequired(plan.getFbtc(), CoreNotify.PLAN_SAVE_FBTC_RQRD);
		validateIntRange(plan.getFbtc(), 19000101, 29991231, CoreNotify.PLAN_SAVE_FBTC_RNGE);
		
		//Validamos que el estado de la planificación esté informado y tenga un valor correcto
		validateStringRequired(plan.getEsta(), CoreNotify.PLAN_SAVE_ESTA_RQRD);
		validateStringDomain(CoreNotify.PLAN_SAVE_ESTA_ERRO, plan.getEsta(), LiteData.LT_ST_PLANESTA);
		
		//Si el estado es pendiente, fecha y hora de fin deben estar vacío, en caso contrario los validamos
		if (LiteData.LT_EL_PLANESTA_PENDIENTE.equals(plan.getEsta())) {
			validateIntEmpty(plan.getFein(), CoreNotify.PLAN_SAVE_FEIN_NPER);
			validateIntEmpty(plan.getHoin(), CoreNotify.PLAN_SAVE_HOIN_NPER);
		} else {
			//Validamos que la fecha de inicio de planificación esté informada y dentro de rango
			validateIntRequired(plan.getFein(), CoreNotify.PLAN_SAVE_FEIN_RQRD);
			validateIntRange(plan.getFein(), 19000101, 29991231, CoreNotify.PLAN_SAVE_FEIN_RNGE);
			
			//Validamos que la hora de inicio de planificación esté dentro de rango
			validateIntRange(plan.getHoin(), 000000, 235959, CoreNotify.PLAN_SAVE_HOIN_RNGE);
		}
		
		//Si el estado es finalizado o error, validamos fecha y hora de finalización, en caso contrario deben estar vacíos
		if (LiteData.LT_EL_PLANESTA_FINALIZADO.equals(plan.getEsta()) ||
			LiteData.LT_EL_PLANESTA_ERROR.equals(plan.getEsta())) {
			//Validamos que la fecha de fin de planificación esté informada y dentro de rango
			validateIntRequired(plan.getFefi(), CoreNotify.PLAN_SAVE_FEFI_RQRD);
			validateIntRange(plan.getFefi(), 19000101, 29991231, CoreNotify.PLAN_SAVE_FEFI_RNGE);
			
			//Validamos que la hora de fin de planificación esté dentro de rango
			validateIntRange(plan.getHofi(), 000000, 235959, CoreNotify.PLAN_SAVE_HOFI_RNGE);
		} else {
			validateIntEmpty(plan.getFefi(), CoreNotify.PLAN_SAVE_FEFI_NPER);
			validateIntEmpty(plan.getHofi(), CoreNotify.PLAN_SAVE_HOFI_NPER);
		}
		
		//Si el estado es pendiente, el número de procesos ejecutados en la planificación debe ser 0, en caso contrario, validamos rango
		if (LiteData.LT_EL_PLANESTA_PENDIENTE.equals(plan.getEsta())) {
			validateIntEmpty(plan.getProc(), CoreNotify.PLAN_SAVE_PROC_NPER);
		} else {
			validateIntRange(plan.getProc(), 000000, 999999, CoreNotify.PLAN_SAVE_PROC_RNGE);
		}
		
		//La ventana debe estar informada y dentro de rango
		validateIntRequired(plan.getVent(), CoreNotify.PLAN_SAVE_VENT_RQRD);
		validateIntRange(plan.getVent(), 0015, 2400, CoreNotify.PLAN_SAVE_VENT_RNGE);
		
		//El porcentaje de uso de ventana sólo estará informada cuando el proceso esté finalizado o en error
		if ((LiteData.LT_EL_PLANESTA_FINALIZADO.equals(plan.getEsta()) ||
			LiteData.LT_EL_PLANESTA_ERROR.equals(plan.getEsta())) &&
			plan.getProc() > 0) {
			validateDecRequired(plan.getPorc(), CoreNotify.PLAN_SAVE_PORC_RQRD);
			validateDecRange(plan.getProc(), 0.000000, 1000.000000, CoreNotify.PLAN_SAVE_PORC_RNGE);
		} else {
			validateDecEmpty(plan.getPorc(), CoreNotify.PLAN_SAVE_PORC_NPER);
		}
	}

}
