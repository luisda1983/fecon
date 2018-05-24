package es.ldrsoftware.core.btc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity	
@Table(name = "LOGP")
public class Logp implements Serializable {

	private static final long serialVersionUID = 7434368930341937563L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "LOGPSECU", nullable = false)
	private long   secu;

	@Column(name = "LOGPTIPO", nullable = false)
	private String tipo;

	@Column(name = "LOGPIDEN", nullable = false)
	private String iden;
	
	@Column(name = "LOGPFECH", nullable = false)
	private int    fech;
	
	@Column(name = "LOGPHORA", nullable = false)
	private int    hora;

	@Column(name = "LOGPTABS", nullable = false)
	private int    tabs;
	
	@Column(name = "LOGPDATO", nullable = false)
	private String dato;

	public long getSecu() {
		return secu;
	}

	public void setSecu(long secu) {
		this.secu = secu;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIden() {
		return iden;
	}

	public void setIden(String iden) {
		this.iden = iden;
	}

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

	public int getTabs() {
		return tabs;
	}

	public void setTabs(int tabs) {
		this.tabs = tabs;
	}

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

}
