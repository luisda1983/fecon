package es.ldrsoftware.core.arq.data;

public class CoreNotify {
	
	//Notificaciones de arquitectura
	public final static String CORE_NOTF_IDEN_RQRD         = "CORE-00001";
	public final static String CORE_NOTF_IDEN_RQRD_DESC    = "Código de notificación obligatorio.";
	
	public final static String CORE_NOTF_NF                = "CORE-00002";
	public final static String CORE_NOTF_NF_DESC           = "Notificación inexistente.";

	public final static String CORE_CTRL_ESTA              = "CORE-00003";
	public final static String CORE_CTRL_ESTA_DESC         = "Llamada incorrecta. Controlador deshabilitado.";
	
	public final static String CORE_CTRL_TIAC_SESI         = "CORE-00004";
	public final static String CORE_CTRL_TIAC_SESI_DESC    = "Acceso no permitido. Usuario registrado requerido.";
	
	public final static String CORE_CTRL_TIAC_NO_SESI      = "CORE-00005";
	public final static String CORE_CTRL_TIAC_NO_SESI_DESC = "Acceso no permitido. Usuario registrado no permitido.";

	public final static String CORE_CTRL_TIAC_PERF         = "CORE-00006";
	public final static String CORE_CTRL_TIAC_PERF_DESC    = "Acceso no permitido. Acción restringida por perfil.";
	
	public final static String CORE_CTRL_TIAC_DOMI         = "CORE-00007";
	public final static String CORE_CTRL_TIAC_DOMI_DESC    = "Error de aplicación. Controlador mal configurado.";

	public final static String CORE_BTCH_NO_IMPL           = "CORE-00008";
	
	public final static String CORE_CTRL_CONT_NREG         = "CORE-00009";
	public final static String CORE_CTRL_CONT_NREG_DESC    = "Configuración incorrecta en Controlador.";

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


	//*********************************************************************************************//
	// Notificaciones del módulo FWK                                                               //
	//*********************************************************************************************//

	//Servicio de consulta de sesiones
	public final static String SESI_LIST_ESTA_RQRD         = "SESI-01001";
	public final static String SESI_LIST_ESTA_ERRO         = "SESI-01501";

	//Servicio de grabado de sesiones
	public final static String SESI_SAVE_SESI_RQRD         = "SESI-02001";
	public final static String SESI_SAVE_USUA_RQRD         = "SESI-02701";
	public final static String SESI_SAVE_DIIP_RQRD         = "SESI-02702";
	public final static String SESI_SAVE_PERF_RQRD         = "SESI-02703";
	public final static String SESI_SAVE_INST_RQRD         = "SESI-02704";
	public final static String SESI_SAVE_FEAP_RQRD         = "SESI-02705";
	public final static String SESI_SAVE_ESTA_RQRD         = "SESI-02706";
	public final static String SESI_SAVE_FEUL_RQRD         = "SESI-02707";
	public final static String SESI_SAVE_FECA_RQRD         = "SESI-02708";
	public final static String SESI_SAVE_DVCE_RQRD         = "SESI-02709";
	public final static String SESI_SAVE_USUA_MAXL         = "SESI-02801";
	public final static String SESI_SAVE_DIIP_MAXL         = "SESI-02802";
	public final static String SESI_SAVE_PERF_ERRO         = "SESI-02803";
	public final static String SESI_SAVE_INST_RNGE         = "SESI-02804";
	public final static String SESI_SAVE_FEAP_RNGE         = "SESI-02805";
	public final static String SESI_SAVE_HOAP_RNGE         = "SESI-02806";
	public final static String SESI_SAVE_ESTA_ERRO         = "SESI-02807";
	public final static String SESI_SAVE_FEUL_RNGE         = "SESI-02808";
	public final static String SESI_SAVE_HOUL_RNGE         = "SESI-02809";
	public final static String SESI_SAVE_FECA_RNGE         = "SESI-02810";
	public final static String SESI_SAVE_HOCA_RNGE         = "SESI-02811";
	public final static String SESI_SAVE_DVCE_ERRO         = "SESI-02812";

	//Servicio de apertura de Sesión
	public final static String SESI_OPEN_USUA_RQRD         = "SESI-03001";
	public final static String SESI_OPEN_PERF_RQRD         = "SESI-03002";
	
