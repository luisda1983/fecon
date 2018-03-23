package es.ldrsoftware.core.fwk.data;

import es.ldrsoftware.core.fwk.entity.Rela;

public class RDParser {

	public static void parse(Rela rela) {
		RD rdat;
		
		if (RelaData.RELA_MAE1_INST.equals(rela.getMae1()) && RelaData.RELA_MAE2_USUA.equals(rela.getMae2())) {
			rdat = new RDInstUsua();
		} else {
			rdat = null;
			//TODO: Bridge
//			 pval = PVParserBridge.parse(para);
//			 if (pval == null) {
//				 return;
//			 }
			return;
		}

		rdat.parse(rela.getData());
		rela.setRdat(rdat);
	}
}