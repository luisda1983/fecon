package es.ldrsoftware.core.sts.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity	
@Table(name = "STDI")
@IdClass(StdiPK.class)
public class Stdi implements Serializable {

	private static final long serialVersionUID = -645928105535579829L;

	@Id
	@Column(name = "STDIFECH", unique = true, nullable = false)
	private int fech;

	@Id
	@Column(name = "STDICTRL", unique = true, nullable = false)
	private String ctrl;
	
	@Column(name = "STDITOTA", nullable = false)
	private int tota;
	
	@Column(name = "STDITIME", nullable = false)
	private float time;
	
	@Column(name = "STDITIMA", nullable = false)
	private int tima;
	
	@Column(name = "STDITIMI", nullable = false)
	private int timi;
	
	@Column(name = "STDINUER", nullable = false)
	private int nuer;

	public int getFech() {
		return fech;
	}

	public void setFech(int fech) {
		this.fech = fech;
	}

	public String getCtrl() {
		return ctrl;
	}

	public void setCtrl(String ctrl) {
		this.ctrl = ctrl;
	}

	public int getTota() {
		return tota;
	}

	public void setTota(int tota) {
		this.tota = tota;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public int getTima() {
		return tima;
	}

	public void setTima(int tima) {
		this.tima = tima;
	}

	public int getTimi() {
		return timi;
	}

	public void setTimi(int timi) {
		this.timi = timi;
	}

	public int getNuer() {
		return nuer;
	}

	public void setNuer(int nuer) {
		this.nuer = nuer;
	}
}

class StdiPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STDIFECH", unique = true, nullable = false)
	private int fech;

	@Id
	@Column(name = "STDICTRL", unique = true, nullable = false)
	private String ctrl;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof StdiPK) {
			StdiPK obj = (StdiPK)o;
			if (obj.fech == this.fech && obj.ctrl.equals(this.ctrl)) {
				return true;
			}
		}
		return false;
	}
}