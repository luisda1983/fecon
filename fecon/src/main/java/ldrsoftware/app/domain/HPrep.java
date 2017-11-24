package ldrsoftware.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "THPREP")
@IdClass(HPrepPK.class)
public class HPrep {

	@Id
	@Column(name = "PREPINST", nullable = false)
	private long inst;
	
	@Id
	@Column(name = "PREPIDEN", nullable = false)
	private int iden;
	
	@Column(name = "PREPANUA", nullable = false)
	private int anua;
	
	@Column(name = "PREPCATE", nullable = false)
	private int cate;
	
	@Column(name = "PREPCONC", nullable = false)
	private int conc;

	@Column(name = "PREPPERI", nullable = false)
	private String peri;
	
	@Column(name = "PREPMINI", nullable = false)
	private int mini;
	
	@Column(name = "PREPMFIN", nullable = false)
	private int mfin;
	
	@Column(name = "PREPIMPO", nullable = false)
	private double impo;
	
	@Column(name = "PREPTOTA", nullable = false)
	private double tota;
	
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

	public int getAnua() {
		return anua;
	}

	public void setAnua(int anua) {
		this.anua = anua;
	}

	public int getCate() {
		return this.cate;
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

	public String getPeri() {
		return peri;
	}

	public void setPeri(String peri) {
		this.peri = peri;
	}

	public int getMini() {
		return mini;
	}

	public void setMini(int mini) {
		this.mini = mini;
	}

	public int getMfin() {
		return mfin;
	}

	public void setMfin(int mfin) {
		this.mfin = mfin;
	}

	public double getImpo() {
		return impo;
	}

	public void setImpo(double impo) {
		this.impo = impo;
	}

	public double getTota() {
		return tota;
	}

	public void setTota(double tota) {
		this.tota = tota;
	}

	@Override
	public String toString() {
		return "Pres [inst=" + inst + ", "
				   + "anua=" + anua + ", "
				   + "cate=" + cate + ", "
				   + "conc=" + conc + ", "
				   + "peri=" + peri + ", "
				   + "mini=" + mini + ", "
				   + "mfin=" + mfin + ", "
				   + "impo=" + impo + ", "
				   + "tota=" + tota + ", "
				   ;
	}
}

class HPrepPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PREPINST", nullable = false)
	private long inst;
	
	@Id
	@Column(name = "PREPIDEN", nullable = false)
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
		if (o instanceof HPrepPK) {
			HPrepPK obj = (HPrepPK)o;
			if (obj.getInst() == this.inst && 
				obj.getIden() == this.iden) {
				return true;
			}
		}
		return false;
	}
}