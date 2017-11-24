package es.ldrsoftware.core.mnu.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity	
@Table(name = "CTMN")
public class Ctmn implements Serializable {

	private static final long serialVersionUID = -1672008928841240755L;

	@Column(name = "CTMNPERF", nullable = false)
	private String perf;
	
	@Id
	@Column(name = "CTMNIDEN", unique = true, nullable = false)
	private    int iden;
	
	@Column(name = "CTMNDESC", nullable = false)
	private String desc;
	
	@Column(name = "CTMNACTI", nullable = false)
	private String acti;
	
	@Column(name = "CTMNORDE", nullable = false)
	private     int orde;
	
	@Transient
	public List<Dtmn> dtmnList;

	public String getPerf() {
		return perf;
	}

	public void setPerf(String perf) {
		this.perf = perf;
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

	public List<Dtmn> getDtmnList() {
		return dtmnList;
	}

	public void setDtmnList(List<Dtmn> dtmnList) {
		this.dtmnList = dtmnList;
	}
	
}
