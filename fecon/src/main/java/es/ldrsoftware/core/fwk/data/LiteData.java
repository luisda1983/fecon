package es.ldrsoftware.core.fwk.data;

public class LiteData {

	public final static String LITE_TBLA_MSTR          = "LITEMASTER";
	public final static String LITE_ELEM_MSTR_APPL     = "APPLITERAL";
	public final static String LITE_ELEM_MSTR_INVIESTA = "INVIESTA";
	public final static String LITE_ELEM_MSTR_INVITIPO = "INVITIPO";
	public final static String LITE_ELEM_MSTR_BOOL     = "BOOL";
	
	public final static String LT_EL_LITEMASTER_BTCHESTA = "BTCHESTA";
	public final static String LT_EL_LITEMASTER_CTRLESTA = "CTRLESTA";
	public final static String LT_EL_LITEMASTER_CTRLTIAC = "CTRLTIAC";
	public final static String LT_EL_LITEMASTER_EJECESTA = "EJECESTA";
	public final static String LT_EL_LITEMASTER_INSTESTA = "INSTESTA";
	public final static String LT_EL_LITEMASTER_INSTTIPO = "INSTTIPO";
	public final static String LT_EL_LITEMASTER_LOGPTIPO = "LOGPTIPO";
	public final static String LT_EL_LITEMASTER_MENUPERF = "MENUPERF";
	public final static String LT_EL_LITEMASTER_MPLAESTA = "MPLAESTA";
	public final static String LT_EL_LITEMASTER_PLANESTA = "PLANESTA";
	public final static String LT_EL_LITEMASTER_RELAESTA = "RELAESTA";
	public final static String LT_EL_LITEMASTER_RELAMAES = "RELAMAES";
	public final static String LT_EL_LITEMASTER_SESIDVCE = "SESIDVCE";
	public final static String LT_EL_LITEMASTER_SESIESTA = "SESIESTA";
	public final static String LT_EL_LITEMASTER_STSTREEJ = "STSTREEJ";
	public final static String LT_EL_LITEMASTER_USUAPERF = "USUAPERF";
	public final static String LT_EL_LITEMASTER_NOTFTIPO = "NOTFTIPO";
	public final static String LT_EL_LITEMASTER_DTO      = "DTO";

	public final static String LT_EL_LITEMASTER_REGTIPOUSU = "REGTIPOUSU";
	public final static String LT_EL_LITEMASTER_CONFREGIST = "CONFREGIST";
	
	
	public final static String LITE_TBLA_APPL      = "APPLITERAL";
	public final static String LITE_TBLA_APPL_NOMB = "APPNOMBRE";
	
	public final static String   LT_TB_INVIESTA            = "INVIESTA";
	public final static String   LT_EL_INVIESTA_SOLICITADA = "S";
	public final static String   LT_EL_INVIESTA_ENVIADA    = "E";
	public final static String   LT_EL_INVIESTA_ACEPTADA   = "A";
	public final static String   LT_EL_INVIESTA_RECHAZADA  = "R";
	public final static String   LT_EL_INVIESTA_CADUCADA   = "C";
	public final static String   LT_EL_INVIESTA_FINALIZADA = "F";
	public final static String[] LT_ST_INVIESTA            = {LT_EL_INVIESTA_SOLICITADA,
			                                                  LT_EL_INVIESTA_ENVIADA,
			                                                  LT_EL_INVIESTA_ACEPTADA,
			                                                  LT_EL_INVIESTA_RECHAZADA,
			                                                  LT_EL_INVIESTA_CADUCADA,
			                                                  LT_EL_INVIESTA_FINALIZADA};
	
	public final static String   LT_TB_INVITIPO             = "INVITIPO";
	public final static String   LT_EL_INVITIPO_INSTALACION = "I";
	public final static String   LT_EL_INVITIPO_USUARIO     = "U";
	public final static String[] LT_ST_INVITIPO             = {LT_EL_INVITIPO_INSTALACION,
			                                                   LT_EL_INVITIPO_USUARIO};
	
	public final static String   LT_TB_BOOL    = "BOOL";
	public final static String   LT_EL_BOOL_SI = "S";
	public final static String   LT_EL_BOOL_NO = "N";
	public final static String[] LT_ST_BOOL    = {LT_EL_BOOL_SI, 
			                                      LT_EL_BOOL_NO}; 

	public final static String   LT_TB_USUAPERF     = "USUAPERF";
	public final static String   LT_EL_USUAPERF_APM = "APM";
	public final static String   LT_EL_USUAPERF_ADM = "ADM";
	public final static String   LT_EL_USUAPERF_USR = "USR";
	public final static String[] LT_ST_USUAPERF     = {LT_EL_USUAPERF_APM, 
			                                           LT_EL_USUAPERF_ADM, 
			                                           LT_EL_USUAPERF_USR};

	public final static String   LT_TB_INSTESTA          = "INSTESTA";
	public final static String   LT_EL_INSTESTA_ACTIVA   = "A";
	public final static String   LT_EL_INSTESTA_INACTIVA = "I";
	public final static String[] LT_ST_INSTESTA          = {LT_EL_INSTESTA_ACTIVA,
			                                                LT_EL_INSTESTA_INACTIVA};
	
