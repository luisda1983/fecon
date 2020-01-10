package es.ldrsoftware.core.oui.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.fwk.data.LiteData;


@Entity	
@Table(name = "USUA")
public class Usua extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 8187605942950289737L;

	@Id
	@Column(name = "USUAIDEN", nullable = false)
	private String iden;

	public final static String IDEN = "Identificador de Usuario";
	
	@Column(name = "USUAMAIL", nullable = false)
	private String mail;

	public final static String MAIL = "Email de Usuario"; 
	
	@Column(name = "USUAPASS", nullable = false)
	private String pass;

	public final static String PASS = "Password";

	public final static String CPAS = "Confirmación de Password";
	
	@Column(name = "USUAACTI", nullable = false)
	private String acti;

	public final static String ACTI = "Indicador de usuario activo";
	
	@Column(name = "USUAFEAL", nullable = false)
	private int    feal;

	public final static String FEAL = "Fecha de alta de Usuario";
	
	@Column(name = "USUAHOAL", nullable = false)
	private int    hoal;

	public final static String HOAL = "Hora de alta de Usuario";
	
	@Column(name = "USUAFEUL", nullable = false)
	private int    feul;

	public final static String FEUL = "Fecha de última actividad de Usuario";
	
	@Column(name = "USUAHOUL", nullable = false)
	private int    houl;

	public final static String HOUL = "Hora de última actividad de Usuario";
	
	public String key() {
		return key(iden);
	}
	
	public static String key(String iden) {
		return iden;
	}
	
	public void validate() throws Exception {
		validateFieldString(iden, 30, Usua.IDEN);
		validateFieldString(mail, 100, Usua.MAIL);
		validateFieldString(pass, 30, Usua.PASS);
		validateFieldDomain(acti, Usua.ACTI, LiteData.LT_ST_BOOL);
		validateFieldDate(feal, Usua.FEAL);
		validateFieldTime(hoal, Usua.HOAL);
		validateFieldDate(feul, Usua.FEUL);
		validateFieldTime(houl, Usua.HOUL);	
	}
	
	public String getIden() {
		return iden;
	}

	public void setIden(String iden) {
		this.iden = iden;
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
