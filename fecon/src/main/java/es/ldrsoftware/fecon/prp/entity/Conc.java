package es.ldrsoftware.fecon.prp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity	
@Table(name = "CONC")
public class Conc implements Serializable {

	private static final long serialVersionUID = 1383027102789571754L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CONCIDEN", nullable = false)
	private long   iden;

	@Column(name = "CONCCATE", nullable = false)
	private long   cate;
	
	@Column(name = "CONCINST", nullable = false)
	private long   inst;

	@Column(name = "CONCTIPO", nullable = false)
	private String tipo;
	
	@Column(name = "CONCDESL", nullable = false)
	private String desl;
	
	@Column(name = "CONCDESC", nullable = false)
	private String desc;
	
	@Column(name = "CONCORDE", nullable = false)
	private int    orde;

	public long getIden() {
		return iden;
	}

	public void setIden(long iden) {
		this.iden = iden;
	}

	public long getCate() {
		return cate;
	}
	
	public void setCate(long cate) {
		this.cate = cate;
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
	
	public String getDesl() {
		return desl;
	}

	public void setDesl(String desl) {
		this.desl = desl;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getOrde() {
		return orde;
	}

	public void setOrde(int orde) {
		this.orde = orde;
	}
		
}
