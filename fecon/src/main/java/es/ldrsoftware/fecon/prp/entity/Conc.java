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
import es.ldrsoftware.fecon.data.LiteData;

@Entity	
@Table(name = "CONC")
public class Conc extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 1383027102789571754L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CONCIDEN", nullable = false)
	private long   iden;

	public final static String IDEN = "Identificador de concepto";
	
	@Column(name = "CONCCATE", nullable = false)
	private long   cate;
	
	public final static String CATE = "Categoría de Concepto";
	
	@Column(name = "CONCINST", nullable = false)
	private long   inst;

	public final static String INST = "Instalación de Concepto";
	
	@Column(name = "CONCTIPO", nullable = false)
	private String tipo;
	
	public final static String TIPO = "Tipo de Concepto";
	
	@Column(name = "CONCDESL", nullable = false)
	private String desl;
	
	public final static String DESL = "Descripción larga de Concepto";
	
	@Column(name = "CONCDESC", nullable = false)
	private String desc;
	
	public final static String DESC = "Descripción corta de Concepto";
	
	@Column(name = "CONCORDE", nullable = false)
	private int    orde;

	public final static String ORDE = "Orden de Concepto";
	
	public String key() {
		return key(iden);
	}
	
	public final static String key(long iden) {
		return StringUtil.extend(iden, 10);
	}
	
	public void validate() throws Exception {
		validateFieldLong(cate, 0, 999999999, Conc.CATE);
		validateFieldLong(cate, 0, 999999999, Conc.INST);
		validateFieldDomain(tipo, Conc.TIPO, LiteData.LT_ST_CONCTIPO);
		validateFieldString(desl, 30, Conc.DESL);
		validateFieldString(desc, 10, Conc.DESC);
		validateFieldLong(orde, 0, 99, Conc.ORDE);
	}
	
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
