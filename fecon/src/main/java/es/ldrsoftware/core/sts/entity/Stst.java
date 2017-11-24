package es.ldrsoftware.core.sts.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity	
@Table(name = "STST")
public class Stst implements Serializable {

	private static final long serialVersionUID = 3569136645585706328L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "STSTIDEN", nullable = false)
	private long iden;
	
	@Column(name = "STSTCTRL", nullable = false)
	private String ctrl;
	
	@Column(name = "STSTINST", nullable = false)
	private long inst;
	
	@Column(name = "STSTUSUA", nullable = false)
	private String usua;
	
	@Column(name = "STSTFEEJ", nullable = false)
	private int feej;
	
	@Column(name = "STSTHOEJ", nullable = false)
	private int hoej;
	
	@Column(name = "STSTTIEJ", nullable = false)
	private int tiej;
	
	@Column(name = "STSTREEJ", nullable = false)
	private String reej;
	
	@Column(name = "STSTNOTF", nullable = false)
	private String notf;

	public long getIden() {
		return iden;
	}

	public void setIden(long iden) {
		this.iden = iden;
	}

	public String getCtrl() {
		return ctrl;
	}

	public void setCtrl(String ctrl) {
		this.ctrl = ctrl;
	}

	public long getInst() {
		return inst;
	}

	public void setInst(long inst) {
		this.inst = inst;
	}

	public String getUsua() {
		return usua;
	}

	public void setUsua(String usua) {
		this.usua = usua;
	}

	public int getFeej() {
		return feej;
	}

	public void setFeej(int feej) {
		this.feej = feej;
	}

	public int getHoej() {
		return hoej;
	}

	public void setHoej(int hoej) {
		this.hoej = hoej;
	}

	public int getTiej() {
		return tiej;
	}

	public void setTiej(int tiej) {
		this.tiej = tiej;
	}

	public String getReej() {
		return reej;
	}

	public void setReej(String reej) {
		this.reej = reej;
	}

	public String getNotf() {
		return notf;
	}
	
	public void setNotf(String notf) {
		this.notf = notf;
	}
}
