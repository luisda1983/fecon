package es.ldrsoftware.core.fwk.data;

public class ParaData {

	public final static String PARA_TBLA_MSTR      = "PARAMASTER";
	public final static String PARA_ELEM_MSTR_FPER = "FECHPERIOD";
	public final static String PARA_ELEM_MSTR_BTAV = "BTCHAVANCE";
	public final static String PARA_ELEM_MSTR_CPER = "CTRLPERIOD";
	public final static String PARA_ELEM_MSTR_APCF = "APLICONFIG";
	public final static String PARA_ELEM_MSTR_DYNF = "DYNAMICFLD";
	public final static String   PM_TB_PARAMASTER            = "PARAMASTER";
	public final static String   PM_EL_PARAMASTER_BTCHAVANCE = "BTCHAVANCE";
	public final static String[] PM_ST_PARAMASTER            = {PM_EL_PARAMASTER_BTCHAVANCE};
	
	public final static String PARA_TBLA_FPER      = "FECHPERIOD";
	public final static String PARA_ELEM_FPER_RSES = "RENOSESION";
	public final static String PARA_ELEM_FPER_RBTC = "RENPROCBTC";
	public final static String PARA_ELEM_FPER_PREM = "RENPREMIUM";

	public final static String PARA_TBLA_BTAV      = "BTCHAVANCE";
	public final static String PARA_ELEM_BTAV_DIAR = "DI";
	public final static String PARA_ELEM_BTAV_MENS = "MS";
	public final static String   PM_TB_BTCHAVANCE    = "BTCHAVANCE";
	public final static String   PM_EL_BTCHAVANCE_DIARIO  = "DI";
	public final static String   PM_EL_BTCHAVANCE_MENSUAL = "MS";
	public final static String[] PM_ST_BTCHAVANCE         = {PM_EL_BTCHAVANCE_DIARIO,
			                                                 PM_EL_BTCHAVANCE_MENSUAL};
	
	public final static String PARA_TBLA_CPER      = "CTRLPERIOD";
	public final static String PARA_ELEM_CPER_FBTC = "FECPROCBTC";
	
	public final static String PARA_TBLA_APCF      = "APLICONFIG";
	public final static String PARA_ELEM_APCF_MLTI = "MULTIINSTA";
	public final static String PARA_ELEM_APCF_CFRG = "CONFREGIST";
	
	public final static String PARA_TBLA_DYNF      = "DYNAMICFLD";
	public final static String PARA_ELEM_DYNF_RIDE = "REGINVDESC";
	
}