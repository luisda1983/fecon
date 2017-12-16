package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.cnt.entity.Hcon;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.prp.bs.BsPresGet;
import es.ldrsoftware.fecon.prp.bs.BsPresGetArea;
import es.ldrsoftware.fecon.prp.bs.BsPresSave;
import es.ldrsoftware.fecon.prp.bs.BsPresSaveArea;
import es.ldrsoftware.fecon.prp.entity.Pres;
import ldrsoftware.app.dao.IHConcDAO;

@Component
public class BsHconApunAnul extends BaseBS {

	@Autowired
	public BsCuenGet bsCuenGet;

	@Autowired
	public BsCuenSave bsCuenSave;

	@Autowired
	public BsHconSave bsHconSave;

	@Autowired
	public BsPresGet bsPresGet;

	@Autowired
	public BsPresSave bsPresSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsHconApunAnulArea area = (BsHconApunAnulArea)a;
		
		Hcon hcon = area.IN.hcon;
		
		//Convivencia
		BsCuenGetArea bsCuenGetArea = new BsCuenGetArea();
		bsCuenGetArea.IN.iden = hcon.getCuen();
		bsCuenGet.executeBS(bsCuenGetArea);
		Cuen cuen = bsCuenGetArea.OUT.cuen;
		
		if (cuen == null) {
			notify(AppNotify.HCON_APUN_ANUL_CUEN_NF);
		}
		
		cuen.setSald(cuen.getSald() - hcon.getImpo());
		
		BsCuenSaveArea bsCuenSaveArea = new BsCuenSaveArea();
		bsCuenSaveArea.IN.cuen = cuen;
		bsCuenSave.executeBS(bsCuenSaveArea);
		
		hcon.setTipo("A");
		hcon.setDesc("[ANULADO] " + hcon.getDesc());
		
		BsHconSaveArea bsHconSaveArea = new BsHconSaveArea();
		bsHconSaveArea.IN.hcon = hcon;
		bsHconSave.execute(bsHconSaveArea);
		hcon = bsHconSaveArea.OUT.hcon;
		
		BsPresGetArea bsPresGetArea = new BsPresGetArea();
		bsPresGetArea.IN.fech = hcon.getFeva();
		//FIXME: usar las variables del area de presupuesto del apunte
		bsPresGetArea.IN.cate = hcon.getCate();
		bsPresGetArea.IN.conc = hcon.getConc();
		bsPresGet.executeBS(bsPresGetArea);
		
		Pres pres = bsPresGetArea.OUT.pres;

		//Mientras no se determine la exclusión de presupuesto mediante la entrada, la partida indicará
		//si computa contra presupuesto o no. En la anulación, el propio movimiento indicará si computó
		//contra presupuesto o no
		if (pres.getImpo() != 0) {
			pres.setImpr(pres.getImpr() - hcon.getImpo());
		} else {
			pres.setImnp(pres.getImnp() - hcon.getImpo());
		}

		pres.setImto(pres.getImto() - hcon.getImpo());
		pres.setDesv(pres.getImto() - pres.getImpo());

		BsPresSaveArea bsPresSaveArea = new BsPresSaveArea();
		bsPresSaveArea.IN.pres = pres;
		bsPresSave.executeBS(bsPresSaveArea);
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsHconApunAnulArea area = (BsHconApunAnulArea)a;
		
		validateDto(area.IN.hcon, AppNotify.HCON_APUN_ANUL_HCON_RQRD);
		
	}
}