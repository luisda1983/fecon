package es.ldrsoftware.core.spt.entity;

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
@Table(name = "DOMI")
public class Domi extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -8420895147432768161L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "DOMIIDEN", nullable = false)
	private long   iden;

	public final static String IDEN = "Identificador de Dominio";
	
	@Column(name = "DOMIINST", nullable = false)
	private long   inst;

	public final static String INST = "Instalación de Dominio";
	
	@Column(name = "DOMINOMB", nullable = false)
	private String nomb;
	
	public final static String NOMB = "Nombre de Dominio";
	
	@Column(name = "DOMIDESC", nullable = false)
	private String desc;

	public final static String DESC = "Descripción de Dominio";
	
	@Column(name = "DOMIFEAL", nullable = false)
	private int    feal;

	public final static String FEAL = "Fecha de alta de Dominio";
	
	@Column(name = "DOMIHOAL", nullable = false)
	private int    hoal;

	public final static String HOAL = "Hora de alta de Dominio";
	
	@Column(name = "DOMIUSAL", nullable = false)
	private String usal;
	
	public final static String USAL = "Usuario de alta de Dominio";
	
	@Column(name = "DOMIFEMO", nullable = false)
	private int    femo;

	public final static String FEMO = "Fecha de modificación de Dominio";
	
	@Column(name = "DOMIHOMO", nullable = false)
	private int    homo;
	
	public final static String HOMO = "Hora de modificación de Dominio";
	
	@Column(name = "DOMIUSMO", nullable = false)
	private String usmo;

	public final static String USMO = "Usuario de modificación de Dominio";
	
	public String key() {
		return key(iden);
	}
	
	public final static String key(long iden) {
		return StringUtil.extend(iden, 10);
	}
	
	public void validate() throws Exception {
		validateFieldLong(inst, 0, 999999999, Domi.INST);
		validateFieldString(nomb, 50, Domi.NOMB);
		validateFieldString(desc, 50, Domi.DESC);
		validateFieldDate(feal, Domi.FEAL);
		validateFieldTime(hoal, Domi.HOAL);
		validateFieldString(usal, 30, Domi.USAL);
		validateFieldDate(femo, Domi.FEMO);
		validateFieldTime(homo, Domi.HOMO);
		validateFieldString(usmo, 30, Domi.USMO);
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

	public String getNomb() {
		return nomb;
	}

	public void setNomb(String nomb) {
		this.nomb = nomb;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
