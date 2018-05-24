package es.ldrsoftware.core.btc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity	
@Table(name = "MPLA")
public class Mpla implements Serializable {

	private static final long serialVersionUID = -1486830618904555915L;

	@Id
	@Column(name = "MPLAHORA", nullable = false)
	private int    hora;
	
	@Column(name = "MPLAESTA", nullable = false)
	private String esta;

	public int getHora() {
		return hora;
	}
	
	public void setHora(int hora) {
		this.hora = hora;
	}
	
	public String getEsta() {
		return esta;
	}

	public void setEsta(String esta) {
		this.esta = esta;
	}
}
