package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.cnt.entity.CuenDAO;
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
		
		Cuen cuen = (Cuen)validateDto(area.IN.cuen, LiteData.LT_EL_DTO_CUEN); 
		cuen.validate();
	}
}