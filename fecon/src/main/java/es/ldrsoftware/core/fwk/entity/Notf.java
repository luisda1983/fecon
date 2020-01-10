package es.ldrsoftware.core.fwk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.fwk.data.LiteData;

@Entity	
@Table(name = "NOTF")
public class Notf extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -4044127667453491960L;

	@Id
	@Column(name = "NOTFIDEN", nullable = false)
	private String iden;
	
	public final static String IDEN = "Identificador de Notificación"; 
	
	@Column(name = "NOTFTIPO", nullable = false)
	private String tipo;
	
	public final static String TIPO = "Tipo de Notificación";
	
	@Column(name = "NOTFDESC", nullable = false)
	private String desc;

	public final static String DESC = "Descripción de Notificación";
	
	public String key() {
		return key(iden);
	}
	
	public final static String key(String iden) {
		return iden;
	}
	
	public void validate() throws Exception {
		validateFieldString(iden, 10, Notf.IDEN);
		validateFieldDomain(tipo, Notf.TIPO, LiteData.LT_ST_NOTFTIPO);
		validateFieldString(desc, 200, Notf.DESC);
	}
	
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
