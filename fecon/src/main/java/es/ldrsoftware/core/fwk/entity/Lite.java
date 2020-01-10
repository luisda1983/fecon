package es.ldrsoftware.core.fwk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import es.ldrsoftware.core.arq.BaseDTO;

@Entity	
@Table(name = "LITE")
@IdClass(LitePK.class)
public class Lite extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 5566487941656016866L;

	@Id
	@Column(name = "LITETBLA", unique = true, nullable = false)
	private String tbla;

	public final static String TBLA = "Tabla de Literales";
	
	@Id
	@Column(name = "LITECLAV", unique = true, nullable = false)
	private String clav;
	
	public final static String CLAV = "Clave de Literal";
	
	@Column(name = "LITEDESC", nullable = false)
	private String desc;

	public final static String DESC = "Descripción de Literal";
	
	@Column(name = "LITEORDE", nullable = false)
	private    int orde;
	
	public final static String ORDE = "Orden de Literal";
	
	@Column(name = "LITEFEAL", nullable = false)
	private    int feal;

	public final static String FEAL = "Fecha de alta de Literal";
	
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getOrde() {
		return orde;
	}

	public void setOrde(int orde) {
		this.orde = orde;
	}

	public int getFeal() {
		return feal;
	}

	public void setFeal(int feal) {
		this.feal = feal;
	}
}

class LitePK implements Serializable {

	private static final long serialVersionUID = -6774898585781547386L;

	@Id
	@Column(name = "LITETBLA", unique = true, nullable = false)
	private String tbla;

	@Id
	@Column(name = "LITECLAV", unique = true, nullable = false)
	private String clav;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof LitePK) {
			LitePK obj = (LitePK)o;
			if (obj.tbla.equals(this.tbla) && obj.clav.equals(this.clav)) {
				return true;
			}
		}
		return false;
	}
}