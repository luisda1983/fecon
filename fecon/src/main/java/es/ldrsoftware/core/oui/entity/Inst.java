package es.ldrsoftware.core.oui.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity	
@Table(name = "INST")
public class Inst implements Serializable {

	private static final long serialVersionUID = -283420044359494278L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "INSTIDEN", nullable = false)
	private long   iden;

	@Column(name = "INSTDESC", nullable = false)
	private String desc;

	@Column(name = "INSTFEAL", nullable = false)
	private int    feal;
	
	@Column(name = "INSTESTA", nullable = false)
	private String esta;
	
	@Column(name = "INSTFEUL", nullable = false)
	private int    feul;
	
	@Column(name = "INSTTIPO", nullable = false)
	private String tipo;
	
	@Column(name = "INSTFECA", nullable = false)
	private int    feca;

	@Column(name = "INSTCODI", nullable = false)
	private String codi;
	
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
