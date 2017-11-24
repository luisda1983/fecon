package ldrsoftware.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "THPARM")
@IdClass(HParmPK.class)
public class HParm {

	@Id
	@Column(name = "PARMTBLA", nullable = false)
	private String tbla;

	@Id
	@Column(name = "PARMCLAV", nullable = false)
	private String clav;
	
	@Column(name = "PARMDESC", nullable = false)
	private String desc;
	
	@Column(name = "PARMVALO", nullable = false)
	private String valo;
	
	@Column(name = "PARMORDE", nullable = false)
	private int orde;
	
	public HParm() {
		tbla = "";
		clav = "";
		desc = "";
		valo = "";
		orde = 0;
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

	public String getValo() {
		return valo;
	}

	public void setValo(String valo) {
		this.valo = valo;
	}

	public int getOrde() {
		return orde;
	}

	public void setOrde(int orde) {
		this.orde = orde;
	}

	@Override
	public String toString() {
		return "Parm [tbla=" + tbla + ", "
				   + "clav=" + clav + ", "
				   + "desc=" + desc + ", "
				   + "valo=" + valo + ", "
				   + "orde=" + orde + ", "
				   ;
	}
		
		
}

class HParmPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PARMTBLA", nullable = false)
	private String tbla;

	@Id
	@Column(name = "PARMCLAV", nullable = false)
	private String clav;

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

	@Override
	public boolean equals(Object o) {
		if (o instanceof HParmPK) {
			HParmPK obj = (HParmPK)o;
			if (obj.getTbla() == this.tbla && obj.getClav() == this.clav) {
				return true;
			}
		}
		return false;
	}
}