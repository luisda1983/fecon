package es.ldrsoftware.core.mnu.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity	
@Table(name = "DTMN")
@IdClass(DtmnPK.class)
public class Dtmn implements Serializable {

	private static final long serialVersionUID = -1074116688488083826L;

	@Id
	@Column(name = "DTMNCTMN", unique = true, nullable = false)
	private    int ctmn;

	@Id
	@Column(name = "DTMNIDEN", unique = true, nullable = false)
	private    int iden;
	
	@Column(name = "DTMNDESC", nullable = false)
	private String desc;
	
	@Column(name = "DTMNACTI", nullable = false)
	private String acti;
	
	@Column(name = "DTMNORDE", nullable = false)
	private    int orde;
	
	@Column(name = "DTMNPATH", nullable = false)
	private String path;
	
	@Column(name = "DTMNICON", nullable = false)
	private String icon;

	public int getCtmn() {
		return ctmn;
	}

	public void setCtmn(int ctmn) {
		this.ctmn = ctmn;
	}

	public int getIden() {
		return iden;
	}

	public void setIden(int iden) {
		this.iden = iden;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getActi() {
		return acti;
	}

	public void setActi(String acti) {
		this.acti = acti;
	}

	public int getOrde() {
		return orde;
	}

	public void setOrde(int orde) {
		this.orde = orde;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}

class DtmnPK implements Serializable {

	private static final long serialVersionUID = -6710988508175895822L;

	@Id
	@Column(name = "DTMNCTMN", unique = true, nullable = false)
	private    int ctmn;

	@Id
	@Column(name = "DTMNIDEN", unique = true, nullable = false)
	private    int iden;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof DtmnPK) {
			DtmnPK obj = (DtmnPK)o;
			if (obj.ctmn == this.ctmn && obj.iden == this.iden) {
				return true;
			}
		}
		return false;
	}
}