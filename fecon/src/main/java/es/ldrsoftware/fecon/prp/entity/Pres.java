package es.ldrsoftware.fecon.prp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.arq.util.StringUtil;
import es.ldrsoftware.fecon.data.LiteData;

@Entity	
@Table(name = "PRES")
@IdClass(PresPK.class)
public class Pres extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 6149532364079334432L;

	@Id
	@Column(name = "PRESINST", unique = true, nullable = false)
	private   long inst;

	public final static String INST = "Instalación de Presupuesto";
	
	@Id
	@Column(name = "PRESANUA", unique = true, nullable = false)
	private    int anua;

	public final static String ANUA = "Anualidad de Presupuesto";
	
	@Id
	@Column(name = "PRESMESP", unique = true, nullable = false)
	private    int mesp;

	public final static String MESP = "Mensualidad de Presupuesto";
	
	@Id
	@Column(name = "PRESCATE", unique = true, nullable = false)
	private   long cate;
	
	public final static String CATE = "Categoría de Presupuesto";
	
	@Id
	@Column(name = "PRESCONC", unique = true, nullable = false)
	private   long conc;
	
	public final static String CONC = "Concepto de Presupuesto";
	
	@Column(name = "PRESIMPO", nullable = false)
	private double impo;

	public final static String IMPO = "Importe de Presupuesto";
	
	@Column(name = "PRESIMPR", nullable = false)
	private double impr;

	public final static String IMPR = "Importe imputado incluído en el Presupuesto";
	
	@Column(name = "PRESIMNP", nullable = false)
	private double imnp;

	public final static String IMNP = "Importe imputado excluído del Presupuesto";
	
	@Column(name = "PRESIMTO", nullable = false)
	private double imto;

	public final static String IMTO = "Importe total vinculado al Presupuesto";
	
	@Column(name = "PRESDESV", nullable = false)
	private double desv;

	public final static String DESV = "Desviación respecto al Presupuesto";
	
	@Column(name = "PRESBALA", nullable = false)
	private double bala;
	
	public final static String BALA = "Balance resultante del Presupuesto";
	
	@Column(name = "PRESESTA", nullable = false)
	private String esta;

	public final static String ESTA = "Estado del Presupuesto";
	
	@Column(name = "PRESOBSE", nullable = false)
	private String obse;

	public final static String OBSE = "Observaciones al Presupuesto";
	
	@Transient
	private double toha;
	
	@Transient
	private double tode;
	
	public final static String FECH = "Fecha";
	
	public String key() {
		return key(anua, mesp, cate, conc);
	}
	
	public final static String key(long anua, long mesp, long cate, long conc) {
		return StringUtil.extend(anua, 4) + "|"
		     + StringUtil.extend(mesp, 2) + "|"
		     + StringUtil.extend(cate, 10) + "|"
		     + StringUtil.extend(conc, 10);
	}
	
	public final static String key(long fech, long cate, long conc) {
		return StringUtil.extend(fech, 8) + "|"
			 + StringUtil.extend(cate, 10) + "|"
			 + StringUtil.extend(conc, 10);
	}
	
	public void validate() throws Exception {
		validateFieldLong(inst, 0, 999999999, Pres.INST);
		validateFieldYear(anua, Pres.ANUA);
		
		if (mesp != 0) {
			validateFieldMonth(mesp, Pres.MESP);
		}

		validateFieldLong(cate, 0, 999999999, Pres.CATE);
		validateFieldLong(conc, 0, 999999999, Pres.CONC);
		validateFieldDouble(impo, -99999999999.99, 99999999999.99, Pres.IMPO);
		validateFieldDouble(impr, -99999999999.99, 99999999999.99, Pres.IMPR);
		validateFieldDouble(imnp, -99999999999.99, 99999999999.99, Pres.IMNP);
		validateFieldDouble(imto, -99999999999.99, 99999999999.99, Pres.IMTO);
		validateFieldDouble(desv, -99999999999.99, 99999999999.99, Pres.DESV);
		validateFieldDouble(bala, -99999999999.99, 99999999999.99, Pres.BALA);
		validateFieldDomain(esta, Pres.ESTA, LiteData.LT_ST_PRESESTA);
		//TODO: validar obse
	}
	
	public long getInst() {
		return inst;
	}

	public void setInst(long inst) {
		this.inst = inst;
	}

	public int getAnua() {
		return anua;
	}

	public void setAnua(int anua) {
		this.anua = anua;
	}

	public int getMesp() {
		return mesp;
	}

	public void setMesp(int mesp) {
		this.mesp = mesp;
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

	public double getImpr() {
		return impr;
	}

	public void setImpr(double impr) {
		this.impr = impr;
	}

	public double getImnp() {
		return imnp;
	}

	public void setImnp(double imnp) {
		this.imnp = imnp;
	}

	public double getImto() {
		return imto;
	}

	public void setImto(double imto) {
		this.imto = imto;
	}

	public double getDesv() {
		return desv;
	}

	public void setDesv(double desv) {
		this.desv = desv;
	}

	public double getBala() {
		return bala;
	}
	
	public void setBala(double bala) {
		this.bala = bala;
	}
	
	public String getEsta() {
		return esta;
	}

	public void setEsta(String esta) {
		this.esta = esta;
	}

	public String getObse() {
		return obse;
	}

	public void setObse(String obse) {
		this.obse = obse;
	}

	public double getToha() {
		return toha;
	}

	public void setToha(double toha) {
		this.toha = toha;
	}

	public double getTode() {
		return tode;
	}

	public void setTode(double tode) {
		this.tode = tode;
	}
}

class PresPK implements Serializable {

	private static final long serialVersionUID = 6516997652014374500L;

	@Id
	@Column(name = "PRESINST", unique = true, nullable = false)
	private   long inst;

	@Id
	@Column(name = "PRESANUA", unique = true, nullable = false)
	private    int anua;

	@Id
	@Column(name = "PRESMESP", unique = true, nullable = false)
	private    int mesp;

	@Id
	@Column(name = "PRESCATE", unique = true, nullable = false)
	private   long cate;
	
	@Id
	@Column(name = "PRESCONC", unique = true, nullable = false)
	private   long conc;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof PresPK) {
			PresPK obj = (PresPK)o;
			if (obj.inst == this.inst && 
				obj.anua == this.anua &&
				obj.mesp == this.mesp &&
				obj.cate == this.cate &&
				obj.conc == this.conc) {
				return true;
			}
		}
		return false;
	}
}