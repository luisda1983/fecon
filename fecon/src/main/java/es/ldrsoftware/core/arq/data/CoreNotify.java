package es.ldrsoftware.core.arq.data;

public class CoreNotify {
	
	//Notificaciones de arquitectura
	public final static String CORE_NOTF_IDEN_RQRD         = "CORE-00001";
	public final static String CORE_NOTF_IDEN_RQRD_DESC    = "C�digo de notificaci�n obligatorio.";
	
	public final static String CORE_NOTF_NF                = "CORE-00002";
	public final static String CORE_NOTF_NF_DESC           = "Notificaci�n inexistente.";
	
	public final static String CORE_CTRL_ESTA              = "CORE-00003";
	public final static String CORE_CTRL_ESTA_DESC         = "Llamada incorrecta. Controlador deshabilitado.";
	
	public final static String CORE_CTRL_TIAC_SESI         = "CORE-00004";
	public final static String CORE_CTRL_TIAC_SESI_DESC    = "Acceso no permitido. Usuario registrado requerido.";
	
	public final static String CORE_CTRL_TIAC_NO_SESI      = "CORE-00005";
	public final static String CORE_CTRL_TIAC_NO_SESI_DESC = "Acceso no permitido. Usuario registrado no permitido.";

	public final static String CORE_CTRL_TIAC_PERF         = "CORE-00006";
	public final static String CORE_CTRL_TIAC_PERF_DESC    = "Acceso no permitido. Acci�n restringida por perfil.";
	
	public final static String CORE_CTRL_TIAC_DOMI         = "CORE-00007";
	public final static String CORE_CTRL_TIAC_DOMI_DESC    = "Error de aplicaci�n. Controlador mal configurado.";

	public final static String CORE_BTCH_NO_IMPL           = "CORE-00008";
	
	//Notificaciones de m�dulo MENU
	public final static String MENU_PERF_RQRD              = "MENU-00001";
	
	//Notificaciones de m�dulo CTRL
	public final static String CTRL_IDEN_RQRD              = "CTRL-00001";
	public final static String CTRL_NF                     = "CTRL-00002";
	
	//Notificaciones de m�dulo SESI
	public final static String SESI_IDEN_RQRD              = "SESI-00001";
	public final static String SESI_CLAV_RQRD              = "SESI-00002";
	public final static String SESI_IDEN_NF                = "SESI-00003";
	public final static String SESI_CLAV_DIFE              = "SESI-00004";
	public final static String SESI_ESTA_NO_ABIE           = "SESI-00005";
	public final static String SESI_DIIP_DIFE              = "SESI-00006";
	public final static String SESI_CADU                   = "SESI-00007";
	public final static String SESI_OPEN_USUA_RQRD         = "SESI-00008";
	public final static String SESI_OPEN_PERF_RQRD         = "SESI-00009";
	
