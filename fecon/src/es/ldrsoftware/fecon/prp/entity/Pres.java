package es.ldrsoftware.fecon.prp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity	
@Table(name = "PRES")
@IdClass(PresPK.class)
public class Pres implements Serializable {

	private static final long serialVersionUID = 6149532364079334432L;

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
	
	@Column(name = "PRESIMPO", nullable = false)
	private double impo;

	@Column(name = "PRESIMPR", nullable = false)
	private double impr;

	@Column(name = "PRESIMNP", nullable = false)
	private double imnp;

	@Column(name = "PRESIMTO", nullable = false)
	private double imto;

	@Column(name = "PRESDESV", nullable = false)
	private double desv;

	@Column(name = "PRESESTA", nullable = false)
	private String esta;

	@Column(name = "PRESOBSE", nullable = false)
	private String obse;

	@Transient
	private double toha;
	
	@Transient
	private double tode;
	
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