	//Servicio de consulta de Sesión, por Usuario
	public final static String SESI_GETU_USUA_RQRD         = "SESI-04001";
	
	//Servicio de consulta de Sesión, por clave (identificador)
	public final static String SESI_GETK_IDEN_RQRD         = "SESI-05001";
	
	//Servicio de validación de Sesión
	public final static String SESI_VALI_IDEN_RQRD         = "SESI-06001";
	public final static String SESI_VALI_CLAV_DIFE         = "SESI-06501";
	public final static String SESI_VALI_ESTA_NO_ABIE      = "SESI-06502";
	public final static String SESI_VALI_DIIP_DIFE         = "SESI-06503";
	public final static String SESI_VALI_SESI_CADU         = "SESI-06504";
	public final static String SESI_VALI_SESI_NF           = "SESI-06901";

	//Servicio de cierre de sesión
	public final static String SESI_EXIT_IDEN_RQRD         = "SESI-07001";
	public final static String SESI_EXIT_SESI_NF           = "SESI-07901";

	//Servicio de consulta de Controlador
	public final static String CTRL_GETK_IDEN_RQRD         = "CTRL-01001";
	public final static String CTRL_GETK_CTRL_NF           = "CTRL-01901";
	
	//Servicio de consulta de Controladores
	public final static String CTRL_LIST_TIPO_RQRD         = "CTRL-02001";
	public final static String CTRL_LIST_TIPO_ERRO         = "CTRL-02501";

	//Servicio de consulta de relaciones
	public final static String RELA_LIST_RELA_RQRD         = "RELA-01001";
	public final static String RELA_LIST_CLC2_RQRD         = "RELA-01002";
	public final static String RELA_LIST_RELA_ERRO         = "RELA-01003";

	//Servicio de grabado de Relación
	public final static String RELA_SAVE_RELA_RQRD         = "RELA-02001";
	public final static String RELA_SAVE_MAE1_RQRD         = "RELA-02701";
	public final static String RELA_SAVE_CLC1_NULL         = "RELA-02702";
	public final static String RELA_SAVE_MAE2_RQRD         = "RELA-02703";
	public final static String RELA_SAVE_CLC2_NULL         = "RELA-02704";
	public final static String RELA_SAVE_ESTA_RQRD         = "RELA-02705";
	public final static String RELA_SAVE_FEAL_RQRD         = "RELA-02706";
	public final static String RELA_SAVE_DATA_NULL         = "RELA-02707";
	public final static String RELA_SAVE_MAE1_ERRO         = "RELA-02801";
	public final static String RELA_SAVE_CLN1_RNGE         = "RELA-02802";
	public final static String RELA_SAVE_CLC1_MAXL         = "RELA-02803";
	public final static String RELA_SAVE_MAE2_ERRO         = "RELA-02804";
	public final static String RELA_SAVE_CLN2_RNGE         = "RELA-02805";
	public final static String RELA_SAVE_CLC2_MAXL         = "RELA-02806";
	public final static String RELA_SAVE_ESTA_ERRO         = "RELA-02807";
	public final static String RELA_SAVE_FEAL_RNGE         = "RELA-02808";
	public final static String RELA_SAVE_DATA_MAXL         = "RELA-02809";

	//Servicio de establecimiento de relación Instalación-Usuario
	public final static String RELA_INST_USUA_INST_RQRD    = "RELA-03001";
	public final static String RELA_INST_USUA_USUA_RQRD    = "RELA-03002";
	public final static String RELA_INST_USUA_PERF_RQRD    = "RELA-03003";

	//Servicio de consulta de tabla de literales
	public final static String LITE_LIST_TBLA_RQRD         = "LITE-01001";
	
	//Servicio de consulta de un literal
	public final static String LITE_GETK_TBLA_RQRD         = "LITE-02001";
	public final static String LITE_GETK_CLAV_RQRD         = "LITE-02002";
	public final static String LITE_GETK_LITE_NF           = "LITE-02901";

	//*********************************************************************************************//
	// Notificaciones del módulo BTC                                                               //
	//*********************************************************************************************//

	//Proceso gestor del Batch
	public final static String BTC_CTRL_PLAN_ERRO          = "BTC-00001";
	public final static String BTC_CTRL_BTCH_NF            = "BTC-00002";
	
