package es.ldrsoftware.core.spt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.spt.entity.Domi;
import es.ldrsoftware.core.spt.entity.DomiDAO;

@Component
public class BsDomiSave extends BaseBS {
	
	@Autowired
	DomiDAO domiDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDomiSaveArea area = (BsDomiSaveArea)a;

		//Guardamos el dominio
		Domi domi = domiDao.save(area.IN.domi);
		
		area.OUT.domi = domi;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsDomiSaveArea area = (BsDomiSaveArea)a;

		Domi domi = (Domi)validateDto(area.IN.domi, LiteData.LT_EL_DTO_DOMI); 
	}
}