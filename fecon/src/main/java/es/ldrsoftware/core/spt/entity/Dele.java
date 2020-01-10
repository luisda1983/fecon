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
@Table(name = "DELE")
public class Dele extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 970464119710684558L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "DELEIDEN", nullable = false)
	private long   iden;

	public final static String IDEN = "Identificador de elemento de dominio";
	
	@Column(name = "DELEINST", nullable = false)
	private long   inst;

	public final static String INST = "Instalación de elemento de dominio";
	
	@Column(name = "DELEDOMI", nullable = false)
	private String domi;
	
	public final static String DOMI = "Dominio de elemento";
	
	@Column(name = "DELEVALO", nullable = false)
	private String valo;

	public final static String VALO = "Valor de elemento de dominio";
	
	@Column(name = "DELEFEAL", nullable = false)
	private int    feal;

	public final static String FEAL = "Fecha de alta de elemento de dominio";
	
	@Column(name = "DELEHOAL", nullable = false)
	private int    hoal;

	public final static String HOAL = "Hora de alta de elemento de dominio";
	
	@Column(name = "DELEUSAL", nullable = false)
	private String usal;
	
	public final static String USAL = "Usuario de alta de elemento de dominio";
	
	@Column(name = "DELEFEMO", nullable = false)
	private int    femo;

	public final static String FEMO = "Fecha de modificación de elemento de dominio";
	
	@Column(name = "DELEHOMO", nullable = false)
	private int    homo;
	
	public final static String HOMO = "Hora de modificación de elemento de dominio";
	
	@Column(name = "DELEUSMO", nullable = false)
	private String usmo;

	public final static String USMO = "Usuario de modificación de elemento de dominio";
	
	public String key() {
		return key(iden);
	}
	
	public final static String key(long iden) {
		return StringUtil.extend(iden, 10);
	}

	public void validate() throws Exception {
		validateFieldLong(inst, 0, 999999999, Dele.INST);
		validateFieldString(domi, 10, Dele.DOMI);
		validateFieldString(valo, 50, Dele.VALO);
		validateFieldDate(feal, Dele.FEAL);
		validateFieldTime(hoal, Dele.HOAL);
		validateFieldString(usal, 30, Dele.USAL);
		validateFieldDate(femo, Dele.FEMO);
		validateFieldTime(homo, Dele.HOMO);
		validateFieldString(usmo, 30, Dele.USMO);
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

	public String getDomi() {
		return domi;
	}

	public void setDomi(String domi) {
		this.domi = domi;
	}

	public String getValo() {
		return valo;
	}

	public void setValo(String valo) {
		this.valo = valo;
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
