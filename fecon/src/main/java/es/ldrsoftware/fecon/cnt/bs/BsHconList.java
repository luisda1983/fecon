package es.ldrsoftware.fecon.cnt.bs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.cnt.entity.Hcon;
import es.ldrsoftware.fecon.cnt.entity.HconDAO;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.LiteData;

@Component
public class BsHconList extends BaseBS {
	
	@Autowired
	public HconDAO hconDao;

	public final static String LT_TIPO_CONC_POSI = "LTCONCPOSI";
	public final static String LT_TIPO_CONC_NEGA = "LTCONCNEGA";
	
	protected void execute(BaseBSArea a) throws Exception {
		BsHconListArea area = (BsHconListArea)a;
		
		List<Hcon> hconList = new ArrayList<Hcon>();
		
		int fevaInic = 0;
		int fevaFina = 0;
		
		switch(area.IN.tipo) {
			case LiteData.LT_EL_HCONLTTIPO_MES:
				 fevaInic = area.IN.anua * 10000 + area.IN.mesh * 100;
				 fevaFina = area.IN.anua * 10000 + area.IN.mesh * 100 + 99;
				 hconList = hconDao.getListByFeva(SESSION.get().inst, fevaInic, fevaFina);
				 break;
			case LiteData.LT_EL_HCONLTTIPO_CONC_MES:
				 fevaInic = area.IN.anua * 10000 + area.IN.mesh * 100;
				 fevaFina = area.IN.anua * 10000 + area.IN.mesh * 100 + 99;
				 if (area.IN.conc == 0) {
					 hconList = hconDao.getListByFevaCate(SESSION.get().inst, fevaInic, fevaFina, area.IN.cate);
				 } else {
					 hconList = hconDao.getListByFevaCateConc(SESSION.get().inst, fevaInic, fevaFina, area.IN.cate, area.IN.conc);
				 }
				 break;
			case LiteData.LT_EL_HCONLTTIPO_CONC_ANUA:
				 fevaInic = area.IN.anua * 10000;
				 fevaFina = area.IN.anua * 10000 + 1299;
				 if (area.IN.conc == 0) {
					 hconList = hconDao.getListByFevaCate(SESSION.get().inst, fevaInic, fevaFina, area.IN.cate);
				 } else {
					 hconList = hconDao.getListByFevaCateConc(SESSION.get().inst, fevaInic, fevaFina, area.IN.cate, area.IN.conc);
				 }
				 break;
			case LT_TIPO_CONC_NEGA:
				 hconList = hconDao.getListByCateConcNega(SESSION.get().inst, area.IN.cate, area.IN.conc);
				 break;
			case LT_TIPO_CONC_POSI:
				 hconList = hconDao.getListByCateConcPosi(SESSION.get().inst, area.IN.cate, area.IN.conc);
				 break;
		}
		
		area.OUT.hconList = hconList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsHconListArea area = (BsHconListArea)a;
		
		validateStringRequired(area.IN.tipo, AppNotify.HCON_LIST_TIPO_RQRD);
		
		switch(area.IN.tipo) {
			case LiteData.LT_EL_HCONLTTIPO_MES:
				 validateIntRequired(area.IN.anua, AppNotify.HCON_LIST_ANUA_RQRD);
				 validateIntRequired(area.IN.mesh, AppNotify.HCON_LIST_MESH_RQRD);
				 break;
			case LiteData.LT_EL_HCONLTTIPO_CONC_MES:
				 validateIntRequired(area.IN.anua, AppNotify.HCON_LIST_ANUA_RQRD);
				 validateIntRequired(area.IN.mesh, AppNotify.HCON_LIST_MESH_RQRD);
				 validateIntRequired(area.IN.cate, AppNotify.HCON_LIST_CATE_RQRD);
				 break;
			case LiteData.LT_EL_HCONLTTIPO_CONC_ANUA:
				 validateIntRequired(area.IN.anua, AppNotify.HCON_LIST_ANUA_RQRD);
				 validateIntRequired(area.IN.cate, AppNotify.HCON_LIST_CATE_RQRD);
				 break;
			case LT_TIPO_CONC_POSI:
			case LT_TIPO_CONC_NEGA:
				 validateIntRequired(area.IN.cate, AppNotify.HCON_LIST_CATE_RQRD);
				 validateIntRequired(area.IN.conc, AppNotify.HCON_LIST_CONC_RQRD);
				 break;
			default:
				notify(AppNotify.HCON_LIST_TIPO_ERRO);
				break;
		}
	}
}