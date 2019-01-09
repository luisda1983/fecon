package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Hcon;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.prp.bs.BsPresGet;
import es.ldrsoftware.fecon.prp.bs.BsPresGetArea;
import es.ldrsoftware.fecon.prp.bs.BsPresSave;
import es.ldrsoftware.fecon.prp.bs.BsPresSaveArea;
import es.ldrsoftware.fecon.prp.entity.Pres;

@Component
public class BsHconPresGest extends BaseBS {

	@Autowired
	public BsHconGet bsHconGet;
	
	@Autowired
	public BsHconSave bsHconSave;

	@Autowired
	public BsPresGet bsPresGet;

	@Autowired
	public BsPresSave bsPresSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsHconPresGestArea area = (BsHconPresGestArea)a;
		
		//Obtenemos el apunte contable.
		BsHconGetArea bsHconGetArea = new BsHconGetArea();
		bsHconGetArea.IN.iden = area.IN.iden;
		bsHconGet.execute(bsHconGetArea);
		
		Hcon hcon = bsHconGetArea.OUT.hcon;
		
		//Error: No existe el apunte
		if (hcon == null) {
			notify(AppNotify.HCON_PRES_GEST_HCON_NF);
		}

		//Error: S�lo se pueden gestionar apuntes contables
		if (!"C".equals(hcon.getTipo())) {
			notify(AppNotify.HCON_PRES_GEST_TIPO_ERRO);
		}
		
		//Error: El apunte ya est� incluido en el presupuesto
		if ("S".equals(hcon.getPres()) && "I".equals(area.IN.acci)) {
			notify(AppNotify.HCON_PRES_GEST_APUN_INCL);
		}
		
		//Error: El apunte ya est� excluido del presupuesto
		if ("N".equals(hcon.getPres()) && "E".equals(area.IN.acci)) {
			notify(AppNotify.HCON_PRES_GEST_APUN_EXCL);
		}
		
		//Obtenemos la partida presupuestaria
		BsPresGetArea bsPresGetArea = new BsPresGetArea();
		bsPresGetArea.IN.anua = hcon.getPran();
		bsPresGetArea.IN.mesp = hcon.getPrms();
		bsPresGetArea.IN.cate = hcon.getPrct();
		bsPresGetArea.IN.conc = hcon.getPrcc();
		bsPresGet.executeBS(bsPresGetArea);
		
		Pres pres = bsPresGetArea.OUT.pres;
		
		//Error: apunte mal relacionado con partida presupuestaria
		if (pres == null) {
			notify(AppNotify.HCON_PRES_GEST_PRES_NF);
		}
		//Error: No se puede incluir sobre una partida no presupuestada
		if ("I".equals(area.IN.acci) && pres.getImpo() == 0) {
			notify(AppNotify.HCON_PRES_GEST_PART_NPRE);
		}
		
		//Modificamos el indicador de presupuestado en el apunte
		if ("I".equals(area.IN.acci)) {
			hcon.setPres("S");
		} else if ("E".equals(area.IN.acci)) {
			hcon.setPres("N");
		}
		
		//Guardamos el apunte
		BsHconSaveArea bsHconSaveArea = new BsHconSaveArea();
		bsHconSaveArea.IN.hcon = hcon;
		bsHconSave.execute(bsHconSaveArea);
		hcon = bsHconSaveArea.OUT.hcon;
		
		//Si estamos incluyendo:
		// - Restamos del importe no presupuestado
		// - Sumamos al importe presupuestado
		//Si estamos excluyendo:
		// - Restamos el importe presupuestado
		// - Sumamos al importe no presupuesto
		if ("I".equals(area.IN.acci)) {
			pres.setImnp(pres.getImnp() - hcon.getImpo());
			pres.setImpr(pres.getImpr() + hcon.getImpo());
		} else if ("E".equals(area.IN.acci)) {
			pres.setImnp(pres.getImnp() + hcon.getImpo());
			pres.setImpr(pres.getImpr() - hcon.getImpo());
		}
		
		//En cualquier caso, recalculamos desviaci�n y balance
		pres.setDesv(pres.getImpr() - pres.getImpo());
		pres.setBala(pres.getImnp() + pres.getDesv());
		
		//Grabamos la partida presupuestaria
		BsPresSaveArea bsPresSaveArea = new BsPresSaveArea();
		bsPresSaveArea.IN.pres = pres;
		bsPresSave.executeBS(bsPresSaveArea);
		pres = bsPresSaveArea.OUT.pres;
		
		area.OUT.hcon = hcon;
		area.OUT.pres = pres;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsHconPresGestArea area = (BsHconPresGestArea)a;
		
		validateIntRequired(area.IN.iden, AppNotify.HCON_PRES_GEST_IDEN_RQRD);
		validateStringRequired(area.IN.acci, AppNotify.HCON_PRES_GEST_ACCI_RQRD);
		
		if (!"I".equals(area.IN.acci) &&  //Incluir apunte en presupuesto
		   (!"E".equals(area.IN.acci))) {  //Excluir apunte de presupuesto
			notify(AppNotify.HCON_PRES_GEST_ACCI_ERRO);
		}
	}
}