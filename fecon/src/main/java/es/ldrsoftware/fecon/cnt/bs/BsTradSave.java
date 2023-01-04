package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Trad;
import es.ldrsoftware.fecon.cnt.entity.TradDAO;
import es.ldrsoftware.fecon.data.LiteData;

@Component
public class BsTradSave extends BaseBS {
	
	@Autowired
	public TradDAO tradDao;

	protected void execute(BaseBSArea a) throws Exception {
		BsTradSaveArea area = (BsTradSaveArea)a;
		
		Trad trad = tradDao.save(area.IN.trad);
		
		area.OUT.trad = trad;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsTradSaveArea area = (BsTradSaveArea)a;
		
		Trad trad = (Trad)validateDto(area.IN.trad, LiteData.LT_EL_DTO_TRAD); 
	}
}