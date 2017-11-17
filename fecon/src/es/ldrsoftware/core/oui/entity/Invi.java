package es.ldrsoftware.core.oui.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity	
@Table(name = "INVI")
public class Invi implements Serializable {

	private static final long serialVersionUID = -5561438190935271112L;

	@Id
	@Column(name = "INVIIDEN", nullable = false)
	private String iden;

	@Column(name = "INVITIPO", nullable = false)
	private String tipo;

	@Column(name = "INVIESTA", nullable = false)
	private String esta;

	@Column(name = "INVIMAIL", nullable = false)
	private String mail;

	@Column(name = "INVIINST", nullable = false)
	private long   inst;

	@Column(name = "INVIUSUA", nullable = false)
	private String usua;

	@Column(name = "INVIFEAL", nullable = false)
	private int    feal;

	@Column(name = "INVIHOAL", nullable = false)
	private int    hoal;

	@Column(name = "INVIFEMO", nullable = false)
	private int    femo;

	@Column(name = "INVIHOMO", nullable = false)
	private int    homo;

	public String getIden() {
		return iden;
	}

	public void setIden(String iden) {
		this.iden = iden;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEsta() {
		return esta;
	}

	public void setEsta(String esta) {
		this.esta = esta;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public long getInst() {
		return inst;
	}

	public void setInst(long inst) {
		this.inst = inst;
	}

	public String getUsua() {
		return usua;
	}

	public void setUsua(String usua) {
		this.usua = usua;
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
	
}
