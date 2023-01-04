package es.ldrsoftware.fecon.cnt.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Trad;
import es.ldrsoftware.fecon.cnt.entity.TradDAO;

@Component
public class BsTradGetk extends BaseBS {
	
	@Autowired
	public TradDAO tradDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsTradGetkArea area = (BsTradGetkArea)a;
		
		Trad trad = tradDao.getByIden(SESSION.get().inst, area.IN.iden);
		
		area.OUT.trad = trad;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsTradGetkArea area = (BsTradGetkArea)a;
		
		validateInputField(area.IN.iden, Trad.IDEN);
	}
}