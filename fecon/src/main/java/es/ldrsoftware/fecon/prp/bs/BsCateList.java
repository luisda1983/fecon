package es.ldrsoftware.fecon.prp.bs;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.prp.entity.Cate;
import es.ldrsoftware.fecon.prp.entity.CateDAO;

@Component
public class BsCateList extends BaseBS {

	@Autowired
	CateDAO cateDao;;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsCateListArea area = (BsCateListArea)a;
		
		List<Cate> cateList = cateDao.getList(SESSION.get().inst);
		
		ListIterator<Cate> it = cateList.listIterator();
		Map<Long, Cate> cateListMap = new HashMap<Long, Cate>();
		
		while (it.hasNext()) {
			Cate cate = it.next();
			cateListMap.put(new Long(cate.getIden()), cate);
		}
		
		area.OUT.cateList = cateList;
		area.OUT.cateListMap = cateListMap;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsCateListArea area = (BsCateListArea)a;
	}

}
