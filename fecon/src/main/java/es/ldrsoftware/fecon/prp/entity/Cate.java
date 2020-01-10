package es.ldrsoftware.fecon.prp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.arq.util.StringUtil;
import es.ldrsoftware.core.fwk.data.LiteData;

@Entity	
@Table(name = "CATE")
public class Cate extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 3322613489867875557L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CATEIDEN", nullable = false)
	private long   iden;

	public final static String IDEN = "Identificador de Categor�a";
	
	@Column(name = "CATEINST", nullable = false)
	private long   inst;

	public final static String INST = "Instalaci�n de Categor�a";
	
	@Column(name = "CATEDESL", nullable = false)
	private String desl;
	
	public final static String DESL = "Descripci�n larga de Categor�a";
	
	@Column(name = "CATEDESC", nullable = false)
	private String desc;
	
	public final static String DESC = "Descripci�n corta de Categor�a";
	
	@Column(name = "CATEORDE", nullable = false)
	private int    orde;

	public final static String ORDE = "Orden de Categor�a";
	
	@Column(name = "CATEPRES", nullable = false)
	private String pres;

	public final static String PRES = "Indicador de Categor�a que admite presupuesto";
	
	public String key() {
		return key(iden);
	}
	
	public final static String key(long iden) {
		return StringUtil.extend(iden, 10);
	}
	
	public void validate() throws Exception {
		validateFieldLong(inst, 0, 999999999, Cate.INST);
		validateFieldString(desl, 30, Cate.DESL);
		validateFieldString(desc, 10, Cate.DESC);
		validateFieldLong(orde, 0, 99, Cate.ORDE);
		validateFieldDomain(pres, Cate.PRES, LiteData.LT_ST_BOOL);	
	}
	
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
