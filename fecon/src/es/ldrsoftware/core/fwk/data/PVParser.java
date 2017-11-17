package es.ldrsoftware.core.fwk.data;

import es.ldrsoftware.core.fwk.entity.Para;
import es.ldrsoftware.corebridge.PVParserBridge;

public class PVParser {

	public static void parse(Para para) {
		PV pval;
		
		switch(para.getTbla()) {
		case ParaData.PARA_TBLA_MSTR:
			 pval = new PVParamaster();
			 break;
		case ParaData.PARA_TBLA_FPER:
		case ParaData.PARA_TBLA_BTAV:
			 pval = new PVFechperiod();
			 break;
		case ParaData.PARA_TBLA_CPER:
			 pval = new PVCtrlperiod();
			 break;
		case ParaData.PARA_TBLA_APCF:
			 switch(para.getClav()) {
			 case ParaData.PARA_ELEM_APCF_MLTI:
			 	  pval = new PVConfigmlti();
				  break;
			 case ParaData.PARA_ELEM_APCF_CFRG:
				  pval = new PVConfregist();
				  break;
			 default:
				  pval = null;
				  return;
			 }
			 break;
		case ParaData.PARA_TBLA_DYNF:
			 pval = new PVDynamicfld();
			 break;
		default:
			 //Invocamos al BRIDGE, que será el encargado de buscar, si procede, en los parámetros de la 
			 //aplicación residente.
			 pval = PVParserBridge.parse(para);
			 if (pval == null) {
				 return;
			 }
		}
		pval.parse(para.getValo());
		para.setPval(pval);
	}
}