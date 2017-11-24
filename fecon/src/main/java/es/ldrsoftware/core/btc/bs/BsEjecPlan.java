package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Ejec;

@Component
public class BsEjecPlan extends BaseBS {

	@Autowired
	BsEjecSave bsEjecSave;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsEjecPlanArea area = (BsEjecPlanArea)a;
		
		Ejec ejec = new Ejec();
		ejec.setBtch(area.IN.btch);
		ejec.setFech(area.IN.fech);
		ejec.setSecu(area.IN.secu);
		ejec.setOrde(area.IN.orde);
		ejec.setEsta("P");
		ejec.setNotf("");
		ejec.setFein(0);
		ejec.setHoin(0);
		ejec.setFefi(0);
		ejec.setHofi(0);
		ejec.setTiej(0);
		
		BsEjecSaveArea saveArea = new BsEjecSaveArea();
		saveArea.IN.ejec = ejec;
		bsEjecSave.executeBS(saveArea);

		area.OUT.ejec = saveArea.OUT.ejec;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsEjecPlanArea area = (BsEjecPlanArea)a;
			
		if (area.IN.btch == null || "".equals(area.IN.btch)) { notify(CoreNotify.EJEC_PLAN_BTCH_RQRD); }
		if (area.IN.fech == 0) { notify(CoreNotify.EJEC_PLAN_FECH_RQRD); }
		if (area.IN.orde == 0) { notify(CoreNotify.EJEC_PLAN_ORDE_RQRD); }
		
	}

}
