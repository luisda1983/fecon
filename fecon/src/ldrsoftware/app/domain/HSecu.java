package ldrsoftware.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "THSECU")
@IdClass(HSecuPK.class)
public class HSecu {

	@Id
	@Column(name = "SECUINST", nullable = false)
	private long inst;
	
	@Id
	@Column(name = "SECUIDEN", nullable = false)
	private String iden;

	@Column(name = "SECUTIPO", nullable = false)
	private String tipo;
	
	@Column(name = "SECUVALO", nullable = false)
	private int valo;
	
	public long getInst() {
		return inst;
	}

	public void setInst(long inst) {
		this.inst = inst;
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

	public int getValo() {
		return valo;
	}

	public void setValo(int valo) {
		this.valo = valo;
	}

	@Override
	public String toString() {
		return "Secu [inst=" + inst + ", "
				   + "iden=" + iden + ", "
				   + "tipo=" + tipo + ", "
				   + "valo=" + valo + ", "
				   ;
	}		
}

class HSecuPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "SECUINST", nullable = false)
	private long inst;
	
	@Id
	@Column(name = "SECUIDEN", nullable = false)
	private String iden;

	public long getInst() {
		return inst;
	}

	public void setInst(long inst) {
		this.inst = inst;
	}

	public String getIden() {
		return iden;
	}

	public void setIden(String iden) {
		this.iden = iden;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof HSecuPK) {
			HSecuPK obj = (HSecuPK)o;
			if (obj.getInst() == this.inst && this.iden.equals(obj.getClass())) {
				return true;
			}
		}
		return false;
	}
}