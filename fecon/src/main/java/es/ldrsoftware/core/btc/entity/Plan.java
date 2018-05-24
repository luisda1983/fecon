package es.ldrsoftware.core.btc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity	
@Table(name = "PLAN")
@IdClass(PlanPK.class)
public class Plan implements Serializable {

	private static final long serialVersionUID = 4470288665160191135L;

	@Id
	@Column(name = "PLANFECH", nullable = false)
	private int    fech;

	@Id
	@Column(name = "PLANHORA", nullable = false)
	private int    hora;
	
	@Column(name = "PLANFBTC", nullable = false)
	private int    fbtc;
	
	@Column(name = "PLANESTA", nullable = false)
	private String esta;
	
	@Column(name = "PLANFEIN", nullable = false)
	private int    fein;
	
	@Column(name = "PLANHOIN", nullable = false)
	private int    hoin;
	
	@Column(name = "PLANFEFI", nullable = false)
	private int    fefi;
	
	@Column(name = "PLANHOFI", nullable = false)
	private int    hofi;
	
	@Column(name = "PLANPROC", nullable = false)
	private int    proc;

	@Column(name = "PLANVENT", nullable = false)
	private int    vent;

	@Column(name = "PLANPORC", nullable = false)
	private double porc;

	public int getFech() {
		return fech;
	}

	public void setFech(int fech) {
		this.fech = fech;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getFbtc() {
		return fbtc;
	}

	public void setFbtc(int fbtc) {
		this.fbtc = fbtc;
	}

	public String getEsta() {
		return esta;
	}

	public void setEsta(String esta) {
		this.esta = esta;
	}

	public int getFein() {
		return fein;
	}

	public void setFein(int fein) {
		this.fein = fein;
	}

	public int getHoin() {
		return hoin;
	}

	public void setHoin(int hoin) {
		this.hoin = hoin;
	}

	public int getFefi() {
		return fefi;
	}

	public void setFefi(int fefi) {
		this.fefi = fefi;
	}

	public int getHofi() {
		return hofi;
	}

	public void setHofi(int hofi) {
		this.hofi = hofi;
	}

	public int getProc() {
		return proc;
	}

	public void setProc(int proc) {
		this.proc = proc;
	}

	public int getVent() {
		return vent;
	}

	public void setVent(int vent) {
		this.vent = vent;
	}

	public double getPorc() {
		return porc;
	}

	public void setPorc(double porc) {
		this.porc = porc;
	}
	
}

class PlanPK implements Serializable {

	private static final long serialVersionUID = 487779799474379307L;

	@Id
	@Column(name = "PLANFECH", unique = true, nullable = false)
	private int    fech;

	@Id
	@Column(name = "PLANHORA", unique = true, nullable = false)
	private int    hora;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof PlanPK) {
			PlanPK obj = (PlanPK)o;
			if (obj.fech == this.fech && obj.hora == this.hora) {
				return true;
			}
		}
		return false;
	}
}