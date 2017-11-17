package es.ldrsoftware.core.btc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity	
@Table(name = "EJEC")
@IdClass(EjecPK.class)
public class Ejec implements Serializable {

	private static final long serialVersionUID = -5894490436685677229L;

	@Id
	@Column(name = "EJECFECH", unique = true, nullable = false)
	private    int fech;

	@Id
	@Column(name = "EJECBTCH", unique = true, nullable = false)
	private String btch;

	@Id
	@Column(name = "EJECSECU", nullable = false)
	private    int secu;
	
	@Column(name = "EJECORDE", nullable = false)
	private    int orde;
	
	@Column(name = "EJECESTA", nullable = false)
	private String esta;
	
	@Column(name = "EJECNOTF", nullable = false)
	private String notf;
	
	@Column(name = "EJECFEIN", nullable = false)
	private    int fein;

	@Column(name = "EJECHOIN", nullable = false)
	private    int hoin;
	
	@Column(name = "EJECFEFI", nullable = false)
	private    int fefi;
	
	@Column(name = "EJECHOFI", nullable = false)
	private    int hofi;
	
	@Column(name = "EJECTIEJ", nullable = false)
	private    int tiej;

	public int getFech() {
		return fech;
	}

	public void setFech(int fech) {
		this.fech = fech;
	}

	public String getBtch() {
		return btch;
	}

	public void setBtch(String btch) {
		this.btch = btch;
	}

	public int getSecu() {
		return secu;
	}

	public void setSecu(int secu) {
		this.secu = secu;
	}

	public int getOrde() {
		return orde;
	}

	public void setOrde(int orde) {
		this.orde = orde;
	}

	public String getEsta() {
		return esta;
	}

	public void setEsta(String esta) {
		this.esta = esta;
	}

	public String getNotf() {
		return notf;
	}

	public void setNotf(String notf) {
		this.notf = notf;
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

	public int getTiej() {
		return tiej;
	}

	public void setTiej(int tiej) {
		this.tiej = tiej;
	}
}

class EjecPK implements Serializable {

	private static final long serialVersionUID = -3586423640423543811L;

	@Id
	@Column(name = "EJECFECH", unique = true, nullable = false)
	private    int fech;

	@Id
	@Column(name = "EJECBTCH", unique = true, nullable = false)
	private String btch;

	@Id
	@Column(name = "EJECSECU", nullable = false)
	private    int secu;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof EjecPK) {
			EjecPK obj = (EjecPK)o;
			if (obj.fech == this.fech && obj.btch.equals(this.btch) && obj.secu == this.secu) {
				return true;
			}
		}
		return false;
	}
}