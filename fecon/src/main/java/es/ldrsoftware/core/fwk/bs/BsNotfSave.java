package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.entity.Notf;
import es.ldrsoftware.core.fwk.entity.NotfDAO;

@Component
public class BsNotfSave extends BaseBS {

	@Autowired
	public NotfDAO notfDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsNotfSaveArea area = (BsNotfSaveArea)a;
	
		Notf notf = notfDao.save(area.IN.notf);
		
		area.OUT.notf = notf;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsNotfSaveArea area = (BsNotfSaveArea)a;
		
		validateDtoRequired(area.IN.notf, CoreNotify.NOTF_SAVE_NOTF_RQRD);
		
		Notf notf = area.IN.notf;
		
		validateStringRequired(notf.getIden(), CoreNotify.NOTF_SAVE_IDEN_RQRD);
		validateStringMaxLength(notf.getIden(), 10, CoreNotify.NOTF_SAVE_IDEN_MAXL);
		
		validateStringRequired(notf.getTipo(), CoreNotify.NOTF_SAVE_TIPO_RQRD);
		validateStringDomain(CoreNotify.NOTF_SAVE_TIPO_ERRO, notf.getTipo(), LiteData.LT_ST_NOTFTIPO);
		
		validateStringRequired(notf.getDesc(), CoreNotify.NOTF_SAVE_DESC_RQRD);
		validateStringMaxLength(notf.getDesc(), 200, CoreNotify.NOTF_SAVE_DESC_MAXL);		
	}
}