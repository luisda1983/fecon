package es.ldrsoftware.fecon.cnt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity	
@Table(name = "CUEN")
public class Cuen implements Serializable {

	private static final long serialVersionUID = 4431685998154671602L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CUENIDEN", nullable = false)
	private long   iden;

	@Column(name = "CUENINST", nullable = false)
	private long   inst;

	@Column(name = "CUENTIPO", nullable = false)
	private String tipo;
	
	@Column(name = "CUENDESC", nullable = false)
	private String desc;
	
	@Column(name = "CUENSALD", nullable = false)
	private double sald;
	
	@Column(name = "CUENFEAL", nullable = false)
	private int    feal;

	@Column(name = "CUENHOAL", nullable = false)
	private int    hoal;
	
	@Column(name = "CUENUSAL", nullable = false)
	private String usal;

	@Column(name = "CUENFEMO", nullable = false)
	private int    femo;

	@Column(name = "CUENHOMO", nullable = false)
	private int    homo;
	
	@Column(name = "CUENUSMO", nullable = false)
	private String usmo;

	public long getIden() {
		return iden;
	}

	public void setIden(long iden) {
		this.iden = iden;
	}

	public long getInst() {
		return inst;
	}

	public void setInst(long inst) {
		this.inst = inst;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getSald() {
		return sald;
	}

	public void setSald(double sald) {
		this.sald = sald;
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

	public String getUsal() {
		return usal;
	}

	public void setUsal(String usal) {
		this.usal = usal;
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
	
}
