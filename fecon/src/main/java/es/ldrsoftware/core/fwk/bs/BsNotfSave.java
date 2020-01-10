package es.ldrsoftware.core.fwk.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
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
		
		Notf notf = (Notf)validateDto(area.IN.notf, LiteData.LT_EL_DTO_NOTF); 
		notf.validate();
				
	}
}