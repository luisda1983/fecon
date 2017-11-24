package ldrsoftware.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "THHCON")
@IdClass(HHconPK.class)
public class HHcon {

	@Id
	@Column(name = "HCONINST", nullable = false)
	private long inst;
	
	@Id
	@Column(name = "HCONIDEN", nullable = false)
	private int iden;
	
	@Column(name = "HCONCUEN", nullable = false)
	private int cuen;
	
	@Column(name = "HCONTIPO", nullable = false)
	private String tipo;
	
	@Column(name = "HCONFOPE", nullable = false)
	private int fope;
	
	@Column(name = "HCONHOPE", unique = true, nullable = false)
	private int hope;
	
	@Column(name = "HCONFVAL", nullable = false)
	private int fval;
	
	@Column(name = "HCONCONC", nullable = false)
	private int conc;

	@Column(name = "HCONCATE", nullable = false)
	private int cate;
	
	@Column(name = "HCONIMPO", nullable = false)
	private double impo;
	
	@Column(name = "HCONADIC", nullable = false)
	private String adic;
	
	@Column(name = "HCONDESC", nullable = false)
	private String desc;
	
	@Column(name = "HCONUSUA", nullable = false)
	private String usua;
	
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

	public int getCuen() {
		return cuen;
	}

	public void setCuen(int cuen) {
		this.cuen = cuen;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getFope() {
		return fope;
	}

	public void setFope(int fope) {
		this.fope = fope;
	}

	public int getHope() {
		return hope;
	}

	public void setHope(int hope) {
		this.hope = hope;
	}

	public int getFval() {
		return fval;
	}

	public void setFval(int fval) {
		this.fval = fval;
	}

	public int getCate() {
		return cate;
	}
	
	public void setCate(int cate) {
		this.cate = cate;
	}
	
	public int getConc() {
		return conc;
	}

	public void setConc(int conc) {
		this.conc = conc;
	}

	public double getImpo() {
		return impo;
	}

	public void setImpo(double impo) {
		this.impo = impo;
	}

	public String getAdic() {
		return adic;
	}

	public void setAdic(String adic) {
		this.adic = adic;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUsua() {
		return usua;
	}

	public void setUsua(String usua) {
		this.usua = usua;
	}

	@Override
	public String toString() {
		return "Hcon [iden=" + iden + ", "
				   + "cuen=" + cuen + ", "
				   + "tipo=" + tipo + ", "
				   + "fope=" + fope + ", "
				   + "hope=" + hope + ", "
				   + "fval=" + fval + ", "
				   + "conc=" + conc + ", "
				   + "impo=" + impo + ", "
				   + "adic=" + adic + ", "
				   + "desc=" + desc + ", "
				   + "usua=" + usua + ", "
				   ;
	}
	
}

class HHconPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	@Id
	@Column(name = "HCONINST", unique = true, nullable = false)
	private long inst;
		
	@Id
	@Column(name = "HCONIDEN", unique = true, nullable = false)
	private int iden;

	public long getInst() {
		return this.inst;
	}
		
	public void setInst(int inst) {
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
		if (o instanceof HHconPK ) {
			HHconPK obj = (HHconPK) o;
			if (this.inst == obj.getInst() && this.iden == obj.getIden()) {
				return true;
			}
		}
		return false;
	}
}