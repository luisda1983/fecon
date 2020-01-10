package es.ldrsoftware.core.sts.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.sts.entity.Stme;
import es.ldrsoftware.core.sts.entity.StmeDao;

@Component
public class BsStmeSave extends BaseBS {

	@Autowired
	private StmeDao stmeDao;

	protected void execute(BaseBSArea a) throws Exception {
		BsStmeSaveArea area = (BsStmeSaveArea)a;
		
		Stme stme = area.IN.stme;
		
		stme = stmeDao.save(stme);
		
		area.OUT.stme = stme;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsStmeSaveArea area = (BsStmeSaveArea)a;

		Stme stme = (Stme)validateDto(area.IN.stme, LiteData.LT_EL_DTO_STME); 
		stme.validate();		
	}

}