	//Notificaciones del m�dulo USUA
	public final static String USUA_EXIT_SESI_RQRD         = "USUA-00001";
	public final static String USUA_GETU_IDEN_RQRD         = "USUA-00002"; //DESUSO
	public final static String USUA_LGON_IDEN_RQRD         = "USUA-00003";
	public final static String USUA_LGON_PASS_RQRD         = "USUA-00004";
	public final static String USUA_LGON_USUA_NF           = "USUA-00005";
	public final static String USUA_LGON_PASS_ERRO         = "USUA-00006";
	public final static String USUA_LGON_ACTI_NO           = "USUA-00007";
	public final static String USUA_LGON_INST_NO           = "USUA-00008";
	public final static String USUA_LGON_INST_MLTI_NO      = "USUA-00009";
	public final static String USUA_LGON_INST_NF           = "USUA-00010";
	public final static String USUA_LGON_INST_ACTI_NO      = "USUA-00011";
	public final static String USUA_SAVE_USUA_RQRD         = "USUA-00012";
	public final static String USUA_SAVE_IDEN_RQRD         = "USUA-00013";
	public final static String USUA_SAVE_IDEN_MAXL         = "USUA-00014";
	public final static String USUA_SAVE_PERF_RQRD         = "USUA-00015";
	public final static String USUA_SAVE_PERF_ERRO         = "USUA-00016";
	public final static String USUA_SAVE_MAIL_RQRD         = "USUA-00017";
	public final static String USUA_SAVE_MAIL_MAXL         = "USUA-00018";
	public final static String USUA_SAVE_PASS_RQRD         = "USUA-00019";
	public final static String USUA_SAVE_PASS_MAXL         = "USUA-00020";
	public final static String USUA_SAVE_ACTI_RQRD         = "USUA-00021";
	public final static String USUA_SAVE_ACTI_ERRO         = "USUA-00022";
	public final static String USUA_SAVE_FEAL_RQRD         = "USUA-00023";
	public final static String USUA_SAVE_FEAL_RNGE         = "USUA-00024";
	public final static String USUA_SAVE_HOAL_RNGE         = "USUA-00025";
	public final static String USUA_SAVE_FEUL_RQRD         = "USUA-00026";
	public final static String USUA_SAVE_FEUL_RNGE         = "USUA-00027";
	public final static String USUA_SAVE_HOUL_RNGE         = "USUA-00028";
	public final static String USUA_REGI_IDEN_RQRD         = "USUA_00029";
	public final static String USUA_REGI_PASS_RQRD         = "USUA-00030";
	public final static String USUA_REGI_CPAS_RQRD         = "USUA-00031";
	public final static String USUA_REGI_MAIL_RQRD         = "USUA-00032";
	public final static String USUA_REGI_PERF_RQRD         = "USUA-00033";
	public final static String USUA_REGI_IDEN_DP           = "USUA-00034";
	public final static String USUA_REGI_MAIL_DP           = "USUA-00035";
	public final static String USUA_REGI_PASS_CPAS_ERRO    = "USUA-00036";
	public final static String USUA_REGI_PERF_APM          = "USUA-00037";
	public final static String USUA_REGI_PERF_ERRO         = "USUA-00038";
	public final static String USUA_GETU_ERRO              = "USUA-00039";
	
	//Notificaciones del m�dulo INST
	public final static String INST_GETI_IDEN_RQRD         = "INST-00001";
	public final static String INST_SAVE_INST_RQRD         = "INST-00002";
	public final static String INST_SAVE_DESC_RQRD         = "INST-00003";
	public final static String INST_SAVE_DESC_MAXL         = "INST-00004";
	public final static String INST_SAVE_FEAL_RQRD         = "INST-00005";
	public final static String INST_SAVE_FEAL_RNGE         = "INST_00006";
	public final static String INST_SAVE_ESTA_RQRD         = "INST-00007";
	public final static String INST_SAVE_ESTA_ERRO         = "INST-00008";
	public final static String INST_SAVE_FEUL_RQRD         = "INST-00009";
	public final static String INST_SAVE_FEUL_RNGE         = "INST-00010";
	public final static String INST_SAVE_TIPO_RQRD         = "INST-00011";
	public final static String INST_SAVE_TIPO_ERRO         = "INST-00012";
	public final static String INST_SAVE_FECA_RQRD         = "INST-00013";
	public final static String INST_SAVE_FECA_RNGE         = "INST-00014";
	public final static String INST_REGI_MAIL_RQRD         = "INST-00015";
	public final static String INST_REGI_USUA_RQRD         = "INST-00016";
	public final static String INST_REGI_PASS_RQRD         = "INST-00017";
	public final static String INST_REGI_CPAS_RQRD         = "INST-00018";
	public final static String INST_REGI_CERR              = "INST-00019";
	public final static String INST_REGI_INVI_RQRD         = "INST-00020";
	
	//Notificaciones del m�dulo RELA
	public final static String RELA_LIST_RELA_ERRO         = "RELA-00001";
	public final static String RELA_LIST_RELA_RQRD         = "RELA-00002";
	public final static String RELA_LIST_CLCA_RQRD         = "RELA-00003";
	public final static String RELA_SAVE_RELA_RQRD         = "RELA-00004";
	public final static String RELA_SAVE_CLN1_RQRD         = "RELA-00005";
	public final static String RELA_SAVE_CLC2_RQRD         = "RELA-00006";
	public final static String RELA_SAVE_RELA_ERRO         = "RELA-00007";
	
