package es.ldrsoftware.core.oui.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.fwk.data.LiteData;


@Entity	
@Table(name = "INVI")
public class Invi extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -5561438190935271112L;

	@Id
	@Column(name = "INVIIDEN", nullable = false)
	private String iden;

	public final static String IDEN = "Identificador de Invitaci�n";
	
	@Column(name = "INVITIPO", nullable = false)
	private String tipo;

	public final static String TIPO = "Tipo de Invitaci�n";
	
	@Column(name = "INVIESTA", nullable = false)
	private String esta;

	public final static String ESTA = "Estado de Invitaci�n";
	
	@Column(name = "INVIMAIL", nullable = false)
	private String mail;

	public final static String MAIL = "Email de Invitaci�n";
	
	@Column(name = "INVIINST", nullable = false)
	private long   inst;

	public final static String INST = "Instalaci�n vinculada a la Invitaci�n";
	
	@Column(name = "INVIUSUA", nullable = false)
	private String usua;

	public final static String USUA = "Usuario vinculado a la Invitaci�n";
	
	@Column(name = "INVIFEAL", nullable = false)
	private int    feal;

	public final static String FEAL = "Fecha de alta de Invitaci�n";
	
	@Column(name = "INVIHOAL", nullable = false)
	private int    hoal;

	public final static String HOAL = "Hora de alta de Invitaci�n";
	
	@Column(name = "INVIFEMO", nullable = false)
	private int    femo;

	public final static String FEMO = "Fecha de modificaci�n de Invitaci�n";
	
	@Column(name = "INVIHOMO", nullable = false)
	private int    homo;

	public final static String HOMO = "Hora de modificaci�n de Invitaci�n";
	
	public String key() {
		return key(iden);
	}
	
	public static String key(String iden) {
		return iden;
	}
	
	public void validate() throws Exception {
		validateFieldString(iden, 20, Inst.IDEN);
		validateFieldDomain(tipo, Inst.TIPO, LiteData.LT_ST_INVITIPO);
		validateFieldDomain(esta, Inst.ESTA, LiteData.LT_ST_INVIESTA);
		validateFieldString(mail, 100, Invi.MAIL);
		
		//Si la invitación es de tipo usuario, validamos que el identificador de instalación esté informado y dentro de rango
		if (LiteData.LT_EL_INVITIPO_USUARIO.equals(tipo)) {
			validateFieldLong(inst, 1, 999999999, Invi.INST);
		}

		//Si la invitación está finalizada, validamos que el identificador de instalación y el usuario estén informado y dentro de rango
		if (LiteData.LT_EL_INVIESTA_FINALIZADA.equals(esta)) {
			validateFieldLong(inst, 1, 999999999, Invi.INST);
			validateFieldString(usua, 30, Invi.USUA);
		} else {
			//Si la invitación no está finalizada y es de tipo instalación, no se permite el identificador de instalación
			if (LiteData.LT_EL_INVITIPO_INSTALACION.equals(tipo)) {
				validateFieldEmpty(inst, Invi.INST);
			}
			//Si la invitación no está finalizada, no se permite el usuario
			validateFieldEmpty(usua, Invi.USUA);
		}

		validateFieldDate(feal, Invi.FEAL);
		validateFieldTime(hoal, Invi.HOAL);
		
		//Si la invitación está enviada o solicitada, no se permite fecha y hora de modificación
		if (LiteData.LT_EL_INVIESTA_SOLICITADA.equals(esta) ||
			LiteData.LT_EL_INVIESTA_ENVIADA.equals(esta)) {
			validateFieldEmpty(femo, Invi.FEMO);
			validateFieldEmpty(homo, Invi.HOMO);
		} else {
		//Si la invitación no está enviada o solicitada, validamos que la fecha y hora de modificación estén informados y dentro de rango
			validateFieldDate(femo, Invi.FEMO);
			validateFieldTime(homo, Invi.HOMO);
		}
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

	public String getEsta() {
		return esta;
	}

	public void setEsta(String esta) {
		this.esta = esta;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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
	
}
