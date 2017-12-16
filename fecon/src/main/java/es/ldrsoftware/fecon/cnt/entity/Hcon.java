package es.ldrsoftware.fecon.cnt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity	
@Table(name = "HCON")
public class Hcon implements Serializable {

	private static final long serialVersionUID = -246781978778352376L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "HCONIDEN", nullable = false)
	private long   iden;

	@Column(name = "HCONINST", nullable = false)
	private long   inst;

	@Column(name = "HCONCUEN", nullable = false)
	private long   cuen;
	
	@Column(name = "HCONTIPO", nullable = false)
	private String tipo;
	
	@Column(name = "HCONFEOP", nullable = false)
	private int    feop;

	@Column(name = "HCONHOOP", nullable = false)
	private int    hoop;

	@Column(name = "HCONFEVA", nullable = false)
	private int    feva;

	@Column(name = "HCONCATE", nullable = false)
	private long   cate;

	@Column(name = "HCONCONC", nullable = false)
	private long   conc;

	@Column(name = "HCONPRES", nullable = false)
	private String pres;

	@Column(name = "HCONPRAN", nullable = false)
	private int    pran;

	@Column(name = "HCONPRMS", nullable = false)
	private int    prms;

	@Column(name = "HCONPRCT", nullable = false)
	private long   prct;
	
	@Column(name = "HCONPRCC", nullable = false)
	private long   prcc;
	
	@Column(name = "HCONIMPO", nullable = false)
	private double impo;
	
	@Column(name = "HCONDESC", nullable = false)
	private String desc;
	
	@Column(name = "HCONUSUA", nullable = false)
	private String usua;

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
