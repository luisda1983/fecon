package es.ldrsoftware.core.fwk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity	
@Table(name = "RELA")
@IdClass(RelaPK.class)
public class Rela implements Serializable {

	private static final long serialVersionUID = 4179560358220168105L;

	@Id
	@Column(name = "RELAMAE1", unique = true, nullable = false)
	private String mae1;

	@Id
	@Column(name = "RELACLN1", unique = true, nullable = false)
	private long   cln1;
	
	@Id
	@Column(name = "RELACLC1", unique = true, nullable = false)
	private String clc1;
	
	@Id
	@Column(name = "RELAMAE2", unique = true, nullable = false)
	private String mae2;

	@Id
	@Column(name = "RELACLN2", unique = true, nullable = false)
	private long   cln2;

	@Id
	@Column(name = "RELACLC2", unique = true, nullable = false)
	private String clc2;
	
	@Column(name = "RELAESTA", nullable = false)
	private String esta;

	@Column(name = "RELAFEAL", nullable = false)
	private    int feal;

	public String getMae1() {
		return mae1;
	}

	public void setMae1(String mae1) {
		this.mae1 = mae1;
	}

	public long getCln1() {
		return cln1;
	}

	public void setCln1(long cln1) {
		this.cln1 = cln1;
	}

	public String getClc1() {
		return clc1;
	}

	public void setClc1(String clc1) {
		this.clc1 = clc1;
	}

	public String getMae2() {
		return mae2;
	}

	public void setMae2(String mae2) {
		this.mae2 = mae2;
	}

	public long getCln2() {
		return cln2;
	}

	public void setCln2(long cln2) {
		this.cln2 = cln2;
	}

	public String getClc2() {
		return clc2;
	}

	public void setClc2(String clc2) {
		this.clc2 = clc2;
	}

	public String getEsta() {
		return esta;
	}

	public void setEsta(String esta) {
		this.esta = esta;
	}

	public int getFeal() {
		return feal;
	}

	public void setFeal(int feal) {
		this.feal = feal;
	}
	
}

class RelaPK implements Serializable {

	private static final long serialVersionUID = 5036199401189225974L;

	@Id
	@Column(name = "RELAMAE1", unique = true, nullable = false)
	private String mae1;

	@Id
	@Column(name = "RELACLN1", unique = true, nullable = false)
	private long   cln1;
	
	@Id
	@Column(name = "RELACLC1", unique = true, nullable = false)
	private String clc1;
	
	@Id
	@Column(name = "RELAMAE2", unique = true, nullable = false)
	private String mae2;

	@Id
	@Column(name = "RELACLN2", unique = true, nullable = false)
	private long   cln2;

	@Id
	@Column(name = "RELACLC2", unique = true, nullable = false)
	private String clc2;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof RelaPK) {
			RelaPK obj = (RelaPK)o;
			if (obj.mae1.equals(this.mae1) && obj.mae2.equals(this.mae2) && 
				obj.clc1.equals(this.clc1) && obj.clc2.equals(this.clc2) &&
				obj.cln1 == this.cln1 && obj.cln2 == this.cln2) {
				return true;
			}
		}
		return false;
	}
}