package es.ldrsoftware.core.btc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity	
@Table(name = "BTCH")
public class Btch implements Serializable {

	private static final long serialVersionUID = -2534977557617460342L;

	@Id
	@Column(name = "BTCHIDEN", nullable = false)
	private String iden;
	
	@Column(name = "BTCHDESC", nullable = false)
	private String desc;
	
	@Column(name = "BTCHFEAL", nullable = false)
	private int    feal;
	
	@Column(name = "BTCHTIPO", nullable = false)
	private String tipo;
	
	@Column(name = "BTCHORDE", nullable = false)
	private int    orde;
	
	@Column(name = "BTCHPLAN", nullable = false)
	private String plan;
	
	@Column(name = "BTCHFEIN", nullable = false)
	private int    fein;
	
	@Column(name = "BTCHFEUL", nullable = false)
	private     int feul;

	@Column(name = "BTCHFEPR", nullable = false)
	private     int fepr;

	public String getIden() {
		return iden;
	}

	public void setIden(String iden) {
		this.iden = iden;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getFeal() {
		return feal;
	}

	public void setFeal(int feal) {
		this.feal = feal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getOrde() {
		return orde;
	}

	public void setOrde(int orde) {
		this.orde = orde;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public int getFein() {
		return fein;
	}

	public void setFein(int fein) {
		this.fein = fein;
	}

	public int getFeul() {
		return feul;
	}

	public void setFeul(int feul) {
		this.feul = feul;
	}

	public int getFepr() {
		return fepr;
	}

	public void setFepr(int fepr) {
		this.fepr = fepr;
	}
}
