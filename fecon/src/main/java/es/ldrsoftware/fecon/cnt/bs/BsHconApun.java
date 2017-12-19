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
import ldrsoftware.app.domain.HConc;

@Component
public class BsHconApun extends BaseBS {

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
	
	@Autowired
	public IHConcDAO concDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsHconApunArea area = (BsHconApunArea)a;
		
		HConc hConc = concDao.getByIden(SESSION.get().inst, area.IN.conc);
		
		if (hConc == null) {
			notify(AppNotify.HCON_APUN_CONC_NF);
		} else {
			if (!hConc.isActi()) {
				notify(AppNotify.HCON_APUN_CONC_NO_ACTI);
			}
			if ("G".equals(hConc.getTipo())){
				if (area.IN.impo  > 0) {
					notify(AppNotify.HCON_APUN_CONC_IMPO_ERRO);
				}
			} else {
				if (area.IN.impo < 0) {
					notify(AppNotify.HCON_APUN_CONC_IMPO_ERRO);
				}
			}
		}
	
		BsCuenGetArea bsCuenGetArea = new BsCuenGetArea();
		bsCuenGetArea.IN.iden = area.IN.cuen;
		bsCuenGet.executeBS(bsCuenGetArea);
		Cuen cuen = bsCuenGetArea.OUT.cuen;

		if (cuen == null) {
			notify(AppNotify.HCON_APUN_CUEN_NF);
		}
		
		cuen.setSald(cuen.getSald() + area.IN.impo);
		BsCuenSaveArea bsCuenSaveArea = new BsCuenSaveArea();
		bsCuenSaveArea.IN.cuen = cuen;
		bsCuenSave.executeBS(bsCuenSaveArea);

		area.OUT.cuen = bsCuenSaveArea.OUT.cuen;
		
		//Vamos montando el apunte, aunque se ejecute tras el tratamiento del presupuesto
		Hcon hcon = new Hcon();
		hcon.setInst(SESSION.get().inst);
		hcon.setCuen(area.IN.cuen);
		hcon.setTipo("C");
		hcon.setFeop(SESSION.get().feop);
		hcon.setHoop(SESSION.get().hoop);
		hcon.setFeva(area.IN.feva);
		hcon.setCate(area.IN.cate);
		hcon.setConc(area.IN.conc);
		hcon.setImpo(area.IN.impo);
		hcon.setDesc(area.IN.desc);
		hcon.setUsua(SESSION.get().usua);

		BsPresGetArea bsPresGetArea = new BsPresGetArea();
		bsPresGetArea.IN.fech = area.IN.feva;
		bsPresGetArea.IN.cate = area.IN.cate;
		bsPresGetArea.IN.conc = area.IN.conc;
		bsPresGet.executeBS(bsPresGetArea);		
		Pres pres = bsPresGetArea.OUT.pres;

		//Mientras no se determine la exclusión de presupuesto mediante la entrada, la partida indicará
		//si computa contra presupuesto o no
		if (pres.getImpo() != 0) {
			pres.setImpr(pres.getImpr() + hcon.getImpo());
			pres.setDesv(pres.getImpr() - pres.getImpo());
			hcon.setPres("S");
		} else {
			pres.setImnp(pres.getImnp() + hcon.getImpo());
			hcon.setPres("N");
		}
		
		pres.setImto(pres.getImto() + hcon.getImpo());
		pres.setBala(pres.getImnp() + pres.getDesv());

		BsPresSaveArea bsPresSaveArea = new BsPresSaveArea();
		bsPresSaveArea.IN.pres = pres;
		bsPresSave.executeBS(bsPresSaveArea);

		//Guardamos en el apunte la partida asociada
		hcon.setPran(pres.getAnua());
		hcon.setPrms(pres.getMesp());
		hcon.setPrct(pres.getCate());
		hcon.setPrcc(pres.getConc());
		
		BsHconSaveArea bsHconSaveArea = new BsHconSaveArea();
		bsHconSaveArea.IN.hcon = hcon;
		bsHconSave.executeBS(bsHconSaveArea);
		area.OUT.hcon = bsHconSaveArea.OUT.hcon;
		
		//Informativo
		notify(AppNotify.HCON_APUN_OK);
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsHconApunArea area = (BsHconApunArea)a;
		
		validateIntRequired(area.IN.cate, AppNotify.HCON_APUN_CATE_RQRD);
		validateIntRequired(area.IN.conc, AppNotify.HCON_APUN_CONC_RQRD);
		validateDecRequired(area.IN.impo, AppNotify.HCON_APUN_IMPO_RQRD);
		validateIntRequired(area.IN.cuen, AppNotify.HCON_APUN_CUEN_RQRD);
		
		if (area.IN.feva == 0) {
			area.IN.feva = SESSION.get().feop;
		}
	}
}