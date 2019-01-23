package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.cnt.entity.Hcon;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.LiteData;

@Component
public class BsHconTras extends BaseBS {

	@Autowired
	public BsCuenGet bsCuenGetk;

	@Autowired
	public BsCuenSave bsCuenSave;

	@Autowired
	public BsHconSave bsHconSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsHconTrasArea area = (BsHconTrasArea)a;
	
		if (area.IN.impo <= 0) {
			notify(AppNotify.HCON_TRAS_IMPO_POSI);
		}

		if (area.IN.ctde == area.IN.ctor) {
			notify(AppNotify.HCON_TRAS_MISM_CTAS);
		}
				
		BsCuenGetArea bsCuenGetkArea = new BsCuenGetArea();
		bsCuenGetkArea.IN.iden = area.IN.ctor;
		bsCuenGetk.executeBS(bsCuenGetkArea);
		
		Cuen ctor = bsCuenGetkArea.OUT.cuen;
		validateDtoRequired(ctor, AppNotify.HCON_TRAS_CTOR_NF);
		
		if (ctor.getSald() < area.IN.impo) {
			notify(AppNotify.HCON_TRAS_SALD_INSF);
		}

		bsCuenGetkArea = new BsCuenGetArea();
		bsCuenGetkArea.IN.iden = area.IN.ctde;
		bsCuenGetk.execute(bsCuenGetkArea);
		
		Cuen ctde = bsCuenGetkArea.OUT.cuen;
		validateDtoRequired(ctde, AppNotify.HCON_TRAS_CTDE_NF);
		
		ctor.setSald(ctor.getSald() - area.IN.impo);
		ctde.setSald(ctde.getSald() + area.IN.impo);
		
		Hcon hcor = new Hcon();
		hcor.setInst(SESSION.get().inst);
		hcor.setCuen(area.IN.ctor);
		hcor.setTipo(LiteData.LT_EL_HCONTIPO_TRASPASO);
		hcor.setFeop(SESSION.get().feop);
		hcor.setHoop(SESSION.get().hoop);
		hcor.setFeva(area.IN.feva);
		hcor.setCate(0);
		hcor.setConc(0);
		hcor.setImpo((-1)*area.IN.impo);
		hcor.setDesc("TRASPASO EMITIDO A " + ctde.getDesc());
		hcor.setUsua(SESSION.get().usua);
		hcor.setPres("");
		hcor.setPran(0);
		hcor.setPrms(0);
		hcor.setPrct(0);
		hcor.setPrcc(0);
				
		BsHconSaveArea bsHconSaveArea = new BsHconSaveArea();
		bsHconSaveArea.IN.hcon = hcor;
		bsHconSave.executeBS(bsHconSaveArea);
		
		area.OUT.hcor = bsHconSaveArea.OUT.hcon;
		
		BsCuenSaveArea bsCuenSaveArea = new BsCuenSaveArea();
		bsCuenSaveArea.IN.cuen = ctor;
		bsCuenSave.execute(bsCuenSaveArea);
		
		area.OUT.ctor = bsCuenSaveArea.OUT.cuen;
		
		Hcon hcde = new Hcon();
		hcde.setInst(SESSION.get().inst);
		hcde.setCuen(area.IN.ctde);
		hcde.setTipo(LiteData.LT_EL_HCONTIPO_TRASPASO);
		hcde.setFeop(SESSION.get().feop);
		hcde.setHoop(SESSION.get().hoop);
		hcde.setFeva(area.IN.feva);
		hcde.setCate(0);
		hcde.setConc(0);
		hcde.setImpo(area.IN.impo);
		hcde.setDesc("TRASPASO RECIBIDO DE " + ctor.getDesc());
		hcde.setUsua(SESSION.get().usua);
		hcde.setPres("");
		hcde.setPran(0);
		hcde.setPrms(0);
		hcde.setPrct(0);
		hcde.setPrcc(0);
		
		bsHconSaveArea = new BsHconSaveArea();
		bsHconSaveArea.IN.hcon = hcde;
		bsHconSave.executeBS(bsHconSaveArea);
		
		area.OUT.hcde = bsHconSaveArea.OUT.hcon;
		
		bsCuenSaveArea = new BsCuenSaveArea();
		bsCuenSaveArea.IN.cuen = ctde;
		bsCuenSave.execute(bsCuenSaveArea);
		
		area.OUT.ctde = bsCuenSaveArea.OUT.cuen;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsHconTrasArea area = (BsHconTrasArea)a;
		
		validateIntRequired(area.IN.ctor, AppNotify.HCON_TRAS_CTOR_RQRD);
		validateIntRequired(area.IN.ctde, AppNotify.HCON_TRAS_CTDE_RQRD);
		validateDecRequired(area.IN.impo, AppNotify.HCON_TRAS_IMPO_RQRD);
		validateIntRequired(area.IN.feva, AppNotify.HCON_TRAS_FEVA_RQRD);

	}
}