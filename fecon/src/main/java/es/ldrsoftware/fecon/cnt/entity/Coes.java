package es.ldrsoftware.fecon.cnt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.arq.util.StringUtil;
import es.ldrsoftware.fecon.data.LiteData;

@Entity	
@Table(name = "COES")
@IdClass(CoesPK.class)
public class Coes extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -2939749996744671770L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "COESIDEN", nullable = false)
	private long   iden;

	public final static String IDEN = "Identificador de Código Específico";
	
	@Column(name = "COESINST", nullable = false)
	private long   inst;

	public final static String INST = "Instalación de Código Específico";

	@Id
	@Column(name = "COESANIN", nullable = false)
	private long   anin;
	
	public final static String ANIN = "Año de inicio de Código Específico";
	
	@Column(name = "COESANFI", nullable = false)
	private long   anfi;
	
	public final static String ANFI = "Año de fin de Código Específico";
	
	@Column(name = "COESTIPO", nullable = false)
	private String tipo;
	
	public final static String TIPO = "Tipo de Código Específico";
	
	@Column(name = "COESDESC", nullable = false)
	private String desc;
	
	public final static String DESC = "Descripción de Código Específico";
	
	@Column(name = "COESFAVO", nullable = false)
	private String favo;
	
	public final static String FAVO = "Indicador de Código Específico favorito";

	@Column(name = "COESUSAD", nullable = false)
	private long   usad;
	
	public final static String USAD = "Contador de uso de Código Específico";

//	@Column(name = "COESTRAD", nullable = false)
//	private long trad;

//	public final static String TRAD = "Traducción de Código Específico";
	
	@Column(name = "COESCATE", nullable = false)
	private long   cate;
	
	public final static String CATE = "Categoria de Código Específico";
	
	@Column(name = "COESCONC", nullable = false)
	private long   conc;
	
	public final static String CONC = "Concepto de Código Específico";
	
	@Column(name = "COESFEAL", nullable = false)
	private int    feal;

	public final static String FEAL = "Fecha de alta de Código Específico";
	
	@Column(name = "COESHOAL", nullable = false)
	private int    hoal;
	
	public final static String HOAL = "Hora de alta de Código Específico";
	
	@Column(name = "COESUSAL", nullable = false)
	private String usal;

	public final static String USAL = "Usuario de alta de Código Específico";
	
	@Column(name = "COESFEMO", nullable = false)
	private int    femo;

	public final static String FEMO = "Fecha de modificación de Código Específico";
	
	@Column(name = "COESHOMO", nullable = false)
	private int    homo;
	
	public final static String HOMO = "Hora de modificación de Código Específico";
	
	@Column(name = "COESUSMO", nullable = false)
	private String usmo;

	public final static String USMO = "Usuario de modificación de Código Específico";
	
	public String key() {
		return key(iden);
	}
	
	public final static String key(long iden) {
		return StringUtil.extend(iden, 10);
	}
	
	public void validate() throws Exception {
		validateFieldLong(iden, 0, 999999999, Coes.IDEN);
		validateFieldLong(inst, 0, 999999999, Coes.INST);
		validateFieldYear(anin, Coes.ANIN);
		validateFieldYear(anfi, Coes.ANFI);
		validateFieldDomain(tipo, Coes.TIPO, LiteData.LT_ST_COESTIPO);
		validateFieldString(desc, 100, Coes.DESC);
		validateFieldDomain(favo, Coes.FAVO, es.ldrsoftware.core.fwk.data.LiteData.LT_ST_BOOL);
		validateFieldLong(usad, 0, 999999999, Coes.USAD);
//		validateFieldLong(trad, 0, 999999999, Coes.TRAD);
		validateFieldLong(cate, 0, 999999999, Coes.CATE);
		validateFieldLong(conc, 0, 999999999, Coes.CONC);
		validateFieldDate(feal, Coes.FEAL);
		validateFieldTime(hoal, Coes.HOAL);
		validateFieldString(usal, 30, Coes.USAL);
		validateFieldDate(femo, Coes.FEMO);
		validateFieldTime(homo, Coes.HOMO);
		validateFieldString(usmo, 30, Coes.USMO);
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFavo() {
		return favo;
	}
	
	public void setFavo(String favo) {
		this.favo = favo;
	}
	
	public long getUsad() {
		return usad;
	}
	
	public void setUsad(long usad) {
		this.usad = usad;
	}
	
//	public long getTrad() {
//		return trad;
//	}
//	
//	public void setTrad(long trad) {
//		this.trad = trad;
//	}

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
	
	public int getFeal() {
		return feal;
	}

	public void setFeal(int feal) {
		this.feal = feal;
	}

	public int getHoal() {
		return hoal;
	}

	public void setHoal(int hoal) {
		this.hoal = hoal;
	}

	public String getUsal() {
		return usal;
	}

	public void setUsal(String usal) {
		this.usal = usal;
	}

	public int getFemo() {
		return femo;
	}

	public void setFemo(int femo) {
		this.femo = femo;
	}

	public int getHomo() {
		return homo;
	}

	public void setHomo(int homo) {
		this.homo = homo;
	}

	public String getUsmo() {
		return usmo;
	}

	public void setUsmo(String usmo) {
		this.usmo = usmo;
	}
}

class CoesPK implements Serializable {

	private static final long serialVersionUID = -7793029146164143962L;

	@Id
	@Column(name = "COESIDEN", unique = true, nullable = false)
	private   long iden;

	@Id
	@Column(name = "COESANIN", unique = true, nullable = false)
	private    int anin;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof CoesPK) {
			CoesPK obj = (CoesPK)o;
			if (obj.iden == this.iden && 
				obj.anin == this.anin) {
				return true;
			}
		}
		return false;
	}
}