	//Servicio de grabado de maestro de planificación
	public final static String MPLA_SAVE_MPLA_RQRD         = "MPLA-01001";
	public final static String MPLA_SAVE_ESTA_RQRD         = "MPLA-01701";
	public final static String MPLA_SAVE_HORA_RNGE         = "MPLA-01801";
	public final static String MPLA_SAVE_ESTA_ERRO         = "MPLA-01802";

	//Servicio de activación de maestro de planificación
	public final static String MPLA_ACTI_ESTA_ERRO         = "MPLA-02501";
	public final static String MPLA_ACTI_MPLA_NF           = "MPLA-02901";
	
	//Servicio de desactivación de maestro de planificación
	public final static String MPLA_DESA_ESTA_ERRO         = "MPLA-03501";
	public final static String MPLA_DESA_MPLA_NF           = "MPLA-03901";
	
	//Servicio de cálculo de ventana de ejecución
	public final static String MPLA_VENT_ACTI_ERRO         = "MPLA-04501";
	
	//Servicio de grabado de planificación
	public final static String PLAN_SAVE_PLAN_RQRD         = "PLAN-01001";
	public final static String PLAN_SAVE_FECH_RQRD         = "PLAN-01701";
	public final static String PLAN_SAVE_ESTA_RQRD         = "PLAN-01702";
	public final static String PLAN_SAVE_FEIN_NPER         = "PLAN-01703";
	public final static String PLAN_SAVE_HOIN_NPER         = "PLAN-01704";
	public final static String PLAN_SAVE_FEIN_RQRD         = "PLAN-01705";
	public final static String PLAN_SAVE_FEFI_RQRD         = "PLAN-01706";
	public final static String PLAN_SAVE_FEFI_NPER         = "PLAN-01707";
	public final static String PLAN_SAVE_HOFI_NPER         = "PLAN-01708";
	public final static String PLAN_SAVE_PROC_NPER         = "PLAN-01709";
	public final static String PLAN_SAVE_VENT_RQRD         = "PLAN-01710";
	public final static String PLAN_SAVE_PORC_RQRD         = "PLAN-01711";
	public final static String PLAN_SAVE_PORC_NPER         = "PLAN-01712";
	public final static String PLAN_SAVE_FBTC_RQRD         = "PLAN-01713";
	public final static String PLAN_SAVE_FECH_RNGE         = "PLAN-01801";
	public final static String PLAN_SAVE_HORA_RNGE         = "PLAN-01802";
	public final static String PLAN_SAVE_ESTA_ERRO         = "PLAN-01803";
	public final static String PLAN_SAVE_FEIN_RNGE         = "PLAN-01804";
	public final static String PLAN_SAVE_HOIN_RNGE         = "PLAN-01805";
	public final static String PLAN_SAVE_FEFI_RNGE         = "PLAN-01806";
	public final static String PLAN_SAVE_HOFI_RNGE         = "PLAN-01807";
	public final static String PLAN_SAVE_PROC_RNGE         = "PLAN-01808";
	public final static String PLAN_SAVE_VENT_RNGE         = "PLAN-01809";
	public final static String PLAN_SAVE_PORC_RNGE         = "PLAN-01810";
	public final static String PLAN_SAVE_FBTC_RNGE         = "PLAN-01811";
	
	//Servicio de creación de planificación
	public final static String PLAN_NUEV_FECH_RQRD         = "PLAN-02001";

	//Servicio de consulta de planificaciones
	public final static String PLAN_LIST_TIPO_RQRD         = "PLAN-03001";
	public final static String PLAN_LIST_FECH_RQRD         = "PLAN-03002";
	public final static String PLAN_LIST_TIPO_ERRO         = "PLAN-03003";
	
