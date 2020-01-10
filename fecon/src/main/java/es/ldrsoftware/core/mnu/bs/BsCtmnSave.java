package es.ldrsoftware.core.mnu.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.mnu.entity.Ctmn;
import es.ldrsoftware.core.mnu.entity.CtmnDAO;

@Component
public class BsCtmnSave extends BaseBS {

	@Autowired
	CtmnDAO ctmnDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCtmnSaveArea area = (BsCtmnSaveArea)a;

		//Guardamos la categoría de menú
		Ctmn ctmn = ctmnDao.save(area.IN.ctmn);
		
		area.OUT.ctmn = ctmn;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsCtmnSaveArea area = (BsCtmnSaveArea)a;

		//Validamos que la categoría de menú está informada
		Ctmn ctmn = (Ctmn)validateDto(area.IN.ctmn, LiteData.LT_EL_DTO_CTMN); 
		ctmn.validate();
		
	}

}
