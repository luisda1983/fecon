package es.ldrsoftware.fecon.cnt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.arq.util.StringUtil;
import es.ldrsoftware.fecon.data.LiteData;

@Entity	
@Table(name = "CUEN")
public class Cuen extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 4431685998154671602L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CUENIDEN", nullable = false)
	private long   iden;

	public final static String IDEN = "Identificador de Cuenta";
	
	@Column(name = "CUENINST", nullable = false)
	private long   inst;

	public final static String INST = "Instalación de Cuenta";
	
	@Column(name = "CUENTIPO", nullable = false)
	private String tipo;
	
	public final static String TIPO = "Tipo de Cuenta";
	
	@Column(name = "CUENDESC", nullable = false)
	private String desc;
	
	public final static String DESC = "Descripción de Cuenta";
	
	@Column(name = "CUENSALD", nullable = false)
	private double sald;
	
	public final static String SALD = "Saldo de Cuenta";
	
	@Column(name = "CUENFEAL", nullable = false)
	private int    feal;

	public final static String FEAL = "Fecha de alta de Cuenta";
	
	@Column(name = "CUENHOAL", nullable = false)
	private int    hoal;
	
	public final static String HOAL = "Hora de alta de Cuenta";
	
	@Column(name = "CUENUSAL", nullable = false)
	private String usal;

	public final static String USAL = "Usuario de alta de Cuenta";
	
	@Column(name = "CUENFEMO", nullable = false)
	private int    femo;

	public final static String FEMO = "Fecha de modificación de Cuenta";
	
	@Column(name = "CUENHOMO", nullable = false)
	private int    homo;
	
	public final static String HOMO = "Hora de modificación de Cuenta";
	
	@Column(name = "CUENUSMO", nullable = false)
	private String usmo;

	public final static String USMO = "Usuario de modificación de Cuenta";
	
	public final static String CTOR = "Cuenta de origen";
	public final static String CTDE = "Cuenta de destino";
	
	public String key() {
		return key(iden);
	}
	
	public final static String key(long iden) {
		return StringUtil.extend(iden, 10);
	}
	
	public void validate() throws Exception {
		validateFieldLong(iden, 0, 999999999, Cuen.IDEN);
		validateFieldLong(inst, 0, 999999999, Cuen.INST);
		validateFieldDomain(tipo, Cuen.TIPO, LiteData.LT_ST_CUENTIPO);
		validateFieldString(desc, 100, Cuen.DESC);
		validateFieldDouble(sald, -99999999999.99, 99999999999.99, Cuen.SALD);
		validateFieldDate(feal, Cuen.FEAL);
		validateFieldTime(hoal, Cuen.HOAL);
		validateFieldString(usal, 30, Cuen.USAL);
		validateFieldDate(femo, Cuen.FEMO);
		validateFieldTime(homo, Cuen.HOMO);
		validateFieldString(usmo, 30, Cuen.USMO);
	}
	
	public long getIden() {
		return iden;
	}

	public void setIden(long iden) {
		this.iden = iden;
	}

	public long getInst() {
		return inst;
	}

	public void setInst(long inst) {
		this.inst = inst;
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

	public double getSald() {
		return sald;
	}

	public void setSald(double sald) {
		this.sald = sald;
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

	public String getUsal() {
		return usal;
	}

	public void setUsal(String usal) {
		this.usal = usal;
	}

	public int getFemo() {
		return femo;
	}

	public void setFemo(int femo) {
		this.femo = femo;
	}

	public int getHomo() {
		return homo;
	}

	public void setHomo(int homo) {
		this.homo = homo;
	}

	public String getUsmo() {
		return usmo;
	}

	public void setUsmo(String usmo) {
		this.usmo = usmo;
	}
}