	//Notificaciones del m�dulo INVI
	public final static String INVI_SAVE_INVI_RQRD         = "INVI-00001";
	public final static String INVI_SAVE_IDEN_RQRD         = "INVI-00002";
	public final static String INVI_SAVE_IDEN_MAXL         = "INVI-00003";
	public final static String INVI_SAVE_TIPO_RQRD         = "INVI-00004";
	public final static String INVI_SAVE_TIPO_ERRO         = "INVI-00005";
	public final static String INVI_SAVE_ESTA_RQRD         = "INVI-00006";
	public final static String INVI_SAVE_ESTA_ERRO         = "INVI-00007";
	public final static String INVI_SAVE_MAIL_RQRD         = "INVI-00008";
	public final static String INVI_SAVE_MAIL_MAXL         = "INVI-00009";
	public final static String INVI_SAVE_FEAL_RQRD         = "INVI-00010";
	public final static String INVI_SAVE_FEAL_RNGE         = "INVI-00011";
	public final static String INVI_SAVE_HOAL_RNGE         = "INVI-00012";
	public final static String INVI_SAVE_INST_MPTY         = "INVI-00013";
	public final static String INVI_SAVE_USUA_MPTY         = "INVI-00014";
	public final static String INVI_SAVE_FEMO_MPTY         = "INVI-00015";
	public final static String INVI_SAVE_HOMO_MPTY         = "INVI-00016";
	public final static String INVI_SOLI_MAIL_RQRD         = "INVI-00017";
	public final static String INVI_LIST_TIPO_RQRD         = "INVI-00018";
	public final static String INVI_LIST_SOLI_PEND         = "INVI-00019";
	public final static String INVI_SOLI_OK                = "INVI-00020";
	public final static String INVI_GETI_IDEN_RQRD         = "INVI-00021";
	public final static String INVI_ACEP_IDEN_RQRD         = "INVI-00022";
	public final static String INVI_ACEP_NF                = "INVI-00023";
	public final static String INVI_ACEP_ESTA_ERRO         = "INVI-00024";
	public final static String INVI_RECH_IDEN_RQRD         = "INVI-00025";
	public final static String INVI_RECH_NF                = "INVI-00026";
	public final static String INVI_RECH_ESTA_ERRO         = "INVI-00027";
	public final static String INVI_SAVE_FEMO_RQRD         = "INVI-00028";
	public final static String INVI_SAVE_FEMO_RNGE         = "INVI-00029";
	public final static String INVI_SAVE_HOMO_RNGE         = "INVI-00030";
	public final static String INVI_VALI_NF                = "INVI-00031";
	public final static String INVI_VALI_ESTA_ERRO         = "INVI-00032";
	public final static String INVI_VALI_IDEN_RQRD         = "INVI-00033";
	public final static String INVI_PROC_IDEN_RQRD         = "INVI-00034";
	public final static String INVI_PROC_INVI_NF           = "INVI-00035";
	public final static String INVI_SAVE_INST_RQRD         = "INVI-00036";
	public final static String INVI_SAVE_INST_RNGE         = "INVI-00037";
	public final static String INVI_SAVE_USUA_RQRD         = "INVI-00038";
	public final static String INVI_SAVE_USUA_MAXL         = "INVI-00039";
	
	//Notificaciones del m�dulo STST
	public final static String STST_SAVE_CTRL_RQRD         = "STST-00001";
	public final static String STST_SAVE_INEJ_RQRD         = "STST-00002";
	public final static String STST_SAVE_FIEJ_RQRD         = "STST-00003";
	public final static String STST_SAVE_INEJ_GT_FIEJ      = "STST-00004";
	public final static String STST_SAVE_NOTF_RQRD         = "STST-00005";
	
