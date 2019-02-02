package es.ldrsoftware.fecon.data;

public class AppNotify {

	//*********************************************************************************************//
	// Notificaciones del mÃ³dulo CNT                                                              //
	//*********************************************************************************************//

	//Servicio de grabado de apunte
	public final static String HCON_SAVE_HCON_RQRD         = "HCON-01001";
	public final static String HCON_SAVE_INST_RQRD         = "HCON-01701";
	public final static String HCON_SAVE_CUEN_RQRD         = "HCON-01702";
	public final static String HCON_SAVE_TIPO_RQRD         = "HCON-01703";
	public final static String HCON_SAVE_FEOP_RQRD         = "HCON-01704";
	public final static String HCON_SAVE_FEVA_RQRD         = "HCON-01705";
	public final static String HCON_SAVE_CATE_RQRD         = "HCON-01706";
	public final static String HCON_SAVE_CONC_RQRD         = "HCON-01707";
	public final static String HCON_SAVE_PRES_RQRD         = "HCON-01708";
	public final static String HCON_SAVE_PRAN_RQRD         = "HCON-01709";
	public final static String HCON_SAVE_PRCT_RQRD         = "HCON-01711";
	public final static String HCON_SAVE_IMPO_RQRD         = "HCON-01712";
	public final static String HCON_SAVE_DESC_RQRD         = "HCON-01713";
	public final static String HCON_SAVE_USUA_RQRD         = "HCON-01714";
	public final static String HCON_SAVE_INST_RNGE         = "HCON-01801";
	public final static String HCON_SAVE_CUEN_RNGE         = "HCON-01802";
	public final static String HCON_SAVE_TIPO_ERRO         = "HCON-01803";
	public final static String HCON_SAVE_FEOP_RNGE         = "HCON-01804";
	public final static String HCON_SAVE_HOOP_RNGE         = "HCON-01805";
	public final static String HCON_SAVE_FEVA_RNGE         = "HCON-01806";
	public final static String HCON_SAVE_CATE_RNGE         = "HCON-01807";
	public final static String HCON_SAVE_CONC_RNGE         = "HCON-01808";
	public final static String HCON_SAVE_PRES_ERRO         = "HCON-01809";
	public final static String HCON_SAVE_PRAN_RNGE         = "HCON-01810";
	public final static String HCON_SAVE_PRMS_RNGE         = "HCON-01811";
	public final static String HCON_SAVE_PRCT_RNGE         = "HCON-01812";
	public final static String HCON_SAVE_PRCC_RNGE         = "HCON-01813";
	public final static String HCON_SAVE_IMPO_RNGE         = "HCON-01814";
	public final static String HCON_SAVE_DESC_MAXL         = "HCON-01815";
	public final static String HCON_SAVE_USUA_MAXL         = "HCON-01816";
	
	//Servicio de realización de apunte contable
	public final static String HCON_APUN_CATE_RQRD         = "HCON-02001";
	public final static String HCON_APUN_CONC_RQRD         = "HCON-02002";
	public final static String HCON_APUN_IMPO_RQRD         = "HCON-02003";
	public final static String HCON_APUN_CUEN_RQRD         = "HCON-02004";
	public final static String HCON_APUN_OK                = "HCON-02301";
	public final static String HCON_APUN_CONC_GAST         = "HCON-02501";
	public final static String HCON_APUN_CONC_INGR         = "HCON-02502";
	public final static String HCON_APUN_PRES_CERR         = "HCON-02503";
	public final static String HCON_APUN_CONC_NF           = "HCON-02901";
	public final static String HCON_APUN_CUEN_NF           = "HCON-02902";
	public final static String HCON_APUN_PRES_NF           = "HCON-02903";
	
	//Servicio de consulta de apunte contable
	public final static String HCON_GETK_IDEN_RQRD         = "HCON-03001";
	
	//Servicio de anulación de apunte contable
	public final static String HCON_ANUL_IDEN_RQRD         = "HCON-04001";
	public final static String HCON_ANUL_TIPO_NO_ANUL      = "HCON-04501";
	public final static String HCON_ANUL_PRES_CERR         = "HCON-04502";
	public final static String HCON_ANUL_CUEN_NF           = "HCON-05901";
	
