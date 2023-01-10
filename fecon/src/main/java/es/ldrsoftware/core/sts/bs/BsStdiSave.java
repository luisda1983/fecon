package es.ldrsoftware.core.sts.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.sts.entity.Stdi;
import es.ldrsoftware.core.sts.entity.StdiDao;

@Component
public class BsStdiSave extends BaseBS {

	@Autowired
	private StdiDao stdiDao;

	protected void execute(BaseBSArea a) throws Exception {
		BsStdiSaveArea area = (BsStdiSaveArea)a;
		
		Stdi stdi = area.IN.stdi;
		
		stdi = stdiDao.save(stdi);
		
		area.OUT.stdi = stdi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsStdiSaveArea area = (BsStdiSaveArea)a;

		Stdi stdi = (Stdi)validateDto(area.IN.stdi, LiteData.LT_EL_DTO_STDI); 
				
	}

}
