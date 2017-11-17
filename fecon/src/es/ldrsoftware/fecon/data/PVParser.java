package es.ldrsoftware.fecon.data;

import es.ldrsoftware.core.fwk.data.PV;
import es.ldrsoftware.core.fwk.data.PVParamaster;
import es.ldrsoftware.core.fwk.entity.Para;

public class PVParser {

	public static PV parse(Para para) {
		PV pval;
		
		switch(para.getTbla()) {
		case ParaData.PARA_TBLA_MSTR:
			 pval = new PVParamaster();
			 break;
		case ParaData.PARA_TBLA_PEPR:
			 pval = new PVPeripresup();
			 break;
		default:
			 pval = null;
			 return pval;
		}
		pval.parse(para.getValo());
		para.setPval(pval);
		return pval;
	}
}