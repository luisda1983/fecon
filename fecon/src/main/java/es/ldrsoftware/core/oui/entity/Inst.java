package es.ldrsoftware.core.oui.entity;

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
@Table(name = "INST")
public class Inst extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -283420044359494278L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "INSTIDEN", nullable = false)
	private long   iden;

	public final static String IDEN = "Identificador de Instalación";
	
	@Column(name = "INSTDESC", nullable = false)
	private String desc;

	public final static String DESC = "Descripción de Instalación";
	
	@Column(name = "INSTFEAL", nullable = false)
	private int    feal;

	public final static String FEAL = "Fecha de alta de Instalación";
	
	@Column(name = "INSTESTA", nullable = false)
	private String esta;
	
	public final static String ESTA = "Estado de Instalación";
	
	@Column(name = "INSTFEUL", nullable = false)
	private int    feul;
	
	public final static String FEUL = "Fecha de último acceso a instalación";
	
	@Column(name = "INSTTIPO", nullable = false)
	private String tipo;
	
	public final static String TIPO = "Tipo de instalación";
	
	@Column(name = "INSTFECA", nullable = false)
	private int    feca;

	public final static String FECA = "Fecha de caducidad de instalación";
	
	@Column(name = "INSTCODI", nullable = false)
	private String codi;
	
	public final static String CODI = "Código de instalación";
	
	public final static String NUMO = "Indicador usuario nuevo o existente";
	
	public String key() {
		return key(iden);
	}
	
	public static String key(long iden) {
		return StringUtil.extend(iden, 10);
	}
	
	public static String keyCodi(String codi) {
		return codi;
	}
	
	public void validate() throws Exception {
		validateFieldString(desc, 50, DESC);
		validateFieldDate(feal, FEAL);
		validateFieldDomain(esta, ESTA, LiteData.LT_ST_INSTESTA);
		validateFieldDate(feul, FEUL);
		validateFieldDomain(tipo, TIPO, LiteData.LT_ST_INSTTIPO);

		//Si el tipo de instalaciÃ³n es PREMIUM, validamos que la fecha de caducidad estÃ© informada y dentro del rango permitido
		if (LiteData.LT_EL_INSTTIPO_PREMIUM.equals(tipo)) {
			validateFieldDate(feca, FECA);
		}							
	}
	
	public long getIden() {
		return iden;
	}

	public void setIden(long iden) {
		this.iden = iden;
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

	public String getEsta() {
		return esta;
	}

	public void setEsta(String esta) {
		this.esta = esta;
	}

	public int getFeul() {
		return feul;
	}

	public void setFeul(int feul) {
		this.feul = feul;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getFeca() {
		return feca;
	}

	public void setFeca(int feca) {
		this.feca = feca;
	}

	public String getCodi() {
		return codi;
	}
	
	public void setCodi(String codi) {
		this.codi = codi;
	}
}
