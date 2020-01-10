package es.ldrsoftware.core.btc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.arq.util.StringUtil;
import es.ldrsoftware.core.fwk.data.LiteData;

@Entity	
@Table(name = "MPLA")
public class Mpla extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -1486830618904555915L;

	@Id
	@Column(name = "MPLAHORA", nullable = false)
	private int    hora;
	
	public final static String HORA = "Hora de planificación";
	
	@Column(name = "MPLAESTA", nullable = false)
	private String esta;

	public final static String ESTA = "Estado de planificación";
	
	public String key() {
		return key(hora);
	}
	
	public final static String key(int hora) {
		return StringUtil.extend(hora, 4);
	}
	
	public void validate() throws Exception {
		validateFieldShortTime(hora, Mpla.HORA);
		validateFieldDomain(esta, Mpla.ESTA, LiteData.LT_ST_MPLAESTA);		
	}
	
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
