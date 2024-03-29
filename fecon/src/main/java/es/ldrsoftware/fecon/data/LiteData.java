package es.ldrsoftware.fecon.data;

public class LiteData {

	public final static String LITE_ELEM_MSTR_PRESESTA = "PRESESTA";
	public final static String LITE_ELEM_MSTR_HCONLTTIPO = "HCONLTTIPO";
	public final static String LT_EL_MSTR_HCONTIPO = "HCONTIPO";
	public final static String LT_EL_MSTR_CUENTIPO = "CUENTIPO";
	public final static String LT_EL_MSTR_CONCTIPO = "CONCTIPO";
	public final static String LT_EL_MSTR_COESTIPO = "COESTIPO";
	public final static String LT_EL_MSTR_TRADTIPO = "TRADTIPO";
	
	public final static String   LT_TB_PRESESTA           = "PRESESTA";
	public final static String   LT_EL_PRESESTA_ABIERTA   = "A";
	public final static String   LT_EL_PRESESTA_CERRADA   = "C";
	public final static String   LT_EL_PRESESTA_NO_CREADA = "N";
	public final static String[] LT_ST_PRESESTA           = {LT_EL_PRESESTA_ABIERTA,
			                                                 LT_EL_PRESESTA_CERRADA,
			                                                 LT_EL_PRESESTA_NO_CREADA};
	
	public final static String LT_TB_HCONLTTIPO           = "HCONLTTIPO";
	public final static String LT_EL_HCONLTTIPO_MES       = "LT01";
	public final static String LT_EL_HCONLTTIPO_CONC_ANUA = "LT02";
	public final static String LT_EL_HCONLTTIPO_CONC_MES  = "LT03";
	
	public final static String LT_TB_HCONMDTIPO             = "HCONMDTIPO";
	public final static String LT_EL_HCONMDTIPO_FECHA       = "MD01";
	public final static String LT_EL_HCONMDTIPO_DESCRIPCION = "MD02";
	public final static String LT_EL_HCONMDTIPO_IMPORTE     = "MD03";
	public final static String LT_EL_HCONMDTIPO_CONCEPTO    = "MD04";
	
	public final static String   LT_TB_HCONTIPO          = "HCONTIPO";
	public final static String   LT_EL_HCONTIPO_CONTABLE = "C";
	public final static String   LT_EL_HCONTIPO_TRASPASO = "T";
	public final static String   LT_EL_HCONTIPO_ANULADO  = "A";
	public final static String[] LT_ST_HCONTIPO          = {LT_EL_HCONTIPO_CONTABLE,
															LT_EL_HCONTIPO_TRASPASO,
															LT_EL_HCONTIPO_ANULADO};

	public final static String   LT_TB_CUENTIPO          = "CUENTIPO";
	public final static String   LT_EL_CUENTIPO_BANCARIA = "B";
	public final static String   LT_EL_CUENTIPO_EFECTIVO = "E";
	public final static String[] LT_ST_CUENTIPO          = {LT_EL_CUENTIPO_BANCARIA,
			                                                LT_EL_CUENTIPO_EFECTIVO};
	
	public final static String   LT_TB_CONCTIPO         = "CONCTIPO";
	public final static String   LT_EL_CONCTIPO_INGRESO = "I";
	public final static String   LT_EL_CONCTIPO_GASTO   = "G";
	public final static String   LT_EL_CONCTIPO_AMBOS   = "A";
	public final static String[] LT_ST_CONCTIPO         = {LT_EL_CONCTIPO_INGRESO,
			                                               LT_EL_CONCTIPO_GASTO,
			                                               LT_EL_CONCTIPO_AMBOS};

	public final static String   LT_TB_COESTIPO         = "COESTIPO";
	public final static String   LT_EL_COESTIPO_INGRESO = "I";
	public final static String   LT_EL_COESTIPO_GASTO   = "G";
	public final static String[] LT_ST_COESTIPO         = {LT_EL_COESTIPO_INGRESO,
			                                               LT_EL_COESTIPO_GASTO};

	public final static String   LT_TB_TRADTIPO = "TRADTIPO";
	public final static String   LT_EL_TRADTIPO_DOMINIO = "D";
	public final static String   LT_EL_TRADTIPO_TEXTO   = "T";
	public final static String   LT_EL_TRADTIPO_FECHA   = "F";
	public final static String   LT_EL_TRADTIPO_IMPORTE = "I";
	public final static String[] LT_ST_TRADTIPO         = {LT_EL_TRADTIPO_DOMINIO,
			                                               LT_EL_TRADTIPO_TEXTO,
			                                               LT_EL_TRADTIPO_FECHA,
			                                               LT_EL_TRADTIPO_IMPORTE};
	
	//Tablas de literales expandidas
	public final static String   LT_EL_DTO_CUEN = "CUEN";
	public final static String   LT_EL_DTO_HCON = "HCON";
	public final static String   LT_EL_DTO_CATE = "CATE";
	public final static String   LT_EL_DTO_CONC = "CONC";
	public final static String   LT_EL_DTO_PRES = "PRES";
	public final static String   LT_EL_DTO_COES = "COES";
	public final static String   LT_EL_DTO_TRAD = "TRAD";
	public final static String   LT_EL_DTO_CONT = "CONT";
}