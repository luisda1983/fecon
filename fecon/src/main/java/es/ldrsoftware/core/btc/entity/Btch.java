package es.ldrsoftware.core.btc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.data.ParaData;

@Entity	
@Table(name = "BTCH")
public class Btch extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -2534977557617460342L;

	@Id
	@Column(name = "BTCHIDEN", nullable = false)
	private String iden;

	public final static String IDEN = "Identificador de Batch";
	
	@Column(name = "BTCHDESC", nullable = false)
	private String desc;

	public final static String DESC = "Descripción de Batch";
	
	@Column(name = "BTCHFEAL", nullable = false)
	private int    feal;
	
	public final static String FEAL = "Fecha de alta de Batch";
	
	@Column(name = "BTCHESTA", nullable = false)
	private String esta;
	
	public final static String ESTA = "Estado de Batch";
	
	@Column(name = "BTCHAUTO", nullable = false)
	private String auto;
	
	public final static String AUTO = "Indicador de Batch de planificación automática";
	
	@Column(name = "BTCHDEPE", nullable = false)
	private String depe;

	public final static String DEPE = "Indicador de Batch con dependencias previas";
	
	@Column(name = "BTCHDBTC", nullable = false)
	private String dbtc;
	
	public final static String DBTC = "Batch de dependencia previa";
	
	@Column(name = "BTCHTIPO", nullable = false)
	private String tipo;

	public final static String TIPO = "Tipo de planificación de Batch";
	
	@Column(name = "BTCHAUX1", nullable = false)
	private int    aux1;
	
	public final static String AUX1 = "Dato auxiliar 1 para planificación";
	
	@Column(name = "BTCHAUX2", nullable = false)
	private int    aux2;
	
	public final static String AUX2 = "Dato auxiliar 2 para planificación";
	
	@Column(name = "BTCHAUX3", nullable = false)
	private int    aux3;
	
	public final static String AUX3 = "Dato auxiliar 3 para planificación";
	
	@Column(name = "BTCHORDE", nullable = false)
	private int    orde;
	
	public final static String ORDE = "Clave de orden de ejecución del Batch";
	
	@Column(name = "BTCHFEIN", nullable = false)
	private int    fein;
	
	public final static String FEIN = "Fecha de primera ejecución del Batch";
	
	@Column(name = "BTCHHOIN", nullable = false)
	private int    hoin;
	
	public final static String HOIN = "Hora de primera ejecución del Batch";
	
	@Column(name = "BTCHFEUL", nullable = false)
	private     int feul;

	public final static String FEUL = "Fecha de última ejecución del Batch";
	
	@Column(name = "BTCHHOUL", nullable = false)
	private int    houl;

	public final static String HOUL = "Hora de última ejecución del Batch";
	
	@Column(name = "BTCHFUPL", nullable = false)
	private int    fupl;
	
	public final static String FUPL = "Fecha de última planificación del Batch";
	
	@Column(name = "BTCHHUPL", nullable = false)
	private int    hupl;
	
	public final static String HUPL = "Hora de última planificación del Batch";
	
	public String key() {
		return key(iden);
	}
	
	public final static String key(String iden) {
		return iden;
	}
	
	public void validate() throws Exception {
		//Validamos que el identificador estÃ© informado y no sea demasiado largo
		validateFieldString(iden, 30, Btch.IDEN);
		validateFieldString(desc, 200, Btch.DESC);
		validateFieldDate(feal, Btch.FEAL);
		validateFieldDomain(esta, Btch.ESTA, LiteData.LT_ST_BTCHESTA);
		validateFieldBool(auto, Btch.AUTO);
		validateFieldBool(depe, Btch.DEPE);
		
		//Si el proceso tiene dependencia, el proceso del que depende debe estar informado y dentro de la longitud permitida
		if (LiteData.LT_EL_BOOL_SI.equals(depe)) {
			validateFieldString(dbtc, 30, Btch.DEPE);
		} else {
			validateFieldEmpty(dbtc, Btch.DBTC);
		}

		//Si el proceso es automÃ¡tico, validamos que el tipo de proceso estÃ© informado y tenga un valor permitido
		if (LiteData.LT_EL_BOOL_SI.equals(auto)) {
			validateFieldDomain(tipo, Btch.TIPO, ParaData.PM_ST_BTCHAVANCE);
		} else {
			validateFieldEmpty(tipo, Btch.TIPO);
		}

		validateFieldLong(orde, 1, 99999, Btch.ORDE);
	}
	
	public String getIden() {
		return iden;
	}

	public void setIden(String iden) {
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

	public String getAuto() {
		return auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}

	public String getDepe() {
		return depe;
	}

	public void setDepe(String depe) {
		this.depe = depe;
	}

	public String getDbtc() {
		return dbtc;
	}

	public void setDbtc(String dbtc) {
		this.dbtc = dbtc;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getAux1() {
		return aux1;
	}

	public void setAux1(int aux1) {
		this.aux1 = aux1;
	}

	public int getAux2() {
		return aux2;
	}

	public void setAux2(int aux2) {
		this.aux2 = aux2;
	}

	public int getAux3() {
		return aux3;
	}

	public void setAux3(int aux3) {
		this.aux3 = aux3;
	}

	public int getOrde() {
		return orde;
	}

	public void setOrde(int orde) {
		this.orde = orde;
	}

	public int getFein() {
		return fein;
	}

	public void setFein(int fein) {
		this.fein = fein;
	}

	public int getHoin() {
		return hoin;
	}

	public void setHoin(int hoin) {
		this.hoin = hoin;
	}

	public int getFeul() {
		return feul;
	}

	public void setFeul(int feul) {
		this.feul = feul;
	}

	public int getHoul() {
		return houl;
	}

	public void setHoul(int houl) {
		this.houl = houl;
	}

	public int getFupl() {
		return fupl;
	}

	public void setFupl(int fupl) {
		this.fupl = fupl;
	}

	public int getHupl() {
		return hupl;
	}

	public void setHupl(int hupl) {
		this.hupl = hupl;
	}

}
