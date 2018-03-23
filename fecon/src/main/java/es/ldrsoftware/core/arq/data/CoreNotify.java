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

	//*********************************************************************************************//
	// Notificaciones del módulo MNU                                                               //
	//*********************************************************************************************//
	
	//Servicio de consulta de categorías de menú
	public final static String CTMN_LIST_PERF_RQRD         = "CTMN-01001";
	public final static String CTMN_LIST_TIPO_RQRD         = "CTMN-01002";
	public final static String CTMN_LIST_ACTI_RQRD         = "CTMN-01003";
	public final static String CTMN_LIST_TIPO_ERRO         = "CTMN-01501";

	//Servicio de consulta de una categoría de menú
	public final static String CTMN_GETK_IDEN_RQRD         = "CTMN-02001";
	
	//Servicio de guardado de categoría de menú
	public final static String CTMN_SAVE_CTMN_RQRD         = "CTMN-03001";
	public final static String CTMN_SAVE_IDEN_RQRD         = "CTMN-03701";
	public final static String CTMN_SAVE_PERF_RQRD         = "CTMN-03702";
	public final static String CTMN_SAVE_DESC_RQRD         = "CTMN-03703";
	public final static String CTMN_SAVE_ACTI_RQRD         = "CTMN-03704";
	public final static String CTMN_SAVE_ORDE_RQRD         = "CTMN-03705";
	public final static String CTMN_SAVE_IDEN_RNGE         = "CTMN-03801";
	public final static String CTMN_SAVE_PERF_ERRO         = "CTMN-03802";
	public final static String CTMN_SAVE_DESC_MAXL         = "CTMN-03803";
	public final static String CTMN_SAVE_ACTI_ERRO         = "CTMN-03804";
	public final static String CTMN_SAVE_ORDE_RNGE         = "CTMN-03805";
	
	//Servicio de activación de categoría de menú
	public final static String CTMN_ACTI_IDEN_RQRD         = "CTMN-04001";
	public final static String CTMN_ACTI_CTMN_DESA_NO      = "CTMN-04501";
	public final static String CTMN_ACTI_CTMN_NF           = "CTMN-04901";
	
	//Servicio de desactivación de categoría de menú
	public final static String CTMN_DESA_IDEN_RQRD         = "CTMN-05001";
	public final static String CTMN_DESA_CTMN_ACTI_NO      = "CTMN-05501";
	public final static String CTMN_DESA_CTMN_NF           = "CTMN-05901";
	
	//Servicio de tratamiento de formulario de categoría de menú
	public final static String CTMN_FORM_IDEN_RQRD         = "CTMN-06001";
	public final static String CTMN_FORM_CTMN_NF           = "CTMN-06901";

	//Servicio de consulta de detalle de menú
	public final static String DTMN_LIST_CTMN_RQRD         = "DTMN-01001";
	public final static String DTMN_LIST_TIPO_RQRD         = "DTMN-01002";
	public final static String DTMN_LIST_ACTI_RQRD         = "DTMN-01003";
	public final static String DTMN_LIST_TIPO_ERRO         = "DTMN-01501";

	//Servicio de consulta de un detalle de menú
	public final static String DTMN_GETK_CTMN_RQRD         = "DTMN-02001";
	public final static String DTMN_GETK_IDEN_RQRD         = "DTMN-02002";

	//Servicio de guardado de detalle de menú
	public final static String DTMN_SAVE_DTMN_RQRD         = "DTMN-03001";
	public final static String DTMN_SAVE_CTMN_RQRD         = "DTMN-03701";
	public final static String DTMN_SAVE_IDEN_RQRD         = "DTMN-03702";
	public final static String DTMN_SAVE_DESC_RQRD         = "DTMN-03703";
	public final static String DTMN_SAVE_ACTI_RQRD         = "DTMN-03704";
	public final static String DTMN_SAVE_ORDE_RQRD         = "DTMN-03705";
	public final static String DTMN_SAVE_PATH_RQRD         = "DTMN-03706";
	public final static String DTMN_SAVE_ICON_RQRD         = "DTMN-03707";
	public final static String DTMN_SAVE_CTMN_RNGE         = "DTMN-03801";
	public final static String DTMN_SAVE_IDEN_RNGE         = "DTMN-03802";
	public final static String DTMN_SAVE_DESC_MAXL         = "DTMN-03803";
	public final static String DTMN_SAVE_ACTI_ERRO         = "DTMN-03804";
	public final static String DTMN_SAVE_ORDE_RNGE         = "DTMN-03805";
	public final static String DTMN_SAVE_PATH_MAXL         = "DTMN-04806";
	public final static String DTMN_SAVE_ICON_MAXL         = "DTMN-04807";

	//Servicio de activación de detalle de menú
	public final static String DTMN_ACTI_CTMN_RQRD         = "DTMN-04001";
	public final static String DTMN_ACTI_IDEN_RQRD         = "DTMN-04002";
	public final static String DTMN_ACTI_DTMN_DESA_NO      = "DTMN-04501";
	public final static String DTMN_ACTI_DTMN_NF           = "DTMN-04901";

	//Servicio de desactivación de detalle de menú
	public final static String DTMN_DESA_CTMN_RQRD         = "DTMN-05001";
	public final static String DTMN_DESA_IDEN_RQRD         = "DTMN-05002";
	public final static String DTMN_DESA_DTMN_ACTI_NO      = "DTMN-05501";
	public final static String DTMN_DESA_DTMN_NF           = "DTMN-05901";

	//Servicio de tratamiento de formulario de detalle de menú
	public final static String DTMN_FORM_CTMN_RQRD         = "DTMN-06001";
	public final static String DTMN_FORM_IDEN_RQRD         = "DTMN-06002";
	public final static String DTMN_FORM_DTMN_NF           = "CTMN-06901";

	//*********************************************************************************************//
	// Notificaciones del módulo OUI                                                               //
	//*********************************************************************************************//

	//Servicio de obtención de usuario por clave
	public final static String USUA_GETK_IDEN_RQRD         = "USUA-01001";
	
	//Servicio de obtención de usuario por email
	public final static String USUA_GETM_MAIL_RQRD         = "USUA-02001";
	
	//Servicio de login de usuario
	public final static String USUA_LGON_IDEN_RQRD         = "USUA-03001";
	public final static String USUA_LGON_PASS_RQRD         = "USUA-03002";
	public final static String USUA_LGON_INST_MLTI_NO      = "USUA-03501";
	public final static String USUA_LGON_INST_ACTI_NO      = "USUA-03502";
	public final static String USUA_LGON_RELA_INST_NF      = "USUA-03901";
	public final static String USUA_LGON_INST_NF           = "USUA-03902";
	
	//Servicio de grabado de usuario
	public final static String USUA_SAVE_USUA_RQRD         = "USUA-04001";
	public final static String USUA_SAVE_IDEN_RQRD         = "USUA-04701";
	public final static String USUA_SAVE_MAIL_RQRD         = "USUA-04702";
	public final static String USUA_SAVE_PASS_RQRD         = "USUA-04703";
	public final static String USUA_SAVE_ACTI_RQRD         = "USUA-04704";
	public final static String USUA_SAVE_FEAL_RQRD         = "USUA-04705";
	public final static String USUA_SAVE_FEUL_RQRD         = "USUA-04706";
	public final static String USUA_SAVE_IDEN_MAXL         = "USUA-04801";
	public final static String USUA_SAVE_MAIL_MAXL         = "USUA-04802";
	public final static String USUA_SAVE_PASS_MAXL         = "USUA-04803";
	public final static String USUA_SAVE_ACTI_ERRO         = "USUA-04804";
	public final static String USUA_SAVE_FEAL_RNGE         = "USUA-04805";
	public final static String USUA_SAVE_HOAL_RNGE         = "USUA-04806";
	public final static String USUA_SAVE_FEUL_RNGE         = "USUA-04807";
	public final static String USUA_SAVE_HOUL_RNGE         = "USUA-04808";
	
	//Servicio de registro de usuario
	public final static String USUA_REGI_IDEN_RQRD         = "USUA-05001";
	public final static String USUA_REGI_PASS_RQRD         = "USUA-05002";
	public final static String USUA_REGI_CPAS_RQRD         = "USUA-05003";
	public final static String USUA_REGI_MAIL_RQRD         = "USUA-05004";
	public final static String USUA_REGI_PERF_RQRD         = "USUA-05005";
	public final static String USUA_REGI_INST_DATA_RQRD    = "USUA-05006";
	public final static String USUA_REGI_NUMO_RQRD         = "USUA-05007";
	public final static String USUA_REGI_CERR              = "USUA-05501";
	public final static String USUA_REGI_INVI_NO           = "USUA-05502";
	public final static String USUA_REGI_PASS_CPAS_ERRO    = "USUA-05503";
	public final static String USUA_REGI_PERF_APM          = "USUA-05504";
	public final static String USUA_REGI_PERF_ERRO         = "USUA-05505";
	public final static String USUA_REGI_MLTI_NO           = "USUA-05506";
	public final static String USUA_REGI_NUMO_ERRO         = "USUA-05507";
	public final static String USUA_REGI_INST_NF           = "USUA-05901";
	public final static String USUA_REGI_IDEN_DP           = "USUA-05902";
	public final static String USUA_REGI_MAIL_DP           = "USUA-05903";
	
	//Servicio de validación de usuario
	public final static String USUA_VALI_IDEN_RQRD         = "USUA-06001";
	public final static String USUA_VALI_PASS_RQRD         = "USUA-06002";
	public final static String USUA_VALI_PASS_ERRO         = "USUA-06501";
	public final static String USUA_VALI_USUA_ACTI_NO      = "USUA-06502";
	public final static String USUA_VALI_MAIL_ERRO         = "USUA-06503";
	public final static String USUA_VALI_USUA_NF           = "USUA-06901";

	//Servicio de consulta de usuarios
	public final static String USUA_LIST_TIPO_RQRD         = "USUA-07001";
	public final static String USUA_LIST_INST_RQRD         = "USUA-07002";
	public final static String USUA_LIST_GRNT_NO           = "USUA-07501";
	public final static String USUA_LIST_TIPO_ERRO         = "USUA-07502";
	
	//Servicio de activación de usuarios
	public final static String USUA_ACTI_IDEN_RQRD         = "USUA-08001";
	public final static String USUA_ACTI_USUA_INAC_NO      = "USUA-08501";
	public final static String USUA_ACTI_USUA_NF           = "USUA-08901";
	
	//Servicio de desactivación de usuarios
	public final static String USUA_INAC_IDEN_RQRD         = "USUA-09001";
	public final static String USUA_INAC_USUA_ACTI_NO      = "USUA-09501";
	public final static String USUA_INAC_USUA_NF           = "USUA-09901";
	
	//Cierre de sesión de usuario
	public final static String USUA_EXIT_SESI_RQRD         = "USUA-10001";
	

	
	//Servicio de consulta de instalaciones
	public final static String INST_LIST_TIPO_RQRD         = "INST-01001";
	public final static String INST_LIST_ESTA_RQRD         = "INST-01002";
	public final static String INST_LIST_TIPO_ERRO         = "INST-01501";
	
	//Servicio de obtención de instalación por clave
	public final static String INST_GETK_IDEN_RQRD         = "INST-02001";
		
	//Servicio de guardado de instalación
	public final static String INST_SAVE_INST_RQRD         = "INST-03001";
	public final static String INST_SAVE_DESC_RQRD         = "INST-03701";
	public final static String INST_SAVE_FEAL_RQRD         = "INST-03702";
	public final static String INST_SAVE_ESTA_RQRD         = "INST-03703";
	public final static String INST_SAVE_FEUL_RQRD         = "INST-03704";
	public final static String INST_SAVE_TIPO_RQRD         = "INST-03705";
	public final static String INST_SAVE_FECA_RQRD         = "INST-03706";
	public final static String INST_SAVE_DESC_MAXL         = "INST-03801";
	public final static String INST_SAVE_FEAL_RNGE         = "INST_03802";
	public final static String INST_SAVE_ESTA_ERRO         = "INST-03803";
	public final static String INST_SAVE_FEUL_RNGE         = "INST-03804";
	public final static String INST_SAVE_TIPO_ERRO         = "INST-03805";
	public final static String INST_SAVE_FECA_RNGE         = "INST-03806";
	
	//Servicio de activación de instalación
	public final static String INST_ACTI_IDEN_RQRD         = "INST-04001";
	public final static String INST_ACTI_INST_INAC_NO      = "INST-04501";
	public final static String INST_ACTI_INST_NF           = "INST-04901";
	
	//Servicio de inactivación de instalación
	public final static String INST_INAC_IDEN_RQRD         = "INST-05001";
	public final static String INST_INAC_INST_ACTI_NO      = "INST-05501";
	public final static String INST_INAC_INST_NF           = "INST-05901";
	
	//Servicio de conversión de instalación en Premium
	public final static String INST_PREM_IDEN_RQRD         = "INST-06001";
	public final static String INST_PREM_INST_NORM_NO      = "INST-06501";
	public final static String INST_PREM_INST_NF           = "INST-06901";
	
	//Servicio de eliminación de Premium a instalación
	public final static String INST_NORM_IDEN_RQRD         = "INST-07001";
	public final static String INST_NORM_INST_PREM_NO      = "INST-07501";
	public final static String INST_NORM_INST_NF           = "INST-07901";

	//Servicio de registro de instalación
	public final static String INST_REGI_NUMO_RQRD         = "INST-08001";
	public final static String INST_REGI_MAIL_RQRD         = "INST-08002";
	public final static String INST_REGI_USUA_RQRD         = "INST-08003";
	public final static String INST_REGI_PASS_RQRD         = "INST-08004";
	public final static String INST_REGI_CPAS_RQRD         = "INST-08005";
	public final static String INST_REGI_DESC_RQRD         = "INST-08006";
	public final static String INST_REGI_NUMO_ERRO         = "INST-08501";
	public final static String INST_REGI_CERR              = "INST-08502";
	public final static String INST_REGI_INVI_NO           = "INST-08503";
	public final static String INST_REGI_MLTI_NO           = "INST-08504";
	public final static String INST_REGI_DESC_DP           = "INST-08901";
	
	//Servicio de obtención de instalación por descripción
	public final static String INST_GETD_DESC_RQRD         = "INST-09001";
	
	//Servicio de obtención de instalación por código
	public final static String INST_GETC_CODI_RQRD         = "INST-10001";
	
	//Servicio de generación aleatorio de código de instalación
	public final static String INST_CODI_GENE_TOUT         = "INST-11501";

	
	
	//Servicio de aceptación de invitaciones
	public final static String INVI_ACEP_IDEN_RQRD         = "INVI-01001";
	public final static String INVI_ACEP_ESTA_ERRO         = "INVI-01501";
	public final static String INVI_ACEP_INVI_NF           = "INVI-01901";
	
	//Servicio de envío de invitaciones
	public final static String INVI_ENVI_MAIL_RQRD         = "INVI-02001";
	public final static String INVI_ENVI_TIPO_RQRD         = "INVI-02002";
	public final static String INVI_ENVI_TIPO_ERRO         = "INVI-02501";
	public final static String INVI_ENVI_INVI_ENVI_EXIS    = "INVI-02502";
	public final static String INVI_ENVI_INVI_SOLI_EXIS    = "INVI-02503";
	public final static String INVI_ENVI_INVI_ACEP_EXIS    = "INVI-02504";
	
	//Servicio de consulta de invitación
	public final static String INVI_GETK_IDEN_RQRD         = "INVI-03001";
	
	//Servicio de consulta de invitaciones
	public final static String INVI_LIST_TIPO_RQRD         = "INVI-04001";
	
	//Servicio de procesado de invitación
	public final static String INVI_PROC_IDEN_RQRD         = "INVI-05001";
	public final static String INVI_PROC_INVI_NF           = "INVI-05901";

	//Servicio de rechazo de invitación
	public final static String INVI_RECH_IDEN_RQRD         = "INVI-06001";
	public final static String INVI_RECH_ESTA_ERRO         = "INVI-06501";
	public final static String INVI_RECH_INVI_NF           = "INVI-06901";

	//Servicio de grabado de invitación
	public final static String INVI_SAVE_INVI_RQRD         = "INVI-07001";
	public final static String INVI_SAVE_IDEN_RQRD         = "INVI-07701";
	public final static String INVI_SAVE_TIPO_RQRD         = "INVI-07702";
	public final static String INVI_SAVE_ESTA_RQRD         = "INVI-07703";
	public final static String INVI_SAVE_MAIL_RQRD         = "INVI-07704";
	public final static String INVI_SAVE_INST_RQRD         = "INVI-07705";
	public final static String INVI_SAVE_USUA_RQRD         = "INVI-07706";
	public final static String INVI_SAVE_INST_MPTY         = "INVI-07707";
	public final static String INVI_SAVE_USUA_MPTY         = "INVI-07708";
	public final static String INVI_SAVE_FEAL_RQRD         = "INVI-07709";
	public final static String INVI_SAVE_FEMO_MPTY         = "INVI-07710";
	public final static String INVI_SAVE_HOMO_MPTY         = "INVI-07711";
	public final static String INVI_SAVE_FEMO_RQRD         = "INVI-07712";
	public final static String INVI_SAVE_IDEN_MAXL         = "INVI-07801";
	public final static String INVI_SAVE_TIPO_ERRO         = "INVI-07802";
	public final static String INVI_SAVE_ESTA_ERRO         = "INVI-07803";
	public final static String INVI_SAVE_MAIL_MAXL         = "INVI-07804";
	public final static String INVI_SAVE_INST_RNGE         = "INVI-07805";
	public final static String INVI_SAVE_USUA_MAXL         = "INVI-07806";
	public final static String INVI_SAVE_FEAL_RNGE         = "INVI-07807";
	public final static String INVI_SAVE_HOAL_RNGE         = "INVI-07808";
	public final static String INVI_SAVE_FEMO_RNGE         = "INVI-07809";
	public final static String INVI_SAVE_HOMO_RNGE         = "INVI-07810";

	//Servicio de solicitud de invitación
	public final static String INVI_SOLI_MAIL_RQRD         = "INVI-08001";
	public final static String INVI_SOLI_INVI_PEND         = "INVI-08501";
	public final static String INVI_SOLI_OK                = "INVI-08I01";

	//Servicio de validación de invitación, para poder ser procesada
	public final static String INVI_VALI_IDEN_RQRD         = "INVI-09001";
	public final static String INVI_VALI_ESTA_ERRO         = "INVI-09501";
	public final static String INVI_VALI_NF                = "INVI-09901";
	
	

	//Notificaciones del m�dulo INVI
	
	public final static String INVI_PROC_INST_NF           = "INVI-00046";
	public final static String INVI_PROC_INST_ACTI_NO      = "INVI-00047";

	
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
	
	//Notificaciones del módulo RELA
	//OOTNN:
	//OO=Operación
	//T=Tipo
	//   0-Validación de entrada
	//   5-Validaciones funcionales
	//   7-Validaciones de campos obligatorios en BB.DD.
	//   8-Validaciones de dominio en BB.DD.
	//   9-Validaciones de BB.DD.
	//NN=Número
	public final static String RELA_LIST_RELA_RQRD         = "RELA-01001";
	public final static String RELA_LIST_CLCA_RQRD         = "RELA-01002";
	public final static String RELA_LIST_RELA_ERRO         = "RELA-01003";
	
	
	public final static String RELA_SAVE_RELA_RQRD         = "RELA-00004";
	public final static String RELA_SAVE_CLN1_RQRD         = "RELA-00005";
	public final static String RELA_SAVE_CLC2_RQRD         = "RELA-00006";
	public final static String RELA_SAVE_RELA_ERRO         = "RELA-00007";
	public final static String RELA_SAVE_PERF_RQRD         = "RELA-00008";
		
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
	
	//Notificaciones del módulo PARA
	public final static String PARA_GETP_TBLA_RQRD         = "PARA-01001";
	public final static String PARA_GETP_CLAV_RQRD         = "PARA-01002";
	public final static String PARA_GETP_PARA_NF           = "PARA-01901";
	
	public final static String PARA_NF                     = "PARA-00004";
	public final static String PARA_GETP_FRNT_MSTR_NF      = "PARA-00005";
	public final static String PARA_GETP_FRNT_CONS_NO      = "PARA-00006";
	public final static String PARA_GETP_FRNT_TBLA_RQRD    = "PARA-0000/";
	public final static String PARA_GETP_FRNT_CLAV_RQRD    = "PARA-00008";
	public final static String PARA_GETP_FRNT_NF           = "PARA-00009";
}
