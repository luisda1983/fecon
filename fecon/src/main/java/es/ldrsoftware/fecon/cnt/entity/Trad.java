package es.ldrsoftware.fecon.cnt.entity;

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
@Table(name = "TRAD")
public class Trad extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 2329008820179297339L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "TRADIDEN", nullable = false)
	private long   iden;

	public final static String IDEN = "Identificador de Traducción";
	
	@Column(name = "TRADINST", nullable = false)
	private long   inst;

	public final static String INST = "Instalación de Traducción";
	
	@Column(name = "TRADNOMB", nullable = false)
	private String nomb;
	
	public final static String NOMB = "Nombre de Traducción";
	
	@Column(name = "TRADTIP1", nullable = false)
	private String tip1;
	
	public final static String TIP1 = "Tipo de elemento de Traducción 1";
	
	@Column(name = "TRADDOM1", nullable = false)
	private long dom1;
	
	public final static String DOM1 = "Dominio de elemento de Traducción 1";
	
	@Column(name = "TRADIDE1", nullable = false)
	private String ide1;
	
	public final static String IDE1 = "Identificador de elemento de Traducción 1";
	
	@Column(name = "TRADOBL1", nullable = false)
	private String obl1;
	
	public final static String OBL1 = "Indicador de elemento de Traducción 1 obligatorio";

	@Column(name = "TRADTIP2", nullable = false)
	private String tip2;
	
	public final static String TIP2 = "Tipo de elemento de Traducción 2";
	
	@Column(name = "TRADDOM2", nullable = false)
	private long dom2;
	
	public final static String DOM2 = "Dominio de elemento de Traducción 2";
	
	@Column(name = "TRADIDE2", nullable = false)
	private String ide2;
	
	public final static String IDE2 = "Identificador de elemento de Traducción 2";
	
	@Column(name = "TRADOBL2", nullable = false)
	private String obl2;
	
	public final static String OBL2 = "Indicador de elemento de Traducción 2 obligatorio";

	@Column(name = "TRADTIP3", nullable = false)
	private String tip3;
	
	public final static String TIP3 = "Tipo de elemento de Traducción 3";
	
	@Column(name = "TRADDOM3", nullable = false)
	private long dom3;
	
	public final static String DOM3 = "Dominio de elemento de Traducción 3";
	
	@Column(name = "TRADIDE3", nullable = false)
	private String ide3;
	
	public final static String IDE3 = "Identificador de elemento de Traducción 3";
	
	@Column(name = "TRADOBL3", nullable = false)
	private String obl3;
	
	public final static String OBL3 = "Indicador de elemento de Traducción 3 obligatorio";

	@Column(name = "TRADDESC", nullable = false)
	private String desc;
	
	public final static String DESC = "Descripción de Traducción";
		
	public String key() {
		return key(iden);
	}
	
	public final static String key(long iden) {
		return StringUtil.extend(iden, 10);
	}
	
	public void validate() throws Exception {
		validateFieldLong(iden, 0, 999999999, Trad.IDEN);
		validateFieldLong(inst, 0, 999999999, Trad.INST);
		validateFieldString(nomb, 20, Trad.NOMB);
		if (tip1 != null && !"".equals(tip1)) {
			validateFieldDomain(tip1, Trad.TIP1, LiteData.LT_ST_TRADTIPO);
			if (LiteData.LT_EL_TRADTIPO_DOMINIO.equals(tip1)) {
				validateFieldLong(dom1, 1, 999999999, Trad.DOM1);
			} else {
				validateFieldLong(dom1, 0, 0, Trad.DOM1);
			}
			validateFieldString(ide1, 30, Trad.IDE1);
			validateFieldDomain(obl1, Trad.OBL1, es.ldrsoftware.core.fwk.data.LiteData.LT_ST_BOOL);
		}
		if (tip2 != null && !"".equals(tip2)) {
			validateFieldDomain(tip2, Trad.TIP2, LiteData.LT_ST_TRADTIPO);
			if (LiteData.LT_EL_TRADTIPO_DOMINIO.equals(tip2)) {
				validateFieldLong(dom2, 1, 999999999, Trad.DOM2);
			} else {
				validateFieldLong(dom2, 0, 0, Trad.DOM2);
			}
			validateFieldString(ide2, 30, Trad.IDE2);
			validateFieldDomain(obl2, Trad.OBL2, es.ldrsoftware.core.fwk.data.LiteData.LT_ST_BOOL);
		}
		if (tip3 != null && !"".equals(tip3)) {
			validateFieldDomain(tip3, Trad.TIP3, LiteData.LT_ST_TRADTIPO);
			if (LiteData.LT_EL_TRADTIPO_DOMINIO.equals(tip3)) {
				validateFieldLong(dom3, 1, 999999999, Trad.DOM3);
			} else {
				validateFieldLong(dom3, 0, 0, Trad.DOM3);
			}
			validateFieldString(ide3, 30, Trad.IDE3);
			validateFieldDomain(obl3, Trad.OBL3, es.ldrsoftware.core.fwk.data.LiteData.LT_ST_BOOL);
		}
		
		validateFieldString(desc, 100, Trad.DESC);
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

	public String getNomb() {
		return nomb;
	}

	public void setNomb(String nomb) {
		this.nomb = nomb;
	}

	public String getTip1() {
		return tip1;
	}

	public void setTip1(String tip1) {
		this.tip1 = tip1;
	}

	public long getDom1() {
		return dom1;
	}

	public void setDom1(long dom1) {
		this.dom1 = dom1;
	}

	public String getIde1() {
		return ide1;
	}

	public void setIde1(String ide1) {
		this.ide1 = ide1;
	}

	public String getObl1() {
		return obl1;
	}

	public void setObl1(String obl1) {
		this.obl1 = obl1;
	}

	public String getTip2() {
		return tip2;
	}

	public void setTip2(String tip2) {
		this.tip2 = tip2;
	}

	public long getDom2() {
		return dom2;
	}

	public void setDom2(long dom2) {
		this.dom2 = dom2;
	}

	public String getIde2() {
		return ide2;
	}

	public void setIde2(String ide2) {
		this.ide2 = ide2;
	}

	public String getObl2() {
		return obl2;
	}

	public void setObl2(String obl2) {
		this.obl2 = obl2;
	}

	public String getTip3() {
		return tip3;
	}

	public void setTip3(String tip3) {
		this.tip3 = tip3;
	}

	public long getDom3() {
		return dom3;
	}

	public void setDom3(long dom3) {
		this.dom3 = dom3;
	}

	public String getIde3() {
		return ide3;
	}

	public void setIde3(String ide3) {
		this.ide3 = ide3;
	}

	public String getObl3() {
		return obl3;
	}

	public void setObl3(String obl3) {
		this.obl3 = obl3;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