	//Servicio de grabado de Batch
	public final static String BTCH_SAVE_BTCH_RQRD         = "BTCH-01001";
	public final static String BTCH_SAVE_IDEN_RQRD         = "BTCH-01002";
	public final static String BTCH_SAVE_DESC_RQRD         = "BTCH-01003";
	public final static String BTCH_SAVE_FEAL_RQRD         = "BTCH-01004";
	public final static String BTCH_SAVE_ESTA_RQRD         = "BTCH-01005";
	public final static String BTCH_SAVE_AUTO_RQRD         = "BTCH-01006";
	public final static String BTCH_SAVE_DEPE_RQRD         = "BTCH-01007";
	public final static String BTCH_SAVE_TIPO_RQRD         = "BTCH-01008";
	public final static String BTCH_SAVE_TIPO_NPER         = "BTCH-01009";
	public final static String BTCH_SAVE_ORDE_RQRD         = "BTCH-01010";
	public final static String BTCH_SAVE_DBTC_RQRD         = "BTCH-01011";
	public final static String BTCH_SAVE_DBTC_NPER         = "BTCH-01012";
	public final static String BTCH_SAVE_IDEN_MAXL         = "BTCH-01501";
	public final static String BTCH_SAVE_DESC_MAXL         = "BTCH-01502";
	public final static String BTCH_SAVE_FEAL_RNGE         = "BTCH-01503";
	public final static String BTCH_SAVE_ESTA_ERRO         = "BTCH-01504";
	public final static String BTCH_SAVE_AUTO_ERRO         = "BTCH-01505";
	public final static String BTCH_SAVE_DEPE_ERRO         = "BTCH-01506";
	public final static String BTCH_SAVE_TIPO_ERRO         = "BTCH-01507";
	public final static String BTCH_SAVE_ORDE_RNGE         = "BTCH-01508";
	public final static String BTCH_SAVE_DBTC_MAXL         = "BTCH-01509";
	
	//Servicio de consulta de Batch por clave (identificador)
	public final static String BTCH_GETK_IDEN_RQRD         = "BTCH-02001";
	
	//Servicio de consultas de Batchs
	public final static String BTCH_LIST_TIPO_RQRD         = "BTCH-03001";
	public final static String BTCH_LIST_TIPO_ERRO         = "BTCH-03501";
	
	//Servicio de grabado de Ejecución
	public final static String EJEC_SAVE_EJEC_RQRD         = "EJEC-01001";
	public final static String EJEC_SAVE_FECH_RQRD         = "EJEC-01701";
	public final static String EJEC_SAVE_BTCH_RQRD         = "EJEC-01702";
	public final static String EJEC_SAVE_SECU_RQRD         = "EJEC-01703";
	public final static String EJEC_SAVE_ORDE_RQRD         = "EJEC-01704";
	public final static String EJEC_SAVE_ESTA_RQRD         = "EJEC-01705";
	public final static String EJEC_SAVE_FEIN_RQRD         = "EJEC-01706";
	public final static String EJEC_SAVE_FEIN_NPER         = "EJEC-01707";
	public final static String EJEC_SAVE_HOIN_NPER         = "EJEC-01708";
	public final static String EJEC_SAVE_FEFI_RQRD         = "EJEC-01709";
	public final static String EJEC_SAVE_FEFI_NPER         = "EJEC-01710";
	public final static String EJEC_SAVE_HOFI_NPER         = "EJEC-01711";
	public final static String EJEC_SAVE_TIEJ_RQRD         = "EJEC-01712";
	public final static String EJEC_SAVE_TIEJ_NPER         = "EJEC-01713";
	public final static String EJEC_SAVE_NOTF_RQRD         = "EJEC-01714";
	public final static String EJEC_SAVE_NOTF_NPER         = "EJEC-01715";
	public final static String EJEC_SAVE_FEPL_RQRD         = "EJEC-01716";
	public final static String EJEC_SAVE_FEPL_NPER         = "EJEC-01717";
	public final static String EJEC_SAVE_HOPL_NPER         = "EJEC-01718";
	public final static String EJEC_SAVE_FBTC_RQRD         = "EJEC-01719";
	public final static String EJEC_SAVE_FBTC_NPER         = "EJEC-01720";
	public final static String EJEC_SAVE_FECH_RNGE         = "EJEC-01801";
	public final static String EJEC_SAVE_HORA_RNGE         = "EJEC-01802";
	public final static String EJEC_SAVE_BTCH_MAXL         = "EJEC-01803";
	public final static String EJEC_SAVE_SECU_RNGE         = "EJEC-01804";
	public final static String EJEC_SAVE_ORDE_RNGE         = "EJEC-01805";
	public final static String EJEC_SAVE_ESTA_ERRO         = "EJEC-01806";
	public final static String EJEC_SAVE_FEIN_RNGE         = "EJEC-01807";
	public final static String EJEC_SAVE_HOIN_RNGE         = "EJEC-01808";
	public final static String EJEC_SAVE_FEFI_RNGE         = "EJEC-01809";
	public final static String EJEC_SAVE_HOFI_RNGE         = "EJEC-01810";
	public final static String EJEC_SAVE_TIEJ_RNGE         = "EJEC-01811";
	public final static String EJEC_SAVE_NOTF_MAXL         = "EJEC-01812";
	public final static String EJEC_SAVE_FEPL_RNGE         = "EJEC-01813";
	public final static String EJEC_SAVE_HOPL_RNGE         = "EJEC-01814";
	public final static String EJEC_SAVE_FBTC_RNGE         = "EJEC-01815";
	