	public final static String   LT_TB_INSTTIPO             = "INSTTIPO";
	public final static String   LT_EL_INSTTIPO_NORMAL      = "N";
	public final static String   LT_EL_INSTTIPO_PREMIUM     = "P";
	public final static String[] LT_ST_INSTTIPO             = {LT_EL_INSTTIPO_NORMAL,
			                                                   LT_EL_INSTTIPO_PREMIUM};
	
	public final static String   LT_TB_RELAESTA             = "RELAESTA";
	public final static String   LT_EL_RELAESTA_ACTIVA      = "ACTIVA";
	public final static String   LT_EL_RELAESTA_INACTIVA    = "INACTIVA";
	public final static String[] LT_ST_RELAESTA             = {LT_EL_RELAESTA_ACTIVA,
			                                                   LT_EL_RELAESTA_INACTIVA};
	
	public final static String   LT_TB_RELAMAES             = "RELAMAES";
	public final static String   LT_EL_RELAMAES_INSTALACION = "INST";
	public final static String   LT_EL_RELAMAES_USUARIO     = "USUA";
	public final static String[] LT_ST_RELAMAES             = {LT_EL_RELAMAES_INSTALACION,
			                                                   LT_EL_RELAMAES_USUARIO};
	
	public final static String   LT_TB_MENUPERF             = "MENUPERF";
	public final static String   LT_EL_MENUPERF_OFFLINE     = "OFF";
	public final static String   LT_EL_MENUPERF_APP_MANAGER = "APM";
	public final static String   LT_EL_MENUPERF_ADMIN       = "ADM";
	public final static String   LT_EL_MENUPERF_USUARIO     = "USR";
	public final static String[] LT_ST_MENUPERF             = {LT_EL_MENUPERF_OFFLINE,
			                                                   LT_EL_MENUPERF_APP_MANAGER,
			                                                   LT_EL_MENUPERF_ADMIN,
			                                                   LT_EL_MENUPERF_USUARIO};
	
	
	public final static String   LT_TB_REGTIPOUSU        = "REGTIPOUSU";
	public final static String   LT_EL_REGTIPOUSU_NUEVO  = "N";
	public final static String   LT_EL_REGTIPOUSU_EXISTE = "E";
	public final static String[] LT_ST_REGTIPOUSU        = {LT_EL_REGTIPOUSU_NUEVO,
			                                                LT_EL_REGTIPOUSU_EXISTE};

	public final static String   LT_TB_CONFREGIST            = "CONFREGIST";
	public final static String   LT_EL_CONFREGIST_CERRADO    = "C";
	public final static String   LT_EL_CONFREGIST_INVITACION = "I";
	public final static String   LT_EL_CONFREGIST_LIBRE      = "L";
	public final static String[] LT_ST_CONFREGIST            = {LT_EL_CONFREGIST_CERRADO,
                                                                LT_EL_CONFREGIST_INVITACION,
                                                                LT_EL_CONFREGIST_LIBRE};
	
	public final static String   LT_TB_SESIESTA         = "SESIESTA";
	public final static String   LT_EL_SESIESTA_ABIERTA = "A";
	public final static String   LT_EL_SESIESTA_CERRADA = "C";
	public final static String[] LT_ST_SESIESTA         = {LT_EL_SESIESTA_ABIERTA,
														   LT_EL_SESIESTA_CERRADA};

	public final static String   LT_TB_MPLAESTA             = "MPLAESTA";
	public final static String   LT_EL_MPLAESTA_ACTIVADO    = "A";
	public final static String   LT_EL_MPLAESTA_DESACTIVADO = "D";
	public final static String[] LT_ST_MPLAESTA             = {LT_EL_MPLAESTA_ACTIVADO,
			                                                   LT_EL_MPLAESTA_DESACTIVADO};
	
	public final static String   LT_TB_PLANESTA             = "PLANESTA";
	public final static String   LT_EL_PLANESTA_PENDIENTE   = "P";
	public final static String   LT_EL_PLANESTA_EN_CURSO    = "X";
	public final static String   LT_EL_PLANESTA_ERROR       = "E";
	public final static String   LT_EL_PLANESTA_FINALIZADO  = "F";
	public final static String[] LT_ST_PLANESTA             = {LT_EL_PLANESTA_PENDIENTE,
			                                                   LT_EL_PLANESTA_EN_CURSO,
			                                                   LT_EL_PLANESTA_ERROR,
			                                                   LT_EL_PLANESTA_FINALIZADO};
	
	public final static String   LT_TB_BTCHESTA             = "BTCHESTA";
	public final static String   LT_EL_BTCHESTA_ACTIVO      = "A";
	public final static String   LT_EL_BTCHESTA_SUSPENDIDO  = "S";
	public final static String   LT_EL_BTCHESTA_CANCELADO   = "C";
	public final static String[] LT_ST_BTCHESTA             = {LT_EL_BTCHESTA_ACTIVO,
			                                                   LT_EL_BTCHESTA_SUSPENDIDO,
			                                                   LT_EL_BTCHESTA_CANCELADO};
	