	//Servicio de apunte de traspaso
	public final static String HCON_TRAS_CTOR_RQRD         = "HCON-05001";
	public final static String HCON_TRAS_CTDE_RQRD         = "HCON-05002";
	public final static String HCON_TRAS_FEVA_RQRD         = "HCON-05003";
	public final static String HCON_TRAS_IMPO_RQRD         = "HCON-05004";	
	public final static String HCON_TRAS_IMPO_POSI         = "HCON-05501";
	public final static String HCON_TRAS_MISM_CTAS         = "HCON-05502";
	public final static String HCON_TRAS_SALD_INSF         = "HCON-05503";
	public final static String HCON_TRAS_CTOR_NF           = "HCON-05901";
	public final static String HCON_TRAS_CTDE_NF           = "HCON-05902";
	
	//Servicio de modificación de apunte contable
	public final static String HCON_MODI_TIPO_RQRD         = "HCON-06001";
	public final static String HCON_MODI_IDEN_RQRD         = "HCON-06002";
	public final static String HCON_MODI_FEVA_RQRD         = "HCON-06003";
	public final static String HCON_MODI_TIPO_ERRO         = "HCON-06501";
	public final static String HCON_MODI_CONT_NO           = "HCON-06502";
	public final static String HCON_MODI_CHGN_NO           = "HCON-06503";
	public final static String HCON_MODI_FECH_MES          = "HCON-06504";
	public final static String HCON_MODI_HCON_NF           = "HCON-06901";
	
	//Servicio de lista de apuntes contables
	public final static String HCON_LIST_TIPO_RQRD         = "HCON-07001";
	public final static String HCON_LIST_ANUA_RQRD         = "HCON-07002";
	public final static String HCON_LIST_MESH_RQRD         = "HCON-07003";
	public final static String HCON_LIST_CATE_RQRD         = "HCON-07004";
	public final static String HCON_LIST_TIPO_ERRO         = "HCON-07501";

	//Servicio de gestión de apunte respecto al presupuesto
	public final static String HCON_PRES_GEST_IDEN_RQRD    = "HCON-08001";
	public final static String HCON_PRES_GEST_ACCI_RQRD    = "HCON-08002";
	public final static String HCON_PRES_GEST_ACCI_ERRO    = "HCON-08501";
	public final static String HCON_PRES_GEST_TIPO_NO_CONT = "HCON-08502";
	public final static String HCON_PRES_GEST_APUN_INCL    = "HCON-08503";
	public final static String HCON_PRES_GEST_APUN_EXCL    = "HCON-08504";
	public final static String HCON_PRES_GEST_PART_NPRE    = "HCON-08505";
	public final static String HCON_PRES_GEST_HCON_NF      = "HCON-08901";
	public final static String HCON_PRES_GEST_PRES_NF      = "HCON-08902";
	
	//Servicio de grabado de cuenta
	public final static String CUEN_SAVE_CUEN_RQRD         = "CUEN-01001";	
	public final static String CUEN_SAVE_IDEN_RQRD         = "CUEN-01701";
	public final static String CUEN_SAVE_INST_RQRD         = "CUEN-01702";
	public final static String CUEN_SAVE_TIPO_RQRD         = "CUEN-01703";
	public final static String CUEN_SAVE_DESC_RQRD         = "CUEN-01704";
	public final static String CUEN_SAVE_FEAL_RQRD         = "CUEN-01705";
	public final static String CUEN_SAVE_USAL_RQRD         = "CUEN-01706";
	public final static String CUEN_SAVE_FEMO_RQRD         = "CUEN-01707";
	public final static String CUEN_SAVE_USMO_RQRD         = "CUEN-01708";
	public final static String CUEN_SAVE_IDEN_RNGE         = "CUEN-01801";
	public final static String CUEN_SAVE_INST_RNGE         = "CUEN-01802";
	public final static String CUEN_SAVE_TIPO_ERRO         = "CUEN-01803";
	public final static String CUEN_SAVE_DESC_MAXL         = "CUEN-01804";
	public final static String CUEN_SAVE_SALD_RNGE         = "CUEN-01805";
	public final static String CUEN_SAVE_FEAL_RNGE         = "CUEN-01806";
	public final static String CUEN_SAVE_HOAL_RNGE         = "CUEN-01807";
	public final static String CUEN_SAVE_USAL_MAXL         = "CUEN-01808";
	public final static String CUEN_SAVE_FEMO_RNGE         = "CUEN-01809";
	public final static String CUEN_SAVE_HOMO_RNGE         = "CUEN-01810";
	public final static String CUEN_SAVE_USMO_MAXL         = "CUEN-01811";
	
	//Servicio de consulta de cuenta
	public final static String CUEN_GETC_IDEN_RQRD         = "CUEN-02001";
	
