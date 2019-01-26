package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.cnt.entity.CuenDAO;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.LiteData;

@Component
public class BsCuenSave extends BaseBS {
	
	@Autowired
	public CuenDAO cuenDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCuenSaveArea area = (BsCuenSaveArea)a;
		
		Cuen cuen = cuenDao.save(area.IN.cuen);
		
		area.OUT.cuen = cuen;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCuenSaveArea area = (BsCuenSaveArea)a;
		
		Cuen cuen = area.IN.cuen;
		
		validateDtoRequired(cuen, AppNotify.CUEN_SAVE_CUEN_RQRD);
		
		validateIntRequired(cuen.getIden(), AppNotify.CUEN_SAVE_IDEN_RQRD);
		validateIntRange(cuen.getIden(), 0, 999999999, AppNotify.CUEN_SAVE_IDEN_RNGE);
		
		validateIntRequired(cuen.getInst(), AppNotify.CUEN_SAVE_INST_RQRD);
		validateIntRange(cuen.getInst(), 0, 999999999, AppNotify.CUEN_SAVE_INST_RNGE);
		
		validateStringRequired(cuen.getTipo(), AppNotify.CUEN_SAVE_TIPO_RQRD);
		validateStringDomain(AppNotify.CUEN_SAVE_TIPO_ERRO, cuen.getTipo(), LiteData.LT_ST_CUENTIPO);
		
		validateStringRequired(cuen.getDesc(), AppNotify.CUEN_SAVE_DESC_RQRD);
		validateStringMaxLength(cuen.getDesc(), 100, AppNotify.CUEN_SAVE_DESC_MAXL);
		
		validateDecRange(cuen.getSald(), -99999999999.99, 99999999999.99, AppNotify.CUEN_SAVE_SALD_RNGE);
		
		validateIntRequired(cuen.getFeal(), AppNotify.CUEN_SAVE_FEAL_RQRD);
		validateIntRange(cuen.getFeal(), 19000101, 29991231, AppNotify.CUEN_SAVE_FEAL_RNGE);
		
		validateIntRange(cuen.getHoal(), 000000, 235959, AppNotify.CUEN_SAVE_HOAL_RNGE);
		
		validateStringRequired(cuen.getUsal(), AppNotify.CUEN_SAVE_USAL_RQRD);
		validateStringMaxLength(cuen.getUsal(), 30, AppNotify.CUEN_SAVE_USAL_MAXL);
		
		validateIntRequired(cuen.getFemo(), AppNotify.CUEN_SAVE_FEMO_RQRD);
		validateIntRange(cuen.getFemo(), 19000101, 29991231, AppNotify.CUEN_SAVE_FEMO_RNGE);
		
		validateIntRange(cuen.getHomo(), 000000, 235959, AppNotify.CUEN_SAVE_HOMO_RNGE);
		
		validateStringRequired(cuen.getUsmo(), AppNotify.CUEN_SAVE_USMO_RQRD);
		validateStringMaxLength(cuen.getUsmo(), 30, AppNotify.CUEN_SAVE_USMO_MAXL);
	}
}