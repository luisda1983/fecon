package es.ldrsoftware.fecon.prp.bs;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.bs.BsParaGet;
import es.ldrsoftware.core.fwk.bs.BsParaGetArea;
import es.ldrsoftware.core.fwk.data.PVParser;
import es.ldrsoftware.core.fwk.entity.Para;
import es.ldrsoftware.fecon.data.ParaData;
import es.ldrsoftware.fecon.cnt.bs.BsCuenList;
import es.ldrsoftware.fecon.cnt.bs.BsCuenListArea;
import es.ldrsoftware.fecon.cnt.bs.BsHconList;
import es.ldrsoftware.fecon.cnt.bs.BsHconListArea;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.PVPeripresup;
import es.ldrsoftware.fecon.prp.entity.Pres;

@Component
public class BsPresCons extends BaseBS {
	
	@Autowired
	public BsPresList bsPresList;
	
	@Autowired
	BsParaGet bsParaGet;
	
	@Autowired
	BsHconList bsHconList;
	
	@Autowired
	BsCuenList bsCuenList;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsPresConsArea area = (BsPresConsArea)a;

		//TODO: StringUtil
		Long l = SESSION.get().inst;
		String s = l.toString();
		while (s.length() < 10) {
			s = "0" + s;
		}

		BsParaGetArea bsParaGetArea = new BsParaGetArea();
		bsParaGetArea.IN.tbla = ParaData.PARA_TBLA_PEPR;
		bsParaGetArea.IN.clav = s;
		bsParaGet.executeBS(bsParaGetArea);
		
		Para para = bsParaGetArea.OUT.para;
		PVParser.parse(para);
		PVPeripresup pvPeripresup = (PVPeripresup)para.getPval();

		//TODO: cuando la consulta permita mostrar el saldo de cuentas a fecha, no sera necesario 
		// validar mes en curso, pues la foto será correcta
		if (pvPeripresup.anac != area.IN.anua || pvPeripresup.msac != area.IN.mesp) {
			notify(AppNotify.PRES_CONS_MESP_ERRO);
		}
		
		List<Pres> presList;
		
		BsPresListArea bsPresListArea = new BsPresListArea();
		bsPresListArea.IN.tipo = BsPresListArea.LIST_TIPO_MENSUAL_PRESUP;
		bsPresListArea.IN.anua = area.IN.anua;
		bsPresListArea.IN.mesp = area.IN.mesp;
		bsPresList.executeBS(bsPresListArea);
		
		area.OUT.presList = bsPresListArea.OUT.presList;
		
		bsPresListArea = new BsPresListArea();
		bsPresListArea.IN.tipo = BsPresListArea.LIST_TIPO_PART_ANUALES;
		bsPresListArea.IN.anua = area.IN.anua;
		bsPresList.executeBS(bsPresListArea);
		
		presList = new ArrayList<Pres>();
		
		ListIterator<Pres> it = bsPresListArea.OUT.presList.listIterator();
		
		while (it.hasNext()) {
			Pres pres = it.next();
			
			BsHconListArea bsHconListArea = new BsHconListArea();
			if (pres.getConc() != 0) {
				bsHconListArea.IN.tipo = BsHconList.LT_TIPO_MES_SUM;
			} else {
				bsHconListArea.IN.tipo = BsHconList.LT_TIPO_MES_CATE_SUM;
			}
			bsHconListArea.IN.anua = area.IN.anua;
			bsHconListArea.IN.mesh = area.IN.mesp;
			bsHconListArea.IN.cate = pres.getCate();
			bsHconListArea.IN.conc = pres.getConc();
			bsHconList.executeBS(bsHconListArea);
			
			Pres pres_2 = new Pres();
			pres_2.setInst(pres.getInst());
			pres_2.setAnua(pres.getAnua());
			pres_2.setCate(pres.getCate());
			pres_2.setConc(pres.getConc());
			pres_2.setImpo(bsHconListArea.OUT.impoSum);
			pres_2.setEsta(pres.getEsta());
			
			presList.add(pres_2);
		}
		
		area.OUT.presAnuaList = presList;
		
		BsCuenListArea bsCuenListArea = new BsCuenListArea();
		bsCuenList.executeBS(bsCuenListArea);
		
		area.OUT.cuenList = bsCuenListArea.OUT.cuenList;
		
		BsHconListArea bsHconListArea = new BsHconListArea();
		bsHconListArea.IN.tipo = BsHconList.LT_TIPO_SUM_MES_NPRE;
		bsHconListArea.IN.anua = area.IN.anua;
		bsHconListArea.IN.mesh = area.IN.mesp;
		bsHconList.executeBS(bsHconListArea);
		
		area.OUT.impoNpre = bsHconListArea.OUT.impoSum;
		
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsPresConsArea area = (BsPresConsArea)a;
		
		validateInputField(area.IN.anua, Pres.ANUA);
		validateInputField(area.IN.mesp, Pres.MESP);
	}
}