	//Servicio de consulta de ejecuciones
	public final static String EJEC_LIST_TIPO_RQRD         = "EJEC-02001";
	public final static String EJEC_LIST_FECH_RQRD         = "EJEC-02002";
	public final static String EJEC_LIST_ESTA_RQRD         = "EJEC-02003";
	public final static String EJEC_LIST_TIPO_ERRO         = "EJEC-02004";
	
	//Servicio de planificación de ejecuciones
	public final static String EJEC_PLAN_BTCH_RQRD         = "EJEC-03001";
	public final static String EJEC_PLAN_FECH_RQRD         = "EJEC-03002";
	public final static String EJEC_PLAN_SECU_RQRD         = "EJEC-03003";
	public final static String EJEC_PLAN_MPLA_ACTI_NO      = "EJEC-03501";
	public final static String EJEC_PLAN_BTCH_ACTI_NO      = "EJEC-03502";
	public final static String EJEC_PLAN_MPLA_NF           = "EJEC-03901";
	public final static String EJEC_PLAN_BTCH_NF           = "EJEC-03902";

	//Servicio de grabado de log de procesos
	public final static String LOGP_SAVE_LOGP_RQRD         = "LOGP-01001";
	public final static String LOGP_SAVE_TIPO_RQRD         = "LOGP-01701";
	public final static String LOGP_SAVE_IDEN_RQRD         = "LOGP-01702";
	public final static String LOGP_SAVE_FECH_RQRD         = "LOGP-01703";
	public final static String LOGP_SAVE_DATO_RQRD         = "LOGP-01704";
	public final static String LOGP_SAVE_TIPO_ERRO         = "LOGP-01801";
	public final static String LOGP_SAVE_IDEN_MAXL         = "LOGP-01802";
	public final static String LOGP_SAVE_FECH_RNGE         = "LOGP-01803";
	public final static String LOGP_SAVE_HORA_RNGE         = "LOGP-01804";
	public final static String LOGP_SAVE_DATO_MAXL         = "LOGP-01805";
	public final static String LOGP_SAVE_TABS_RNGE         = "LOGP-01806";

	//*********************************************************************************************//
	// Notificaciones del módulo STS                                                               //
	//*********************************************************************************************//

	//Servicio de grabado de estadística
	public final static String STST_SAVE_STST_RQRD         = "STST-01001";
	public final static String STST_SAVE_CTRL_RQRD         = "STST-01701";
	public final static String STST_SAVE_USUA_RQRD         = "STST-01702";
	public final static String STST_SAVE_FEEJ_RQRD         = "STST-01703";
	public final static String STST_SAVE_TIEJ_RQRD         = "STST-01704";
	public final static String STST_SAVE_NOTF_RQRD         = "STST-01705";
	public final static String STST_SAVE_CTRL_MAXL         = "STST-01801";
	public final static String STST_SAVE_INST_RNGE         = "STST-01802";
	public final static String STST_SAVE_USUA_MAXL         = "STST-01803";
	public final static String STST_SAVE_FEEJ_RNGE         = "STST-01804";
	public final static String STST_SAVE_HOEJ_RNGE         = "STST-01805";
	public final static String STST_SAVE_TIEJ_RNGE         = "STST-01806";
	public final static String STST_SAVE_REEJ_ERRO         = "STST-01807";
	public final static String STST_SAVE_NOTF_MAXL         = "STST-01808";
	
