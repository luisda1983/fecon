package es.ldrsoftware.core.fwk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity	
@Table(name = "NOTF")
public class Notf implements Serializable {

	private static final long serialVersionUID = -4044127667453491960L;

	@Id
	@Column(name = "NOTFIDEN", nullable = false)
	private String iden;
	
	@Column(name = "NOTFTIPO", nullable = false)
	private String tipo;
	
	@Column(name = "NOTFDESC", nullable = false)
	private String desc;

	public String getIden() {
		return iden;
	}

	public void setIden(String iden) {
		this.iden = iden;
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

}
