package es.ldrsoftware.core.fwk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity	
@Table(name = "CTRL")
public class Ctrl implements Serializable {

	private static final long serialVersionUID = -2323904631592824271L;

	@Id
	@Column(name = "CTRLIDEN", nullable = false)
	private String iden;
	
	@Column(name = "CTRLDESC", nullable = false)
	private String desc;
	
	@Column(name = "CTRLESTA", nullable = false)
	private String esta;
	
	@Column(name = "CTRLMOTI", nullable = false)
	private String moti;
	
	@Column(name = "CTRLTIAC", nullable = false)
	private String tiac;
	
	@Column(name = "CTRLPERF", nullable = false)
	private String perf;
	
	@Column(name = "CTRLSTST", nullable = false)
	private String stst;
	
	@Column(name = "CTRLFEAL", nullable = false)
	private     int feal;

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

	public String getEsta() {
		return esta;
	}

	public void setEsta(String esta) {
		this.esta = esta;
	}

	public String getMoti() {
		return moti;
	}

	public void setMoti(String moti) {
		this.moti = moti;
	}

	public String getTiac() {
		return tiac;
	}

	public void setTiac(String tiac) {
		this.tiac = tiac;
	}

	public String getPerf() {
		return perf;
	}

	public void setPerf(String perf) {
		this.perf = perf;
	}

	public String getStst() {
		return stst;
	}

	public void setStst(String stst) {
		this.stst = stst;
	}

	public int getFeal() {
		return feal;
	}

	public void setFeal(int feal) {
		this.feal = feal;
	}
	
}
