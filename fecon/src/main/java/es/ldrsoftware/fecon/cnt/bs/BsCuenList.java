package es.ldrsoftware.fecon.cnt.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.cnt.entity.CuenDAO;

@Component
public class BsCuenList extends BaseBS {

	@Autowired
	CuenDAO cuenDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCuenListArea area = (BsCuenListArea)a;
		
		List<Cuen> cuenList = cuenDao.getList(SESSION.get().inst);
				
		area.OUT.cuenList = cuenList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsCuenListArea area = (BsCuenListArea)a;
		
	}

}
