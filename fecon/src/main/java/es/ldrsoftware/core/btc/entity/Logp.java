package es.ldrsoftware.core.btc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.arq.util.StringUtil;
import es.ldrsoftware.core.fwk.data.LiteData;


@Entity	
@Table(name = "LOGP")
public class Logp extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 7434368930341937563L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "LOGPSECU", nullable = false)
	private long   secu;

	public final static String SECU = "Secuencia de Log";
	
	@Column(name = "LOGPTIPO", nullable = false)
	private String tipo;

	public final static String TIPO = "Tipo de Log";
	
	@Column(name = "LOGPIDEN", nullable = false)
	private String iden;
	
	public final static String IDEN = "Identificador de Log";
	
	@Column(name = "LOGPFECH", nullable = false)
	private int    fech;
	
	public final static String FECH = "Fecha de Log";
	
	@Column(name = "LOGPHORA", nullable = false)
	private int    hora;

	public final static String HORA = "Hora de Log";
	
	@Column(name = "LOGPTABS", nullable = false)
	private int    tabs;
	
	public final static String TABS = "Tabulación de Log";
	
	@Column(name = "LOGPDATO", nullable = false)
	private String dato;

	public final static String DATO = "Datos de Log";
	
	public String key() {
		return key(secu);
	}
	
	public final static String key(long secu) {
		return StringUtil.extend(secu, 10);
	}
	
	public void validate() throws Exception {
		validateFieldDomain(tipo, Logp.TIPO, LiteData.LT_ST_LOGPTIPO);
		validateFieldString(iden, 30, Logp.IDEN);
		validateFieldDate(fech, Logp.FECH);
		validateFieldShortTime(hora, Logp.HORA);
		validateFieldLong(tabs, 0, 9, Logp.TABS);
		validateFieldString(dato, 500, Logp.DATO);				
	}
	
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
