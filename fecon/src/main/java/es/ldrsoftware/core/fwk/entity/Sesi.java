package es.ldrsoftware.core.fwk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity	
@Table(name = "SESI")
public class Sesi implements Serializable {

	private static final long serialVersionUID = -3070323939097781437L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "SESIIDEN", nullable = false)
	private long  iden;
	
	@Column(name = "SESIUSUA", nullable = false)
	private String usua;
	
	@Column(name = "SESICLAV", nullable = false)
	private long   clav;
	
	@Column(name = "SESIDIIP", nullable = false)
	private String diip;
	
	@Column(name = "SESIPERF", nullable = false)
	private String perf;

	@Column(name = "SESIDVCE", nullable = false)
	private String dvce;
	
	@Column(name = "SESIINST", nullable = false)
	private long   inst;
	
	@Column(name = "SESIFEAP", nullable = false)
	private int    feap;
	
	@Column(name = "SESIHOAP", nullable = false)
	private int    hoap;
	
	@Column(name = "SESIESTA", nullable = false)
	private String esta;

	@Column(name = "SESIFEUL", nullable = false)
	private int    feul;
	
	@Column(name = "SESIHOUL", nullable = false)
	private int    houl;

	@Column(name = "SESIFERE", nullable = false)
	private int    fere;
	
	@Column(name = "SESIHORE", nullable = false)
	private int    hore;

	@Column(name = "SESIFECA", nullable = false)
	private int    feca;
	
	@Column(name = "SESIHOCA", nullable = false)
	private int    hoca;

	public long getIden() {
		return iden;
	}

	public void setIden(long iden) {
		this.iden = iden;
	}

	public String getUsua() {
		return usua;
	}

	public void setUsua(String usua) {
		this.usua = usua;
	}

	public long getClav() {
		return clav;
	}
	
	public void setClav(long clav) {
		this.clav = clav;
	}
	
	public String getDiip() {
		return diip;
	}

	public void setDiip(String diip) {
		this.diip = diip;
	}

	public String getPerf() {
		return perf;
	}

	public void setPerf(String perf) {
		this.perf = perf;
	}

	public String getDvce() {
		return dvce;
	}

	public void setDvce(String dvce) {
		this.dvce = dvce;
	}

	public long getInst() {
		return inst;
	}

	public void setInst(long inst) {
		this.inst = inst;
	}

	public int getFeap() {
		return feap;
	}

	public void setFeap(int feap) {
		this.feap = feap;
	}

	public int getHoap() {
		return hoap;
	}

	public void setHoap(int hoap) {
		this.hoap = hoap;
	}

	public String getEsta() {
		return esta;
	}

	public void setEsta(String esta) {
		this.esta = esta;
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

	public int getFere() {
		return fere;
	}
	
	public void setFere(int fere) {
		this.fere = fere;
	}
	
	public int getHore() {
		return hore;
	}
	
	public void setHore(int hore) {
		this.hore = hore;
	}
	
	public int getFeca() {
		return feca;
	}

	public void setFeca(int feca) {
		this.feca = feca;
	}

	public int getHoca() {
		return hoca;
	}

	public void setHoca(int hoca) {
		this.hoca = hoca;
	}

}