	//Notificaciones del m�dulo STDI
	public final static String STDI_SAVE_FECH_RQRD         = "STDI-00001";
	public final static String STDI_SAVE_CTRL_RQRD         = "STDI-00002";

	//Notificaciones del modulo EJEC
	public final static String EJEC_SAVE_RQRD              = "EJEC-00001";
	public final static String EJEC_SAVE_BTCH_RQRD         = "EJEC-00002";
	public final static String EJEC_SAVE_FECH_RQRD         = "EJEC-00003";
	public final static String EJEC_SAVE_ORDE_RQRD         = "EJEC-00004";
	public final static String EJEC_SAVE_NOTP_FEIN_RQRD    = "EJEC-00005";
	public final static String EJEC_SAVE_NOTP_FEFI_RQRD    = "EJEC-00006";
	public final static String EJEC_SAVE_NOTP_TIEJ_RQRD    = "EJEC-00007";
	public final static String EJEC_SAVE_NOTP_HOIN_RQRD    = "EJEC-00008";
	public final static String EJEC_SAVE_NOTP_HOFI_RQRD    = "EJEC-00009";
	public final static String EJEC_SAVE_VOID_NOTF_RQRD    = "EJEC-00010";
	public final static String EJEC_SAVE_ESTA_ERRO         = "EJEC-00011";
	public final static String EJEC_PLAN_BTCH_RQRD         = "EJEC-00012";
	public final static String EJEC_PLAN_FECH_RQRD         = "EJEC-00013";
	public final static String EJEC_PLAN_ORDE_RQRD         = "EJEC-00014";
	public final static String EJEC_GETL_FECH_RQRD         = "EJEC-00015";
	
	//Notificaciones del modulo BTCH
	public final static String BTCH_SAVE_RQRD              = "BTCH-00001";
	public final static String BTCH_SAVE_IDEN_RQRD         = "BTCH-00002";
	public final static String BTCH_SAVE_DESC_RQRD         = "BTCH-00003";
	public final static String BTCH_SAVE_FEAL_RQRD         = "BTCH-00004";
	public final static String BTCH_SAVE_ORDE_RQRD         = "BTCH-00005";
	public final static String BTCH_SAVE_TIPO_RQRD         = "BTCH-00006";
	public final static String BTCH_SAVE_TIPO_ERRO         = "BTCH-00007";
	public final static String BTCH_SAVE_PLAN_RQRD         = "BTCH-00008";
	public final static String BTCH_SAVE_PLAN_ERRO         = "BTCH-00009";
	public final static String BTCH_SAVE_PLAN_FEIN_RQRD    = "BTCH-00010";
	public final static String BTCH_SAVE_PERI_FEPR_RQRD    = "BTCH-00011";
	public final static String BTCH_GETB_IDEN_RQRD         = "BTCH-00012";
	public final static String BTCH_NF                     = "BTCH-00013";
	public final static String BTCH_TIPO_ERRO              = "BTCH-00014";
	
	//Notificaciones del modulo LITE
	public final static String LITE_GETL_TBLA_RQRD         = "LITE-00001";
	public final static String LITE_GETL_CLAV_RQRD         = "LITE-00002";
	public final static String LITE_NF                     = "LITE-00003";
	
	//Notificaciones del m�dulo PARA
	public final static String PARA_GETP_TBLA_RQRD         = "PARA-00001";
	public final static String PARA_GETP_CLAV_RQRD         = "PARA-00002";
	public final static String PARA_GETP_TBLA_ERRO         = "PARA_00003";
	public final static String PARA_NF                     = "PARA-00004";
	public final static String PARA_GETP_FRNT_MSTR_NF      = "PARA-00005";
	public final static String PARA_GETP_FRNT_CONS_NO      = "PARA-00006";
	public final static String PARA_GETP_FRNT_TBLA_RQRD    = "PARA-0000/";
	public final static String PARA_GETP_FRNT_CLAV_RQRD    = "PARA-00008";
	public final static String PARA_GETP_FRNT_NF           = "PARA-00009";
}
