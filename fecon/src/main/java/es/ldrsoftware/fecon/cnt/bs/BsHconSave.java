package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Hcon;
import es.ldrsoftware.fecon.cnt.entity.HconDAO;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.LiteData;

@Component
public class BsHconSave extends BaseBS {
	
	@Autowired
	public HconDAO hconDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsHconSaveArea area = (BsHconSaveArea)a;
		
		Hcon hcon = hconDao.save(area.IN.hcon);
		
		area.OUT.hcon = hcon;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsHconSaveArea area = (BsHconSaveArea)a;
		
		validateDtoRequired(area.IN.hcon, AppNotify.HCON_SAVE_HCON_RQRD);
		
		Hcon hcon = area.IN.hcon;
		
		validateIntRequired(hcon.getInst(), AppNotify.HCON_SAVE_INST_RQRD);
		validateIntRange(hcon.getInst(), 0, 999999999, AppNotify.HCON_SAVE_INST_RNGE);
		
		validateIntRequired(hcon.getCuen(), AppNotify.HCON_SAVE_CUEN_RQRD);
		validateIntRange(hcon.getCuen(), 0, 999999999, AppNotify.HCON_SAVE_CUEN_RNGE);
		
		validateStringRequired(hcon.getTipo(), AppNotify.HCON_SAVE_TIPO_RQRD);
		validateStringDomain(AppNotify.HCON_SAVE_TIPO_ERRO, hcon.getTipo(), LiteData.LT_ST_HCONTIPO);
		
		validateIntRequired(hcon.getFeop(),  AppNotify.HCON_SAVE_FEOP_RQRD);
		validateIntRange(hcon.getFeop(), 19000101, 29991231, AppNotify.HCON_SAVE_FEOP_RNGE);
		
		validateIntRange(hcon.getHoop(), 0, 235959, AppNotify.HCON_SAVE_HOOP_RNGE);
		
		validateIntRequired(hcon.getFeva(),  AppNotify.HCON_SAVE_FEVA_RQRD);
		validateIntRange(hcon.getFeva(), 0, 29991231, AppNotify.HCON_SAVE_FEVA_RNGE);
	
		if (!LiteData.LT_EL_HCONTIPO_TRASPASO.equals(hcon.getTipo())) {
			validateIntRequired(hcon.getCate(), AppNotify.HCON_SAVE_CATE_RQRD);
			validateIntRange(hcon.getCate(), 0, 999999999, AppNotify.HCON_SAVE_CATE_RNGE);
		
			validateIntRequired(hcon.getConc(), AppNotify.HCON_SAVE_CONC_RQRD);
			validateIntRange(hcon.getConc(), 0, 999999999, AppNotify.HCON_SAVE_CONC_RNGE);
		
			validateStringRequired(hcon.getPres(), AppNotify.HCON_SAVE_PRES_RQRD);
			validateStringDomain(AppNotify.HCON_SAVE_PRES_ERRO, hcon.getPres(), es.ldrsoftware.core.fwk.data.LiteData.LT_ST_BOOL);
		
			validateIntRequired(hcon.getPran(), AppNotify.HCON_SAVE_PRAN_RQRD);
			validateIntRange(hcon.getPran(), 2000, 2999, AppNotify.HCON_SAVE_PRAN_RNGE);
		
			validateIntRange(hcon.getPrms(), 0, 12, AppNotify.HCON_SAVE_PRMS_RNGE);
		
			validateIntRequired(hcon.getPrct(), AppNotify.HCON_SAVE_PRCT_RQRD);
			validateIntRange(hcon.getPrct(), 0, 999999999, AppNotify.HCON_SAVE_PRCT_RNGE);
		
			validateIntRange(hcon.getPrcc(), 0, 999999999, AppNotify.HCON_SAVE_PRCC_RNGE);
		}
		
		validateDecRequired(hcon.getImpo(), AppNotify.HCON_SAVE_IMPO_RQRD);
		validateDecRange(hcon.getImpo(), -99999999999.99, 99999999999.99, AppNotify.HCON_SAVE_IMPO_RNGE);
		
		//TODO: cuando el desc se monte en función del concrepto: 
		//validateStringRequired(hcon.getDesc(), AppNotify.HCON_SAVE_DESC_RQRD);
		validateStringNotNull(hcon.getDesc(), AppNotify.HCON_SAVE_DESC_RQRD);
		validateStringMaxLength(hcon.getDesc(), 200, AppNotify.HCON_SAVE_DESC_MAXL);
		
		validateStringRequired(hcon.getUsua(), AppNotify.HCON_SAVE_USUA_RQRD);
		validateStringMaxLength(hcon.getUsua(), 30, AppNotify.HCON_SAVE_USUA_MAXL);
	}
}