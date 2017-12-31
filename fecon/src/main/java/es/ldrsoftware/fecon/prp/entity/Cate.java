package es.ldrsoftware.fecon.prp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity	
@Table(name = "CATE")
public class Cate implements Serializable {

	private static final long serialVersionUID = 3322613489867875557L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CATEIDEN", nullable = false)
	private long   iden;

	@Column(name = "CATEINST", nullable = false)
	private long   inst;

	@Column(name = "CATEDESL", nullable = false)
	private String desl;
	
	@Column(name = "CATEDESC", nullable = false)
	private String desc;
	
	@Column(name = "CATEORDE", nullable = false)
	private int    orde;

	@Column(name = "CATEPRES", nullable = false)
	private String pres;

	public long getIden() {
		return iden;
	}

	public void setIden(long iden) {
		this.iden = iden;
	}

	public long getInst() {
		return inst;
	}

	public void setInst(long inst) {
		this.inst = inst;
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

	public String getPres() {
		return pres;
	}

	public void setPres(String pres) {
		this.pres = pres;
	}
		
}