	//Servicio de alta y edición de cuenta
	public final static String CUEN_FORM_TIPO_RQRD         = "CUEN-03001";
	public final static String CUEN_FORM_DESC_RQRD         = "CUEN-03002";
	public final static String CUEN_FORM_SALD_MODI_NPER    = "CUEN-03501";
	public final static String CUEN_FORM_CUEN_NF           = "CUEN-03901";
	
	//Servicio de cuadre de cuenta
	public final static String CUEN_CUAD_CUEN_RQRD         = "CUEN-04001";
	public final static String CUEN_CUAD_CATE_RQRD         = "CUEN-04002";
	public final static String CUEN_CUAD_CONC_RQRD         = "CUEN-04003";
	public final static String CUEN_CUAD_IMPO_RQRD         = "CUEN-04003";
	public final static String CUEN_CUAD_CUEN_NF           = "CUEN-04901";

	public final static String CUEN_TRAS_CTOR_RQRD         = "CUEN-05001";
	public final static String CUEN_TRAS_CTDE_RQRD         = "CUEN-05002";
	public final static String CUEN_TRAS_IMPO_RQRD         = "CUEN-05003";

	
	//*********************************************************************************************//
	// Notificaciones del mÃ³dulo PRP                                                              //
	//*********************************************************************************************//

	//Servicio de grabado de Categorías
	public final static String CATE_SAVE_CATE_RQRD         = "CATE-01001";
	public final static String CATE_SAVE_INST_RQRD         = "CATE-01701";
	public final static String CATE_SAVE_DESL_RQRD         = "CATE-01702";
	public final static String CATE_SAVE_DESC_RQRD         = "CATE-01703";
	public final static String CATE_SAVE_ORDE_RQRD         = "CATE-01704";
	public final static String CATE_SAVE_PRES_RQRD         = "CATE-01705";
	public final static String CATE_SAVE_INST_RNGE         = "CATE-01801";
	public final static String CATE_SAVE_DESL_MAXL         = "CATE-01802";
	public final static String CATE_SAVE_DESC_MAXL         = "CATE-01803";
	public final static String CATE_SAVE_ORDE_RNGE         = "CATE-01804";
	public final static String CATE_SAVE_PRES_ERRO         = "CATE-01805";
	
	//Servicio de consulta de categoría
	public final static String CATE_GETK_IDEN_RQRD         = "CATE-02001";
	
	//Servicio de consulta de categoría por descripción
	public final static String CATE_GETD_DESL_RQRD         = "CATE-03001";
	public final static String CATE_GETD_DESC_RQRD         = "CATE-03002";

	//Servicio de alta y edición de categoría
	public final static String CATE_FORM_DESL_RQRD         = "CATE-04001";
	public final static String CATE_FORM_DESC_RQRD         = "CATE-04002";
	public final static String CATE_FORM_PRES_RQRD         = "CATE-04003";
	public final static String CATE_FORM_DESL_DP           = "CATE-04901";
	public final static String CATE_FORM_DESC_DP           = "CATE-04902";
	public final static String CATE_FORM_CATE_NF           = "CATE-04903";

	//Servicio de grabado de concepto
	public final static String CONC_SAVE_CONC_RQRD         = "CONC-01001";
	public final static String CONC_SAVE_CATE_RQRD         = "CONC-01701";
	public final static String CONC_SAVE_INST_RQRD         = "CONC-01702";
	public final static String CONC_SAVE_TIPO_RQRD         = "CONC-01703";
	public final static String CONC_SAVE_DESL_RQRD         = "CONC-01704";
	public final static String CONC_SAVE_DESC_RQRD         = "CONC-01705";
	public final static String CONC_SAVE_ORDE_RQRD         = "CONC_01706";
	public final static String CONC_SAVE_CATE_RNGE         = "CONC-01801";
	public final static String CONC_SAVE_INST_RNGE         = "CONC-01802";
	public final static String CONC_SAVE_TIPO_ERRO         = "CONC-01803";
	public final static String CONC_SAVE_DESL_MAXL         = "CONC-01804";
	public final static String CONC_SAVE_DESC_MAXL         = "CONC-01805";
	public final static String CONC_SAVE_ORDE_RNGE         = "CONC-01806";
	
