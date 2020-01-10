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

	public final static String LT_TIPO_CONC_POSI    = "LTCONCPOSI";
	public final static String LT_TIPO_CONC_NEGA    = "LTCONCNEGA";
	public final static String LT_TIPO_MES_SUM      = "LTMESSUM";
	public final static String LT_TIPO_MES_CATE_SUM = "LTMESCTSUM";
	public final static String LT_TIPO_SUM_MES_NPRE = "LTSUMMESNP";
	
	protected void execute(BaseBSArea a) throws Exception {
		BsHconListArea area = (BsHconListArea)a;
		
		List<Hcon> hconList = new ArrayList<Hcon>();
		double impoSum = 0;
		
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
			case LT_TIPO_MES_SUM:
				 fevaInic = area.IN.anua * 10000 + area.IN.mesh * 100;
				 fevaFina = area.IN.anua * 10000 + area.IN.mesh * 100 + 99;
				 impoSum = hconDao.getSumByFevaCateConc(SESSION.get().inst, fevaInic, fevaFina, area.IN.cate, area.IN.conc);
				 break;
			case LT_TIPO_MES_CATE_SUM:
				 fevaInic = area.IN.anua * 10000 + area.IN.mesh * 100;
				 fevaFina = area.IN.anua * 10000 + area.IN.mesh * 100 + 99;
				 impoSum = hconDao.getSumByFevaCate(SESSION.get().inst, fevaInic, fevaFina, area.IN.cate);
				 break;
			case LT_TIPO_SUM_MES_NPRE:
				 fevaInic = area.IN.anua * 10000 + area.IN.mesh * 100;
				 fevaFina = area.IN.anua * 10000 + area.IN.mesh * 100 + 99;
				 impoSum = hconDao.getSumByFevaPres(SESSION.get().inst, fevaInic, fevaFina, "N");
				 break;
		}
		
		area.OUT.hconList = hconList;
		area.OUT.impoSum  = impoSum;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsHconListArea area = (BsHconListArea)a;
		
		validateInputField(area.IN.tipo, Hcon.TIPO_LIST);
		
		switch(area.IN.tipo) {
			case LiteData.LT_EL_HCONLTTIPO_MES:
				 validateInputField(area.IN.anua, Hcon.ANUA);
				 validateInputField(area.IN.mesh, Hcon.MESH);
				 break;
			case LiteData.LT_EL_HCONLTTIPO_CONC_MES:
				 validateInputField(area.IN.anua, Hcon.ANUA);
				 validateInputField(area.IN.mesh, Hcon.MESH);
				 validateInputField(area.IN.cate, Hcon.CATE);
				 break;
			case LiteData.LT_EL_HCONLTTIPO_CONC_ANUA:
				 validateInputField(area.IN.anua, Hcon.ANUA);
				 validateInputField(area.IN.cate, Hcon.CATE);
				 break;
			case LT_TIPO_CONC_POSI:
			case LT_TIPO_CONC_NEGA:
				 validateInputField(area.IN.cate, Hcon.CATE);
				 validateInputField(area.IN.conc, Hcon.CONC);
				 break;
			case LT_TIPO_MES_SUM:
				 validateInputField(area.IN.cate, Hcon.CATE);
				 validateInputField(area.IN.conc, Hcon.CONC);
				 validateInputField(area.IN.mesh, Hcon.MESH);
				 break;
			case LT_TIPO_MES_CATE_SUM:
				 validateInputField(area.IN.anua, Hcon.ANUA);
				 validateInputField(area.IN.cate, Hcon.CATE);
				 validateInputField(area.IN.mesh, Hcon.MESH);
			case LT_TIPO_SUM_MES_NPRE:
				 validateInputField(area.IN.anua, Hcon.ANUA);
				 validateInputField(area.IN.mesh, Hcon.MESH);
				 break;
			default:
				notify(AppNotify.HCON_LIST_TIPO_ERRO);
				break;
		}
	}
}