package es.ldrsoftware.core.fwk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.fwk.data.PV;

@Entity	
@Table(name = "PARA")
@IdClass(ParaPK.class)
public class Para extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1691261471239275686L;

	@Id
	@Column(name = "PARATBLA", unique = true, nullable = false)
	private String tbla;

	public final static String TBLA = "Tabla de Parámetros";
	
	@Id
	@Column(name = "PARACLAV", unique = true, nullable = false)
	private String clav;

	public final static String CLAV = "Clave de Parámetro";
	
	@Column(name = "PARAORIG", nullable = false)
	private String orig;

	public final static String ORIG = "Origen de Parámetro";
	
	@Column(name = "PARADESC", nullable = false)
	private String desc;

	public final static String DESC = "Descripción de Parámetro";
	
	@Column(name = "PARAVALO", nullable = false)
	private String valo;
	
	public final static String VALO = "Valor de Parámetro";
	
	@Column(name = "PARAFEAL", nullable = false)
	private    int feal;

	public final static String FEAL = "Fecha de alta de Parámetro";
	
	@Column(name = "PARAMODI", nullable = false)
	private String modi;

	public final static String MODI = "Indicador de parámetro modificable";
	
	@Column(name = "PARAFEMO", nullable = false)
	private    int femo;

	public final static String FEMO = "Fecha de modificación de parámetro";
	
	@Column(name = "PARAHOMO", nullable = false)
	private    int homo;

	public final static String HOMO = "Hora de modificación de parámetro";
	
	@Column(name = "PARAUSMO", nullable = false)
	private String usmo;

	public final static String USMO = "Usuario de modificación de parámetro";
	
	@Transient
	private PV pval;
	
	public String key() {
		return key(tbla, clav);
	}
	
	public final static String key(String tbla, String clav) {
		return tbla + "|"
			 + clav;
	}
	
	public void validate() throws Exception {
		
	}
	
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