package es.ldrsoftware.fecon.cnt.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Coes;
import es.ldrsoftware.fecon.cnt.entity.CoesDAO;

@Component
public class BsCoesList extends BaseBS {

	@Autowired
	CoesDAO coesDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCoesListArea area = (BsCoesListArea)a;
		
		List<Coes> coesList = coesDao.getList(SESSION.get().inst);
				
		area.OUT.coesList = coesList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsCoesListArea area = (BsCoesListArea)a;
		
	}

}
