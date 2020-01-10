package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Btch;
import es.ldrsoftware.core.btc.entity.Ejec;
import es.ldrsoftware.core.btc.entity.Mpla;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.data.ParaData;

@Component
public class BsEjecPlan extends BaseBS {

	@Autowired
	BsEjecSave bsEjecSave;
	
	@Autowired
	BsBtchGetk bsBtchGetk;

	@Autowired
	BsBtchSave bsBtchSave;
	
	@Autowired
	BsMplaGetk bsMplaGetk;
	
	@Autowired
	BsMplaVent bsMplaVent;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsEjecPlanArea area = (BsEjecPlanArea)a;

		//Recuperamos el Batch
		BsBtchGetkArea bsBtchGetkArea = new BsBtchGetkArea();
		bsBtchGetkArea.IN.iden = area.IN.btch;
		bsBtchGetk.executeBS(bsBtchGetkArea);
		
		Btch btch = bsBtchGetkArea.OUT.btch;
		
		//Validamos que existe y se encuentre activo
		if (btch == null) {
			notify(CoreNotify.EJEC_PLAN_BTCH_NF);
		}
		if (!LiteData.LT_EL_BTCHESTA_ACTIVO.equals(btch.getEsta())) {
			notify(CoreNotify.EJEC_PLAN_BTCH_ACTI_NO);
		}

		//Recuperamos el maestro de la planificación
		BsMplaGetkArea bsMplaGetkArea = new BsMplaGetkArea();
		bsMplaGetkArea.IN.hora = area.IN.hora;
		bsMplaGetk.executeBS(bsMplaGetkArea);
		
		Mpla mpla = bsMplaGetkArea.OUT.mpla;
		
		//Validamos que exista y se encuentra activada la franja
		if (mpla == null) {
			notify(CoreNotify.EJEC_PLAN_MPLA_NF);
		}
		if (!LiteData.LT_EL_MPLAESTA_ACTIVADO.equals(mpla.getEsta())) {
			if (area.IN.ajus) {
				if (ParaData.PM_EL_BTCHAVANCE_DIARIO.equals(btch.getTipo()) ||
					ParaData.PM_EL_BTCHAVANCE_MENSUAL.equals(btch.getTipo())) {
					BsMplaVentArea bsMplaVentArea = new BsMplaVentArea();
					bsMplaVentArea.IN.hora = area.IN.hora;
					bsMplaVent.executeBS(bsMplaVentArea);
				
					area.IN.hora = bsMplaVentArea.OUT.mpla.getHora();
				} else {
					notify(CoreNotify.EJEC_PLAN_MPLA_ACTI_NO);
				}
			} else {
				notify(CoreNotify.EJEC_PLAN_MPLA_ACTI_NO);
			}
		}
		
		//Creamos el registro de ejecución
		Ejec ejec = new Ejec();
		ejec.setBtch(area.IN.btch);
		ejec.setFech(area.IN.fech);
		ejec.setHora(area.IN.hora);
		ejec.setSecu(area.IN.secu);
		ejec.setOrde(btch.getOrde());
		ejec.setEsta(LiteData.LT_EL_EJECESTA_PENDIENTE);
		ejec.setNotf("");
		ejec.setFein(0);
		ejec.setHoin(0);
		ejec.setFefi(0);
		ejec.setHofi(0);
		ejec.setTiej(0);
		ejec.setFepl(0);
		ejec.setHopl(0);
		
		//Grabamos la ejecución
		BsEjecSaveArea bsEjecSaveArea = new BsEjecSaveArea();
		bsEjecSaveArea.IN.ejec = ejec;
		bsEjecSave.executeBS(bsEjecSaveArea);

		//Actualizamos la fecha de última planificación del Batch
		btch.setFupl(area.IN.fech);
		btch.setHupl(area.IN.hora);
		
		BsBtchSaveArea bsBtchSaveArea = new BsBtchSaveArea();
		bsBtchSaveArea.IN.btch = btch;
		bsBtchSave.executeBS(bsBtchSaveArea);
		
		area.OUT.ejec = bsEjecSaveArea.OUT.ejec;
		area.OUT.btch = bsBtchSaveArea.OUT.btch;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsEjecPlanArea area = (BsEjecPlanArea)a;

		//Validamos que recibimos el proceso Batch
		validateInputField(area.IN.btch, Ejec.BTCH);
		
		//Validamos que recibimos la fecha
		validateInputField(area.IN.fech, Ejec.FECH);
		
		//Validamos que recibimos la secuencia
		validateInputField(area.IN.secu, Ejec.SECU);
		
	}

}
