package es.ldrsoftware.core.spt.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.spt.entity.Domi;
import es.ldrsoftware.core.spt.entity.DomiDAO;

@Component
public class BsDomiList extends BaseBS {
	
	@Autowired
	DomiDAO domiDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsDomiListArea area = (BsDomiListArea)a;

		//Obtenemos la lista de dominios
		List<Domi> domiList = domiDao.getList(SESSION.get().inst);
		
		area.OUT.domiList = domiList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsDomiListArea area = (BsDomiListArea)a;
	}
}