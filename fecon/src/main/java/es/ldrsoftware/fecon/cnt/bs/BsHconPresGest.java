package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Hcon;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.LiteData;
import es.ldrsoftware.fecon.prp.bs.BsPresGet;
import es.ldrsoftware.fecon.prp.bs.BsPresGetArea;
import es.ldrsoftware.fecon.prp.bs.BsPresSave;
import es.ldrsoftware.fecon.prp.bs.BsPresSaveArea;
import es.ldrsoftware.fecon.prp.entity.Pres;

@Component
public class BsHconPresGest extends BaseBS {

	@Autowired
	public BsHconGetk bsHconGetk;
	
	@Autowired
	public BsHconSave bsHconSave;

	@Autowired
	public BsPresGet bsPresGetk;

	@Autowired
	public BsPresSave bsPresSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsHconPresGestArea area = (BsHconPresGestArea)a;
		
		//Obtenemos el apunte contable.
		BsHconGetkArea bsHconGetkArea = new BsHconGetkArea();
		bsHconGetkArea.IN.iden = area.IN.iden;
		bsHconGetk.execute(bsHconGetkArea);
		
		Hcon hcon = bsHconGetkArea.OUT.hcon;
		
		//No existe el apunte
		validateDtoRequired(hcon, AppNotify.HCON_PRES_GEST_HCON_NF);
		
		//Sólo se pueden gestionar apuntes contables
		if (!LiteData.LT_EL_HCONTIPO_CONTABLE.equals(hcon.getTipo())) {
			notify(AppNotify.HCON_PRES_GEST_TIPO_NO_CONT);
		}
		
		//Error: El apunte ya estï¿½ incluido en el presupuesto
		if (es.ldrsoftware.core.fwk.data.LiteData.LT_EL_BOOL_SI.equals(hcon.getPres()) && "I".equals(area.IN.acci)) {
			notify(AppNotify.HCON_PRES_GEST_APUN_INCL);
		}
		
		//Error: El apunte ya estï¿½ excluido del presupuesto
		if (es.ldrsoftware.core.fwk.data.LiteData.LT_EL_BOOL_NO.equals(hcon.getPres()) && "E".equals(area.IN.acci)) {
			notify(AppNotify.HCON_PRES_GEST_APUN_EXCL);
		}
		
		//Obtenemos la partida presupuestaria
		BsPresGetArea bsPresGetkArea = new BsPresGetArea();
		bsPresGetkArea.IN.anua = hcon.getPran();
		bsPresGetkArea.IN.mesp = hcon.getPrms();
		bsPresGetkArea.IN.cate = hcon.getPrct();
		bsPresGetkArea.IN.conc = hcon.getPrcc();
		bsPresGetk.executeBS(bsPresGetkArea);
		
		Pres pres = bsPresGetkArea.OUT.pres;
		
		//Apunte mal relacionado con partida presupuestaria
		validateDtoRequired(pres, AppNotify.HCON_PRES_GEST_PRES_NF);
		
		//Error: No se puede incluir sobre una partida no presupuestada
		if ("I".equals(area.IN.acci) && pres.getImpo() == 0) {
			notify(AppNotify.HCON_PRES_GEST_PART_NPRE);
		}
		
		//Modificamos el indicador de presupuestado en el apunte
		if ("I".equals(area.IN.acci)) {
			hcon.setPres(es.ldrsoftware.core.fwk.data.LiteData.LT_EL_BOOL_SI);
		} else if ("E".equals(area.IN.acci)) {
			hcon.setPres(es.ldrsoftware.core.fwk.data.LiteData.LT_EL_BOOL_NO);
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
		
		//En cualquier caso, recalculamos desviaciï¿½n y balance
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