package ldrsoftware.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "THCONC")
@IdClass(HConcPK.class)
public class HConc {

	@Id
	@Column(name = "CONCINST", nullable = false)
	private long inst;
	
	@Id
	@Column(name = "CONCIDEN", nullable = false)
	private int iden;
	
	@Column(name = "CONCCATE", nullable = false)
	private int cate;
	
	@Column(name = "CONCTIPO", nullable = false)
	private String tipo;
	
	@Column(name = "CONCDESC", nullable = false)
	private String desc;
	
	@Column(name = "CONCDOMI", nullable = false)
	private int domi;
	
	@Column(name = "CONCORDE", nullable = false)
	private int orde;
	
	@Column(name = "CONCACTI", nullable = false)
	private boolean acti;
	
	public long getInst() {
		return inst;
	}

	public void setInst(long inst) {
		this.inst = inst;
	}

	public int getIden() {
		return iden;
	}

	public void setIden(int iden) {
		this.iden = iden;
	}

	public int getCate() {
		return cate;
	}

	public void setCate(int cate) {
		this.cate = cate;
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

	public int getDomi() {
		return domi;
	}

	public void setDomi(int domi) {
		this.domi = domi;
	}

	public int getOrde() {
		return orde;
	}

	public void setOrde(int orde) {
		this.orde = orde;
	}

	public boolean isActi() {
		return acti;
	}

	public void setActi(boolean acti) {
		this.acti = acti;
	}

	@Override
	public String toString() {
		return "Conc [inst=" + inst + ", "
				   + "iden=" + iden + ", "
				   + "cate=" + cate + ", "
				   + "tipo=" + tipo + ", "
				   + "desc=" + desc + ", "
				   + "domi=" + domi + ", "
				   + "orde=" + orde + ", "
				   + "acti=" + acti + ", "
				   ;
	}		
}

class HConcPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CONCINST", nullable = false)
	private long inst;
	
	@Id
	@Column(name = "CONCIDEN", nullable = false)
	private int iden;

	public long getInst() {
		return inst;
	}

	public void setInst(long inst) {
		this.inst = inst;
	}

	public int getIden() {
		return iden;
	}

	public void setIden(int iden) {
		this.iden = iden;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof HConcPK) {
			HConcPK obj = (HConcPK)o;
			if (obj.getInst() == this.inst && obj.getIden() == this.iden) {
				return true;
			}
		}
		return false;
	}
}