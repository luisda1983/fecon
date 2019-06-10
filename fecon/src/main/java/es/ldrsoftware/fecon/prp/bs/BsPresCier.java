package es.ldrsoftware.fecon.prp.bs;

import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.bs.BsParaGet;
import es.ldrsoftware.core.fwk.bs.BsParaGetArea;
import es.ldrsoftware.core.fwk.bs.BsParaSave;
import es.ldrsoftware.core.fwk.bs.BsParaSaveArea;
import es.ldrsoftware.core.fwk.data.PVParser;
import es.ldrsoftware.core.fwk.entity.Para;
import es.ldrsoftware.fecon.data.ParaData;
import es.ldrsoftware.fecon.cnt.bs.BsCuenList;
import es.ldrsoftware.fecon.cnt.bs.BsHconList;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.LiteData;
import es.ldrsoftware.fecon.data.PVPeripresup;
import es.ldrsoftware.fecon.prp.entity.Pres;

@Component
public class BsPresCier extends BaseBS {
	
	@Autowired
	public BsPresList bsPresList;
	
	@Autowired
	BsParaGet bsParaGet;

	@Autowired
	BsParaSave bsParaSave;
	
	@Autowired
	BsHconList bsHconList;
	
	@Autowired
	BsCuenList bsCuenList;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsPresCierArea area = (BsPresCierArea)a;

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

		if (pvPeripresup.anac != area.IN.anua || pvPeripresup.msac != area.IN.mesp) {
			notify(AppNotify.PRES_CIER_MESP_ERRO);
		}
		
		BsPresListArea bsPresListArea = new BsPresListArea();
		bsPresListArea.IN.tipo = BsPresListArea.LIST_TIPO_MENSUAL_PRESUP;
		bsPresListArea.IN.anua = area.IN.anua;
		bsPresListArea.IN.mesp = area.IN.mesp;
		bsPresList.executeBS(bsPresListArea);
		
		ListIterator<Pres> itPres = bsPresListArea.OUT.presList.listIterator();
		
		while (itPres.hasNext()) {
			Pres pres = itPres.next();
			
			if (!LiteData.LT_EL_PRESESTA_CERRADA.equals(pres.getEsta())) {
				notify(AppNotify.PRES_CIER_PART_CERR_NO);
			}
		}
		
		//TODO: utilidad de tratamiento de peripresup
		if (pvPeripresup.msac == 12) {
			pvPeripresup.msac = 1;
			pvPeripresup.anac++;
		} else {
			pvPeripresup.msac++;
		}
		area.OUT.anua = pvPeripresup.anac;
		area.OUT.mesp = pvPeripresup.msac;
		
		para.setValo(pvPeripresup.format());
		
		BsParaSaveArea bsParaSaveArea = new BsParaSaveArea();
		bsParaSaveArea.IN.para = para;
		bsParaSave.executeBS(bsParaSaveArea);
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsPresCierArea area = (BsPresCierArea)a;
		
		validateIntRequired(area.IN.anua, AppNotify.PRES_CIER_ANUA_RQRD);
		validateIntRequired(area.IN.mesp, AppNotify.PRES_CIER_MESP_RQRD);
	}
}