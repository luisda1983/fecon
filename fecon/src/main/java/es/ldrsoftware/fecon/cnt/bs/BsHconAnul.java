package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.cnt.entity.Hcon;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.LiteData;
import es.ldrsoftware.fecon.prp.bs.BsPresCalc;
import es.ldrsoftware.fecon.prp.bs.BsPresCalcArea;
import es.ldrsoftware.fecon.prp.bs.BsPresSave;
import es.ldrsoftware.fecon.prp.bs.BsPresSaveArea;
import es.ldrsoftware.fecon.prp.entity.Pres;

@Component
public class BsHconAnul extends BaseBS {

	@Autowired
	public BsHconGetk bsHconGetk;

	@Autowired
	public BsHconSave bsHconSave;

	@Autowired
	public BsCuenGetk bsCuenGet;

	@Autowired
	public BsCuenSave bsCuenSave;

	@Autowired
	public BsPresCalc bsPresCalc;

	@Autowired
	public BsPresSave bsPresSave;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsHconAnulArea area = (BsHconAnulArea)a;

		BsHconGetkArea bsHconGetkArea = new BsHconGetkArea();
		bsHconGetkArea.IN.iden = area.IN.iden;
		bsHconGetk.execute(bsHconGetkArea);
		
		Hcon hcon = bsHconGetkArea.OUT.hcon;
		
		if (!LiteData.LT_EL_HCONTIPO_CONTABLE.equals(hcon.getTipo())){
			notify(AppNotify.HCON_ANUL_TIPO_NO_ANUL);
		}
		
		BsCuenGetkArea bsCuenGetArea = new BsCuenGetkArea();
		bsCuenGetArea.IN.iden = hcon.getCuen();
		bsCuenGet.executeBS(bsCuenGetArea);
		Cuen cuen = bsCuenGetArea.OUT.cuen;
	
		validateDtoRequired(cuen, AppNotify.HCON_APUN_CUEN_NF);
		
		cuen.setSald(cuen.getSald() - hcon.getImpo());
		
		BsCuenSaveArea bsCuenSaveArea = new BsCuenSaveArea();
		bsCuenSaveArea.IN.cuen = cuen;
		bsCuenSave.executeBS(bsCuenSaveArea);
		
		cuen = bsCuenSaveArea.OUT.cuen;
		area.OUT.cuen = bsCuenSaveArea.OUT.cuen;
		
		hcon.setTipo(LiteData.LT_EL_HCONTIPO_ANULADO);
		hcon.setDesc("[ANULADO] " + hcon.getDesc());
		
		BsHconSaveArea bsHconSaveArea = new BsHconSaveArea();
		bsHconSaveArea.IN.hcon = hcon;
		bsHconSave.execute(bsHconSaveArea);
		
		hcon = bsHconSaveArea.OUT.hcon;
		area.OUT.hcon = bsHconSaveArea.OUT.hcon;

		BsPresCalcArea bsPresCalcArea = new BsPresCalcArea();
		bsPresCalcArea.IN.fech = hcon.getFeva();
		//TODO: cambiar el presCalc para obtener a partir de los anua/mesp y no desglose por fecha
		//bsPresCalcArea.IN.anua = hcon.getPran();
		//bsPresCalcArea.IN.mesp = hcon.getPrms();
		//TODO: cambiar el presCalc para ir a tiro hecho a por la partida y no la busque
		bsPresCalcArea.IN.cate = hcon.getCate();
		bsPresCalcArea.IN.conc = hcon.getConc();
		//bsPresCalcArea.IN.cate = hcon.getPrct();
		//bsPresCalcArea.IN.conc = hcon.getPrcc();
		bsPresCalc.executeBS(bsPresCalcArea);
		
		Pres pres = bsPresCalcArea.OUT.pres;
		
		if (LiteData.LT_EL_PRESESTA_CERRADA.equals(pres.getEsta())) {
			notify(AppNotify.HCON_ANUL_PRES_CERR);
		}
		
		if (es.ldrsoftware.core.fwk.data.LiteData.LT_EL_BOOL_SI.equals(hcon.getPres())) {
			pres.setImpr(pres.getImpr() - hcon.getImpo());
			pres.setDesv(pres.getImpr() - pres.getImpo());
		} else {
			pres.setImnp(pres.getImnp() - hcon.getImpo());
		}

		pres.setImto(pres.getImto() - hcon.getImpo());
		pres.setBala(pres.getImnp() + pres.getDesv());

		BsPresSaveArea bsPresSaveArea = new BsPresSaveArea();
		bsPresSaveArea.IN.pres = pres;
		bsPresSave.executeBS(bsPresSaveArea);
		
		pres = bsPresSaveArea.OUT.pres;
		area.OUT.pres = bsPresSaveArea.OUT.pres;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsHconAnulArea area = (BsHconAnulArea)a;
		
		validateIntRequired(area.IN.iden, AppNotify.HCON_ANUL_IDEN_RQRD);
	}
}