	public final static String   LT_TB_EJECESTA             = "EJECESTA";
	public final static String   LT_EL_EJECESTA_PENDIENTE   = "P";
	public final static String   LT_EL_EJECESTA_VOID        = "V";
	public final static String   LT_EL_EJECESTA_FINALIZADA  = "F";
	public final static String[] LT_ST_EJECESTA             = {LT_EL_EJECESTA_PENDIENTE,
			                                                   LT_EL_EJECESTA_VOID,
			                                                   LT_EL_EJECESTA_FINALIZADA};

	public final static String   LT_TB_LOGPTIPO             = "LOGPTIPO";
	public final static String   LT_EL_LOGPTIPO_PASE        = "P";
	public final static String   LT_EL_LOGPTIPO_PROCESO     = "B";
	public final static String[] LT_ST_LOGPTIPO             = {LT_EL_LOGPTIPO_PASE,
			                                                   LT_EL_LOGPTIPO_PROCESO};
	
	public final static String   LT_TB_STSTREEJ             = "STSTREEJ";
	public final static String   LT_EL_STSTREEJ_OK          = " ";
	public final static String   LT_EL_STSTREEJ_VOID        = "V";
	public final static String   LT_EL_STSTREEJ_INFORMATIVO = "I";
	public final static String[] LT_ST_STSTREEJ             = {LT_EL_STSTREEJ_OK,
			                                                   LT_EL_STSTREEJ_VOID,
			                                                   LT_EL_STSTREEJ_INFORMATIVO};
	
	public final static String   LT_TB_SESIDVCE       = "SESIDVCE";
	public final static String   LT_EL_SESIDVCE_PC    = "PC";
	public final static String   LT_EL_SESIDVCE_MOVIL = "MB";
	public final static String[] LT_ST_SESIDVCE       = {LT_EL_SESIDVCE_PC,
			                                             LT_EL_SESIDVCE_MOVIL};
	
	public final static String   LT_TB_CTRLESTA             = "CTRLESTA";
	public final static String   LT_EL_CTRLESTA_ACTIVO      = "A";
	public final static String   LT_EL_CTRLESTA_DESACTIVADO = "D";
	public final static String[] LT_ST_CTRLESTA             = {LT_EL_CTRLESTA_ACTIVO,
			                                                   LT_EL_CTRLESTA_DESACTIVADO};
	
	public final static String   LT_TB_CTRLTIAC                = "CTRLTIAC";
	public final static String   LT_EL_CTRLTIAC_USUARIO        = "U";
	public final static String   LT_EL_CTRLTIAC_LOGOFF         = "O";
	public final static String   LT_EL_CTRLTIAC_LIBRE          = "L";
	public final static String   LT_EL_CTRLTIAC_USUARIO_PERFIL = "P";
	public final static String[] LT_ST_CTRLTIAC                = {LT_EL_CTRLTIAC_USUARIO,
			                                                      LT_EL_CTRLTIAC_LOGOFF,
			                                                      LT_EL_CTRLTIAC_LIBRE,
			                                                      LT_EL_CTRLTIAC_USUARIO_PERFIL};
	
	public final static String   LT_TB_NOTFTIPO              = "NOTFTIPO";
	public final static String   LT_EL_NOTFTIPO_VOID         = "V";
	public final static String   LT_EL_NOTFTIPO_AUTORIZACION = "O";
	public final static String   LT_EL_NOTFTIPO_INFORMATIVO  = "I";
	public final static String[] LT_ST_NOTFTIPO              = {LT_EL_NOTFTIPO_VOID,
			                                                    LT_EL_NOTFTIPO_AUTORIZACION,
			                                                    LT_EL_NOTFTIPO_INFORMATIVO};
	
	public final static String   LT_TB_DTO      = "DTO";
	public final static String   LT_EL_DTO_BTCH = "BTCH";
	public final static String   LT_EL_DTO_EJEC = "EJEC";
	public final static String   LT_EL_DTO_LOGP = "LOGP";
	public final static String   LT_EL_DTO_MPLA = "MPLA";
	public final static String   LT_EL_DTO_PLAN = "PLAN";
	public final static String   LT_EL_DTO_NOTF = "NOTF";
	public final static String   LT_EL_DTO_RELA = "RELA";
	public final static String   LT_EL_DTO_SESI = "SESI";
	public final static String   LT_EL_DTO_CTMN = "CTMN";
	public final static String   LT_EL_DTO_DTMN = "DTMN";
	public final static String   LT_EL_DTO_INST = "INST";
	public final static String   LT_EL_DTO_INVI = "INVI";
	public final static String   LT_EL_DTO_USUA = "USUA";
	public final static String   LT_EL_DTO_DELE = "DELE";
	public final static String   LT_EL_DTO_DOMI = "DOMI";
	public final static String   LT_EL_DTO_STDI = "STDI";
	public final static String   LT_EL_DTO_STME = "STME";
	public final static String   LT_EL_DTO_STST = "STST";
	public final static String   LT_EL_DTO_LITE = "LITE";
	public final static String   LT_EL_DTO_PARA = "PARA";
	
}