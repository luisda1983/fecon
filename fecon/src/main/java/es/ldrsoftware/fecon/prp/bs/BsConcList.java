package es.ldrsoftware.fecon.prp.bs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.prp.entity.Conc;
import es.ldrsoftware.fecon.prp.entity.ConcDAO;

@Component
public class BsConcList extends BaseBS {

	@Autowired
	ConcDAO concDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsConcListArea area = (BsConcListArea)a;

		List<Conc> concList = new ArrayList<Conc>(); 
		
		switch(area.IN.tipo) {
		case BsConcListArea.LIST_TIPO_FULL:
			 concList = concDao.getList(SESSION.get().inst);
			 break;
		case BsConcListArea.LIST_TIPO_CATE:
			 concList = concDao.getListByCate(SESSION.get().inst, area.IN.cate);
			 break;
		}
		
		ListIterator<Conc> it = concList.listIterator();
		Map<Long, Conc> concListMap = new HashMap<Long, Conc>();
		
		while (it.hasNext()) {
			Conc conc = it.next();
			concListMap.put(new Long(conc.getIden()), conc);
		}
		
		area.OUT.concList = concList;
		area.OUT.concListMap = concListMap;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsConcListArea area = (BsConcListArea)a;
		
		validateInputField(area.IN.tipo, Conc.TIPO_LIST);
		
		switch(area.IN.tipo) {
		case BsConcListArea.LIST_TIPO_CATE:
			 validateInputField(area.IN.cate, Conc.CATE);
			 break;
		case BsConcListArea.LIST_TIPO_FULL:
			 break;
		default:
			 notify(AppNotify.CONC_LIST_TIPO_ERRO);
			 break;
		}
	}

}
