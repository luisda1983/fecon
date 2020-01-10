package es.ldrsoftware.core.fwk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.arq.util.StringUtil;


@Entity	
@Table(name = "AVIS")
public class Avis extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -2733611135262428879L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "AVISIDEN", nullable = false)
	private long  iden;

	public final static String IDEN = "Identificador de aviso";
	
	@Column(name = "AVISDEST", nullable = false)
	private String dest;
	
	public final static String DEST = "Destinatario de aviso";
	
	@Column(name = "AVISTIPO", nullable = false)
	private String tipo;

	public final static String TIPO = "Tipo de aviso";
	
	@Column(name = "AVISINST", nullable = false)
	private long   inst;

	public final static String INST = "Instalación de aviso";
	
	@Column(name = "AVISUSUA", nullable = false)
	private String usua;
	
	public final static String USUA = "Usuario al que va dirigido el aviso";
	
	@Column(name = "AVISPERF", nullable = false)
	private String perf;
	
	public final static String PERF = "Perfil al que va dirigido el aviso";
	
	@Column(name = "AVISESTA", nullable = false)
	private String esta;
	
	public final static String ESTA = "Estado del aviso";
	
	@Column(name = "AVISDESC", nullable = false)
	private String desc;
	
	public final static String DESC = "Descripción del aviso";
	
	@Column(name = "AVISWKFL", nullable = false)
	private long   wkfl;
	
	public final static String WKFL = "NPI";
	
	@Column(name = "AVISFEAL", nullable = false)
	private int    feal;
	
	public final static String FEAL = "Fecha de alta del aviso";
	
	@Column(name = "AVISHOAL", nullable = false)
	private int    hoal;
	
	public final static String HOAL = "Hora de alta del aviso";
	
	@Column(name = "AVISFECA", nullable = false)
	private int    feca;

	public final static String FECA = "Fecha de canducidad del aviso";
	
	public String key() {
		return key(iden);
	}
	
	public final static String key(long iden) {
		return StringUtil.extend(iden, 10);
	}
	
	public void validate() throws Exception {
		
	}
	
	public long getIden() {
		return iden;
	}

	public void setIden(long iden) {
		this.iden = iden;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public long getInst() {
		return inst;
	}

	public void setInst(long inst) {
		this.inst = inst;
	}

	public String getUsua() {
		return usua;
	}

	public void setUsua(String usua) {
		this.usua = usua;
	}

	public String getPerf() {
		return perf;
	}

	public void setPerf(String perf) {
		this.perf = perf;
	}

	public String getEsta() {
		return esta;
	}

	public void setEsta(String esta) {
		this.esta = esta;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getWkfl() {
		return wkfl;
	}

	public void setWkfl(long wkfl) {
		this.wkfl = wkfl;
	}

	public int getFeal() {
		return feal;
	}

	public void setFeal(int feal) {
		this.feal = feal;
	}

	public int getHoal() {
		return hoal;
	}

	public void setHoal(int hoal) {
		this.hoal = hoal;
	}

	public int getFeca() {
		return feca;
	}

	public void setFeca(int feca) {
		this.feca = feca;
	}

}
