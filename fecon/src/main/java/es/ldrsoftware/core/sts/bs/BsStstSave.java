package es.ldrsoftware.core.sts.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.sts.entity.Stst;
import es.ldrsoftware.core.sts.entity.StstDao;

@Component
public class BsStstSave extends BaseBS {

	@Autowired
	private StstDao ststDao;

	protected void execute(BaseBSArea a) throws Exception {
		BsStstSaveArea area = (BsStstSaveArea)a;
		
		Stst stst = area.IN.stst;
		
		stst = ststDao.save(stst);
		
		area.OUT.stst = stst;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsStstSaveArea area = (BsStstSaveArea)a;

		Stst stst = (Stst)validateDto(area.IN.stst, LiteData.LT_EL_DTO_STST); 
		stst.validate();
	}

}
