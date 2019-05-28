package es.ldrsoftware.core.spt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity	
@Table(name = "DOMI")
public class Domi implements Serializable {

	private static final long serialVersionUID = -8420895147432768161L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "DOMIIDEN", nullable = false)
	private long   iden;

	@Column(name = "DOMIINST", nullable = false)
	private long   inst;

	@Column(name = "DOMINOMB", nullable = false)
	private String nomb;
	
	@Column(name = "DOMIDESC", nullable = false)
	private String desc;

	@Column(name = "DOMIFEAL", nullable = false)
	private int    feal;

	@Column(name = "DOMIHOAL", nullable = false)
	private int    hoal;

	@Column(name = "DOMIUSAL", nullable = false)
	private String usal;
	
	@Column(name = "DOMIFEMO", nullable = false)
	private int    femo;

	@Column(name = "DOMIHOMO", nullable = false)
	private int    homo;
	
	@Column(name = "DOMIUSMO", nullable = false)
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

	public String getNomb() {
		return nomb;
	}

	public void setNomb(String nomb) {
		this.nomb = nomb;
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
