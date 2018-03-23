package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.data.AppNotify;

@Component
public class BsCuenCuad extends BaseBS {
	
	@Autowired
	BsCuenGet bsCuenGet;
	
	@Autowired
	BsHconApun bsHconApun;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCuenCuadArea area = (BsCuenCuadArea)a;
		
		BsCuenGetArea bsCuenGetArea = new BsCuenGetArea();
		bsCuenGetArea.IN.iden = area.IN.cuen;
		bsCuenGet.executeBS(bsCuenGetArea);
		
		Cuen cuen = bsCuenGetArea.OUT.cuen;
		
		validateDtoRequired(cuen, AppNotify.CUEN_CUAD_CUEN_NF);

		//TODO: validaci�n de concurrencia. No funciona por redondeo
//		if (cuen.getSald() != area.IN.sald) {
//			System.out.println(cuen.getSald() + " vs " + area.IN.sald);
//			notify(AppNotify.CUEN_CUAD_CONCURREN);
//		}
	
		BsHconApunArea bsHconApunArea = new BsHconApunArea();
		bsHconApunArea.IN.cate = area.IN.cate;
		bsHconApunArea.IN.conc = area.IN.conc;
		bsHconApunArea.IN.cuen = area.IN.cuen;
		bsHconApunArea.IN.impo = area.IN.impo;
		bsHconApunArea.IN.feva = SESSION.get().feop;
		bsHconApunArea.IN.desc = "CUADRE DE CUENTA " + cuen.getDesc();
		bsHconApun.execute(bsHconApunArea);
		
		area.OUT.cuen = bsHconApunArea.OUT.cuen;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCuenCuadArea area = (BsCuenCuadArea)a;
		
		validateIntRequired(area.IN.cuen, AppNotify.CUEN_CUAD_CUEN_RQRD);
		validateIntRequired(area.IN.cate, AppNotify.CUEN_CUAD_CATE_RQRD);
		validateIntRequired(area.IN.conc, AppNotify.CUEN_CUAD_CONC_RQRD);
		validateDecRequired(area.IN.impo, AppNotify.CUEN_CUAD_IMPO_RQRD);
	}

}
