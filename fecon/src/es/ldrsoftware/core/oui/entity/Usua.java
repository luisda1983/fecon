package es.ldrsoftware.core.oui.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity	
@Table(name = "USUA")
public class Usua implements Serializable {

	private static final long serialVersionUID = 8187605942950289737L;

	@Id
	@Column(name = "USUAIDEN", nullable = false)
	private String iden;

	@Column(name = "USUAPERF", nullable = false)
	private String perf;

	@Column(name = "USUAMAIL", nullable = false)
	private String mail;

	@Column(name = "USUAPASS", nullable = false)
	private String pass;

	@Column(name = "USUAACTI", nullable = false)
	private String acti;

	@Column(name = "USUAFEAL", nullable = false)
	private int    feal;

	@Column(name = "USUAHOAL", nullable = false)
	private int    hoal;

	@Column(name = "USUAFEUL", nullable = false)
	private int    feul;

	@Column(name = "USUAHOUL", nullable = false)
	private int    houl;

	public String getIden() {
		return iden;
	}

	public void setIden(String iden) {
		this.iden = iden;
	}

	public String getPerf() {
		return perf;
	}

	public void setPerf(String perf) {
		this.perf = perf;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getActi() {
		return acti;
	}

	public void setActi(String acti) {
		this.acti = acti;
	}

	public int getFeal() {
		return feal;
	}

	public void setFeal(int feal) {
		this.feal = feal;
	}

	public int getHoal() {
		return hoal;
	}

	public void setHoal(int hoal) {
		this.hoal = hoal;
	}

	public int getFeul() {
		return feul;
	}

	public void setFeul(int feul) {
		this.feul = feul;
	}

	public int getHoul() {
		return houl;
	}

	public void setHoul(int houl) {
		this.houl = houl;
	}
	
}
