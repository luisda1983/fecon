package es.ldrsoftware.fecon.cnt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.arq.util.StringUtil;
import es.ldrsoftware.fecon.data.LiteData;

@Entity	
@Table(name = "HCON")
public class Hcon extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -246781978778352376L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "HCONIDEN", nullable = false)
	private long   iden;

	public final static String IDEN = "Identificador de Apunte";
	
	@Column(name = "HCONINST", nullable = false)
	private long   inst;

	public final static String INST = "Instalación de Apunte";
	
	@Column(name = "HCONCUEN", nullable = false)
	private long   cuen;
	
	public final static String CUEN = "Cuenta de Apunte";
	
	@Column(name = "HCONTIPO", nullable = false)
	private String tipo;
	
	public final static String TIPO = "Tipo de Apunte";
	
	@Column(name = "HCONFEOP", nullable = false)
	private int    feop;

	public final static String FEOP = "Fecha de operación de Apunte";
	
	@Column(name = "HCONHOOP", nullable = false)
	private int    hoop;

	public final static String HOOP = "Hora de operación de Apunte";
	
	@Column(name = "HCONFEVA", nullable = false)
	private int    feva;

	public final static String FEVA = "Fecha valor de Apunte";
	
	@Column(name = "HCONCATE", nullable = false)
	private long   cate;

	public final static String CATE = "Categoría de Apunte";
	
	@Column(name = "HCONCONC", nullable = false)
	private long   conc;

	public final static String CONC = "Concepto de Apunte";
	
	@Column(name = "HCONPRES", nullable = false)
	private String pres;

	public final static String PRES = "Indicador de Apunte incluido en presupuesto";
	
	@Column(name = "HCONPRAN", nullable = false)
	private int    pran;

	public final static String PRAN = "Anualidad presupuestaria que incluye el Apunte";
	
	@Column(name = "HCONPRMS", nullable = false)
	private int    prms;

	public final static String PRMS = "Mes presupuestario que incluye el Apunte";
	
	@Column(name = "HCONPRCT", nullable = false)
	private long   prct;
	
	public final static String PRCT = "Categoría del presupuesto que incluye el Apunte";
	
	@Column(name = "HCONPRCC", nullable = false)
	private long   prcc;
	
	public final static String PRCC = "Concepto del presupuesto que incluye el Apunte";
	
	@Column(name = "HCONIMPO", nullable = false)
	private double impo;
	
	public final static String IMPO = "Importe de Apunte";
	
	@Column(name = "HCONDESC", nullable = false)
	private String desc;
	
	public final static String DESC = "Descripción del Apunte";
	
	@Column(name = "HCONUSUA", nullable = false)
	private String usua;

	public final static String USUA = "Usuario que realiza el Apunte";
	
	
	public final static String ANUA = "Año de Apunte";
	public final static String MESH = "Mes de Apunte";
	public final static String TIMO = "Tipo de modificación de Apunte";
	
	public String key() {
		return key(iden);
	}
	
	public final static String key(long iden) {
		return StringUtil.extend(iden, 10);
	}
	
	public void validate() throws Exception {
		validateFieldLong(inst, 0, 999999999, Hcon.INST);
		validateFieldLong(cuen, 0, 999999999, Hcon.CUEN);
		validateFieldDomain(tipo, Hcon.TIPO, LiteData.LT_ST_HCONTIPO);
		validateFieldDate(feop, Hcon.FEOP);
		validateFieldTime(hoop, Hcon.HOOP);
		validateFieldDate(feva, Hcon.FEVA);
		
		if (!LiteData.LT_EL_HCONTIPO_TRASPASO.equals(tipo)) {
			validateFieldLong(cate, 0, 999999999, Hcon.CATE);
			validateFieldLong(conc, 0, 999999999, Hcon.CONC);
			validateFieldDomain(pres, Hcon.PRES, es.ldrsoftware.core.fwk.data.LiteData.LT_ST_BOOL);
			validateFieldYear(pran, Hcon.PRAN);
			if (prms != 0) {
				validateFieldMonth(prms, Hcon.PRMS);
			}
			validateFieldLong(prct, 1, 999999999, Hcon.PRCT);
			validateFieldLong(prcc, 0, 999999999, Hcon.PRCC);
			
		}
		
		validateFieldDouble(impo, -99999999999.99, 99999999999.99, Hcon.IMPO);
		
		//TODO: cuando el desc se monte en función del concrepto: 
		//validateStringRequired(hcon.getDesc(), AppNotify.HCON_SAVE_DESC_RQRD);
		//validateStringNotNull(hcon.getDesc(), AppNotify.HCON_SAVE_DESC_RQRD);
		//validateStringMaxLength(hcon.getDesc(), 200, AppNotify.HCON_SAVE_DESC_MAXL);
		
		validateFieldString(usua, 30, Hcon.USUA);
	}
	
	public long getIden() {
		return iden;
	}

	public void setIden(long iden) {
		this.iden = iden;
	}

	public long getInst() {
		return inst;
	}

	public void setInst(long inst) {
		this.inst = inst;
	}

	public long getCuen() {
		return cuen;
	}

	public void setCuen(long cuen) {
		this.cuen = cuen;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getFeop() {
		return feop;
	}

	public void setFeop(int feop) {
		this.feop = feop;
	}

	public int getHoop() {
		return hoop;
	}

	public void setHoop(int hoop) {
		this.hoop = hoop;
	}

	public int getFeva() {
		return feva;
	}

	public void setFeva(int feva) {
		this.feva = feva;
	}

	public String getPres() {
		return pres;
	}

	public void setPres(String pres) {
		this.pres = pres;
	}


	public int getPran() {
		return pran;
	}

	public void setPran(int pran) {
		this.pran = pran;
	}

	public int getPrms() {
		return prms;
	}

	public void setPrms(int prms) {
		this.prms = prms;
	}

	public long getPrct() {
		return prct;
	}

	public void setPrct(long prct) {
		this.prct = prct;
	}

	public long getPrcc() {
		return prcc;
	}

	public void setPrcc(long prcc) {
		this.prcc = prcc;
	}

	public long getCate() {
		return cate;
	}

	public void setCate(long cate) {
		this.cate = cate;
	}

	public long getConc() {
		return conc;
	}

	public void setConc(long conc) {
		this.conc = conc;
	}

	public double getImpo() {
		return impo;
	}

	public void setImpo(double impo) {
		this.impo = impo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUsua() {
		return usua;
	}

	public void setUsua(String usua) {
		this.usua = usua;
	}
}
