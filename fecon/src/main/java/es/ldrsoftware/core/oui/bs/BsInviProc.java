package es.ldrsoftware.core.oui.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.entity.Invi;

@Component
public class BsInviProc extends BaseBS {

	@Autowired
	private BsInstRegi bsInstRegi;
	
	@Autowired
	private BsInviGetk  bsInviGet;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsInviProcArea area = (BsInviProcArea)a;
	
		//Consultamos la invitaci�n
		BsInviGetkArea bsInviGetArea = new BsInviGetkArea();
		bsInviGetArea.IN.iden = area.IN.iden;
		bsInviGet.executeBS(bsInviGetArea);
		
		Invi invi = bsInviGetArea.OUT.invi;
		
		if (invi == null) {
			notify(CoreNotify.INVI_PROC_INVI_NF);
		}
		
		if (LiteData.LT_EL_INVITIPO_INSTALACION.equals(invi.getTipo())) {
			BsInstRegiArea bsInstRegiArea = new BsInstRegiArea();
			bsInstRegiArea.IN.invi = area.IN.iden;
			bsInstRegiArea.IN.usua = area.IN.usua;
			bsInstRegiArea.IN.pass = area.IN.pass;
			bsInstRegiArea.IN.cpas = area.IN.cpas;
			bsInstRegiArea.IN.mail = area.IN.mail;
			bsInstRegi.executeBS(bsInstRegiArea);
			
			//area.OUT.invi = bsInstRegiArea.OUT.invi;
		} else {
			notify("NOIMPLEMEN");
		}
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsInviProcArea area = (BsInviProcArea)a;
		
		validateStringRequired(area.IN.iden, CoreNotify.INVI_PROC_IDEN_RQRD);

	}
}