	//Servicio de registro de estadística
	public final static String STST_REGI_CTRL_RQRD         = "STST-02001";
	public final static String STST_REGI_INEJ_RQRD         = "STST-02002";
	public final static String STST_REGI_FIEJ_RQRD         = "STST-02003";
	
	//Servicio de consulta de estadísticas
	public final static String STST_LIST_TIPO_RQRD         = "STST-03001";
	public final static String STST_LIST_FECH_RQRD         = "STST-03002";
	public final static String STST_LIST_TIPO_ERRO         = "STST-03003";

	//Servicio de grabado de estadística diaria
	public final static String STDI_SAVE_STDI_RQRD         = "STDI-01001";
	public final static String STDI_SAVE_FECH_RQRD         = "STDI-01701";
	public final static String STDI_SAVE_CTRL_RQRD         = "STDI-01702";
	public final static String STDI_SAVE_TOTA_RQRD         = "STDI-01703";
	public final static String STDI_SAVE_TIME_RQRD         = "STDI-01704";
	public final static String STDI_SAVE_TIMA_RQRD         = "STDI-01705";
	public final static String STDI_SAVE_TIMI_RQRD         = "STDI-01706";
	public final static String STDI_SAVE_FECH_RNGE         = "STDI-01801";
	public final static String STDI_SAVE_CTRL_MAXL         = "STDI-01802";
	public final static String STDI_SAVE_TOTA_RNGE         = "STDI-01803";
	public final static String STDI_SAVE_TIME_RNGE         = "STDI-01804";
	public final static String STDI_SAVE_TIMA_RNGE         = "STDI-01805";
	public final static String STDI_SAVE_TIMI_RNGE         = "STDI-01806";
	public final static String STDI_SAVE_NUER_RNGE         = "STDI-01807";
	
	//Servicio de consulta de estadísticas diarias
	public final static String STDI_LIST_TIPO_RQRD         = "STDI-02001";
	public final static String STDI_LIST_FECH_RQRD         = "STDI-02002";
	public final static String STDI_LIST_FEIN_RQRD         = "STDI-02003";
	public final static String STDI_LIST_FEFI_RQRD         = "STDI-02004";
	public final static String STDI_LIST_TIPO_ERRO         = "STDI-02501";

	//Servicio de grabado de estadísticas mensuales
	public final static String STME_SAVE_STME_RQRD         = "STME-01001";
	public final static String STME_SAVE_ANYO_RQRD         = "STDI-01701";
	public final static String STME_SAVE_MESS_RQRD         = "STDI-01702";
	public final static String STME_SAVE_CTRL_RQRD         = "STDI-01703";
	public final static String STME_SAVE_TOTA_RQRD         = "STDI-01704";
	public final static String STME_SAVE_TIME_RQRD         = "STDI-01705";
	public final static String STME_SAVE_TIMA_RQRD         = "STDI-01706";
	public final static String STME_SAVE_TIMI_RQRD         = "STDI-01707";
	public final static String STME_SAVE_ANYO_RNGE         = "STDI-01801";
	public final static String STME_SAVE_MESS_RNGE         = "STDI-01802";
	public final static String STME_SAVE_CTRL_MAXL         = "STDI-01803";
	public final static String STME_SAVE_TOTA_RNGE         = "STDI-01804";
	public final static String STME_SAVE_TIME_RNGE         = "STDI-01805";
	public final static String STME_SAVE_TIMA_RNGE         = "STDI-01806";
	public final static String STME_SAVE_TIMI_RNGE         = "STDI-01807";
	public final static String STME_SAVE_NUER_RNGE         = "STDI-01808";
	
	//Servicio de consulta de estadísticas mensuales
	public final static String STME_LIST_TIPO_RQRD         = "STME-02001";
	public final static String STME_LIST_ANYO_RQRD         = "STME-02002";
	public final static String STME_LIST_MESS_RQRD         = "STME-02003";
	public final static String STME_LIST_TIPO_ERRO         = "STME-02501";
		
	//Notificaciones del módulo RELA
	//OOTNN:
	//OO=Operación
	//T=Tipo
	//   0-Validación de entrada
	//   3-Informativos
	//   5-Validaciones funcionales
	//   7-Validaciones de campos obligatorios en BB.DD.
	//   8-Validaciones de dominio en BB.DD.
	//   9-Validaciones de BB.DD.
	//NN=Número
		
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
