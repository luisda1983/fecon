package es.ldrsoftware.fecon.cnt.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Trad;
import es.ldrsoftware.fecon.cnt.entity.TradDAO;

@Component
public class BsTradList extends BaseBS {

	@Autowired
	TradDAO tradDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsTradListArea area = (BsTradListArea)a;
		
		List<Trad> tradList = tradDao.getList(SESSION.get().inst);
				
		area.OUT.tradList = tradList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsTradListArea area = (BsTradListArea)a;
		
	}

}
