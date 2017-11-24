package es.ldrsoftware.core.sts.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity	
@Table(name = "STME")
@IdClass(StmePK.class)
public class Stme implements Serializable {

	private static final long serialVersionUID = 8498481307873855913L;

	@Id
	@Column(name = "STMEANYO", unique = true, nullable = false)
	private int anyo;

	@Id
	@Column(name = "STMEMESS", unique = true, nullable = false)
	private int mess;

	@Id
	@Column(name = "STMECTRL", unique = true, nullable = false)
	private String ctrl;
	
	@Column(name = "STMETOTA", nullable = false)
	private int tota;
	
	@Column(name = "STMETIME", nullable = false)
	private float time;
	
	@Column(name = "STMETIMA", nullable = false)
	private int tima;
	
	@Column(name = "STMETIMI", nullable = false)
	private int timi;
	
	@Column(name = "STMENUER", nullable = false)
	private int nuer;


	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public int getMess() {
		return mess;
	}

	public void setMess(int mess) {
		this.mess = mess;
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

class StmePK implements Serializable {

	private static final long serialVersionUID = -7310122972933975059L;

	@Id
	@Column(name = "STMEANYO", unique = true, nullable = false)
	private int anyo;

	@Id
	@Column(name = "STMEMESS", unique = true, nullable = false)
	private int mess;

	@Id
	@Column(name = "STMECTRL", unique = true, nullable = false)
	private String ctrl;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof StmePK) {
			StmePK obj = (StmePK)o;
			if (obj.anyo == this.anyo && obj.mess == this.mess && obj.ctrl.equals(this.ctrl)) {
				return true;
			}
		}
		return false;
	}
}