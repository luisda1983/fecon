package es.ldrsoftware.core.fwk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import es.ldrsoftware.core.fwk.data.PV;

@Entity	
@Table(name = "PARA")
@IdClass(ParaPK.class)
public class Para implements Serializable {

	private static final long serialVersionUID = 1691261471239275686L;

	@Id
	@Column(name = "PARATBLA", unique = true, nullable = false)
	private String tbla;

	@Id
	@Column(name = "PARACLAV", unique = true, nullable = false)
	private String clav;

	@Column(name = "PARAORIG", nullable = false)
	private String orig;

	@Column(name = "PARADESC", nullable = false)
	private String desc;

	@Column(name = "PARAVALO", nullable = false)
	private String valo;
	
	@Column(name = "PARAFEAL", nullable = false)
	private    int feal;

	@Column(name = "PARAMODI", nullable = false)
	private String modi;

	@Column(name = "PARAFEMO", nullable = false)
	private    int femo;

	@Column(name = "PARAHOMO", nullable = false)
	private    int homo;

	@Column(name = "PARAUSMO", nullable = false)
	private String usmo;

	@Transient
	private PV pval;
	
	public String getTbla() {
		return tbla;
	}

	public void setTbla(String tbla) {
		this.tbla = tbla;
	}

	public String getClav() {
		return clav;
	}

	public void setClav(String clav) {
		this.clav = clav;
	}

	public String getOrig() {
		return orig;
	}

	public void setOrig(String orig) {
		this.orig = orig;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getValo() {
		return valo;
	}

	public void setValo(String valo) {
		this.valo = valo;
	}

	public int getFeal() {
		return feal;
	}

	public void setFeal(int feal) {
		this.feal = feal;
	}

	public String getModi() {
		return modi;
	}

	public void setModi(String modi) {
		this.modi = modi;
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

	public PV getPval() {
		return pval;
	}
	
	public void setPval(PV pval) {
		this.pval = pval;
	}
}

class ParaPK implements Serializable {

	private static final long serialVersionUID = 7531054146929800831L;

	@Id
	@Column(name = "PARATBLA", unique = true, nullable = false)
	private String tbla;

	@Id
	@Column(name = "PARACLAV", unique = true, nullable = false)
	private String clav;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof ParaPK) {
			ParaPK obj = (ParaPK)o;
			if (obj.tbla.equals(this.tbla) && obj.clav.equals(this.clav)) {
				return true;
			}
		}
		return false;
	}
}