package es.ldrsoftware.core.spt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity	
@Table(name = "DELE")
public class Dele implements Serializable {

	private static final long serialVersionUID = 970464119710684558L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "DELEIDEN", nullable = false)
	private long   iden;

	@Column(name = "DELEINST", nullable = false)
	private long   inst;

	@Column(name = "DELEDOMI", nullable = false)
	private String domi;
	
	@Column(name = "DELEVALO", nullable = false)
	private String valo;

	@Column(name = "DELEFEAL", nullable = false)
	private int    feal;

	@Column(name = "DELEHOAL", nullable = false)
	private int    hoal;

	@Column(name = "DELEUSAL", nullable = false)
	private String usal;
	
	@Column(name = "DELEFEMO", nullable = false)
	private int    femo;

	@Column(name = "DELEHOMO", nullable = false)
	private int    homo;
	
	@Column(name = "DELEUSMO", nullable = false)
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

	public String getDomi() {
		return domi;
	}

	public void setDomi(String domi) {
		this.domi = domi;
	}

	public String getValo() {
		return valo;
	}

	public void setValo(String valo) {
		this.valo = valo;
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