	//Servicio de alta y modificacón de concepto
	public final static String CONC_FORM_CATE_RQRD         = "CONC-02001";
	public final static String CONC_FORM_TIPO_RQRD         = "CONC-02002";
	public final static String CONC_FORM_DESL_RQRD         = "CONC-02003";
	public final static String CONC_FORM_DESC_RQRD         = "CONC-02004";
	public final static String CONC_FORM_CATE_DIFF         = "CONC-02501";
	public final static String CONC_FORM_TIPO_DIFF         = "CONC-02502";
	public final static String CONC_FORM_CATE_NF           = "CONC-02901";
	public final static String CONC_FORM_DESL_DP           = "CONC-02902";
	public final static String CONC_FORM_DESC_DP           = "CONC-02903";
	public final static String CONC_FORM_CONC_NF           = "CONC-02904";
	
	//Servicio de consulta de concepto
	public final static String CONC_GETK_IDEN_RQRD         = "CONC-03001";
	
	//Servicio de consulta de conceptos por descripción
	public final static String CONC_GETD_CATE_RQRD         = "CONC-04001";
	public final static String CONC_GETD_DESL_RQRD         = "CONC-04002";
	public final static String CONC_GETD_DESC_RQRD         = "CONC-04003";
	
	//Servicio de lista de conceptos
	public final static String CONC_LIST_TIPO_RQRD         = "CONC-05001";
	public final static String CONC_LIST_CATE_RQRD         = "CONC-05002";
	public final static String CONC_LIST_TIPO_ERRO         = "CONC-05501";
	
	//Servicio de grabado de presupuesto
	public final static String PRES_SAVE_PRES_RQRD         = "PRES-01001";
	public final static String PRES_SAVE_NPER_IMPO         = "PRES-01501";
	public final static String PRES_SAVE_IMPO_DESC         = "PRES-01502";
	public final static String PRES_SAVE_DESV_DESC         = "PRES-01503";
	public final static String PRES_SAVE_BALA_DESC         = "PRES-01504";
	public final static String PRES_SAVE_INST_RQRD         = "PRES-01701";
	public final static String PRES_SAVE_ANUA_RQRD         = "PRES-01702";
	public final static String PRES_SAVE_CATE_RQRD         = "PRES-01703";
	public final static String PRES_SAVE_ESTA_RQRD         = "PRES-01704";
	public final static String PRES_SAVE_OBSE_NOTN         = "PRES-01705";
	public final static String PRES_SAVE_INST_RNGE         = "PRES-01801";
	public final static String PRES_SAVE_ANUA_RNGE         = "PRES-01802";
	public final static String PRES_SAVE_MESP_RNGE         = "PRES-01803";
	public final static String PRES_SAVE_CATE_RNGE         = "PRES-01804";
	public final static String PRES_SAVE_CONC_RNGE         = "PRES-01805";
	public final static String PRES_SAVE_IMPO_RNGE         = "PRES-01806";
	public final static String PRES_SAVE_IMPR_RNGE         = "PRES-01807";
	public final static String PRES_SAVE_IMNP_RNGE         = "PRES-01808";
	public final static String PRES_SAVE_IMTO_RNGE         = "PRES_01809";
	public final static String PRES_SAVE_DESV_RNGE         = "PRES-01810";
	public final static String PRES_SAVE_BALA_RNGE         = "PRES-01811";
	public final static String PRES_SAVE_ESTA_ERRO         = "PRES-01812";
	public final static String PRES_SAVE_OBSE_MAXL         = "PRES-01813";
	
	//Servicio de cálculo de partida
	public final static String PRES_CALC_FECH_RQRD         = "PRES-02001";
	public final static String PRES_CALC_CATE_RQRD         = "PRES-02002";
	public final static String PRES_CALC_CONC_RQRD         = "PRES-02003";
	
	//Servicio de cambio de estado de partida
	public final static String PRES_ESTA_ANUA_RQRD         = "PRES-03001";
	public final static String PRES_ESTA_CATE_RQRD         = "PRES-03002";
	public final static String PRES_ESTA_ESTA_RQRD         = "PRES-03003";
	public final static String PRES_ESTA_ESTA_ERRO         = "PRES-03501";
	public final static String PRES_ESTA_PRES_NF           = "PRES-03901";
	
	//Servicio de consulta de partida
	public final static String PRES_GETK_ANUA_RQRD         = "PRES-04001";
	public final static String PRES_GETK_CATE_RQRD         = "PRES-04002";

	//Servicio de lista de partida
	public final static String PRES_LIST_TIPO_RQRD         = "PRES-05001";
	public final static String PRES_LIST_ANUA_RQRD         = "PRES-05002";
	public final static String PRES_LIST_MESP_RQRD         = "PRES-05003";
	public final static String PRES_LIST_TIPO_ERRO         = "PRES-05501";
	
}
