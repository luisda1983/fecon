package es.ldrsoftware.core.btc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity	
@Table(name = "BTCH")
public class Btch implements Serializable {

	private static final long serialVersionUID = -2534977557617460342L;

	@Id
	@Column(name = "BTCHIDEN", nullable = false)
	private String iden;
	
	@Column(name = "BTCHDESC", nullable = false)
	private String desc;
	
	@Column(name = "BTCHFEAL", nullable = false)
	private int    feal;
	
	@Column(name = "BTCHESTA", nullable = false)
	private String esta;
	
	@Column(name = "BTCHAUTO", nullable = false)
	private String auto;
	
	@Column(name = "BTCHDEPE", nullable = false)
	private String depe;

	@Column(name = "BTCHDBTC", nullable = false)
	private String dbtc;
	
	@Column(name = "BTCHTIPO", nullable = false)
	private String tipo;

	@Column(name = "BTCHAUX1", nullable = false)
	private int    aux1;
	
	@Column(name = "BTCHAUX2", nullable = false)
	private int    aux2;
	
	@Column(name = "BTCHAUX3", nullable = false)
	private int    aux3;
	
	@Column(name = "BTCHORDE", nullable = false)
	private int    orde;
	
	@Column(name = "BTCHFEIN", nullable = false)
	private int    fein;
	
	@Column(name = "BTCHHOIN", nullable = false)
	private int    hoin;
	
	@Column(name = "BTCHFEUL", nullable = false)
	private     int feul;

	@Column(name = "BTCHHOUL", nullable = false)
	private int    houl;

	@Column(name = "BTCHFUPL", nullable = false)
	private int    fupl;
	
	@Column(name = "BTCHHUPL", nullable = false)
	private int    hupl;
	
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
