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
import es.ldrsoftware.fecon.prp.entity.Pres;
import es.ldrsoftware.fecon.prp.entity.PresDAO;

@Component
public class BsPresList extends BaseBS {
	
	@Autowired
	public PresDAO presDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsPresListArea area = (BsPresListArea)a;
		
		List<Pres> presList = new ArrayList<Pres>();
		List<Pres> presListAnua = new ArrayList<Pres>();
		Map<Long, List<Pres>> presListMap = new HashMap<Long, List<Pres>>();
		ListIterator<Pres> it;

		switch(area.IN.tipo) {
		case BsPresListArea.LIST_TIPO_RESUMEN:
			 //Consulta de resumen por año
			 presList = presDao.getGroupList(SESSION.get().inst);
			 break;
		case BsPresListArea.LIST_TIPO_ANUAL:
			 //Consulta para un año: por una parte los meses, y por otra parte las partidas anuales
			 presList = presDao.getGroupListByAnua(SESSION.get().inst, area.IN.anua);
			 presListAnua = presDao.getListByAnuaMesp(SESSION.get().inst, area.IN.anua, 0);
			 break;
		case BsPresListArea.LIST_TIPO_MENSUAL:
			 //Consulta agrupada por categorias para un mes
			 presList = presDao.getGroupCateListByAnuaMesp(SESSION.get().inst, area.IN.anua, area.IN.mesp);
			 it = presList.listIterator();
			 while(it.hasNext()) {
				 Pres pres = it.next();
				 List<Pres> presCateList = presDao.getListByAnuaMespCate(SESSION.get().inst, pres.getAnua(), pres.getMesp(), pres.getCate());
				 presListMap.put(pres.getCate(), presCateList);
			 }
			 break;
		case BsPresListArea.LIST_TIPO_CONC_ANUA:
			 //Consulta agrupada por categorias para un año
			 presList = presDao.getGroupCateListByAnua(SESSION.get().inst, area.IN.anua);
			 it = presList.listIterator();
			 while(it.hasNext()) {
				 Pres pres = it.next();
				 List<Pres> presCateList = presDao.getGroupConcListByAnuaCate(SESSION.get().inst, pres.getAnua(), pres.getCate());
				 presListMap.put(pres.getCate(), presCateList);
			 }
			 break;
		}
		
		area.OUT.presList = presList;
		area.OUT.presListAnua = presListAnua;
		area.OUT.presListMap  = presListMap;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsPresListArea area = (BsPresListArea)a;
		
		validateStringRequired(area.IN.tipo, AppNotify.PRES_LIST_TIPO_RQRD);
		
		switch (area.IN.tipo) {
		case BsPresListArea.LIST_TIPO_RESUMEN:
			 break;
		case BsPresListArea.LIST_TIPO_ANUAL:
			 validateIntRequired(area.IN.anua, AppNotify.PRES_LIST_ANUA_RQRD);
			 break;
		case BsPresListArea.LIST_TIPO_MENSUAL:
			 validateIntRequired(area.IN.anua, AppNotify.PRES_LIST_ANUA_RQRD);
			 validateIntRequired(area.IN.mesp, AppNotify.PRES_LIST_MESP_RQRD);
			 break;
		case BsPresListArea.LIST_TIPO_CONC_ANUA:
			 validateIntRequired(area.IN.anua, AppNotify.PRES_LIST_ANUA_RQRD);
			 break;
		default:
			 notify(AppNotify.PRES_LIST_TIPO_ERRO);
			 break;
		}
		
	}
}