package es.ldrsoftware.core.mnu.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.arq.util.StringUtil;
import es.ldrsoftware.core.fwk.data.LiteData;

@Entity	
@Table(name = "CTMN")
public class Ctmn extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -1672008928841240755L;

	@Column(name = "CTMNPERF", nullable = false)
	private String perf;
	
	public final static String PERF = "Perfil de menú";
	
	@Id
	@Column(name = "CTMNIDEN", unique = true, nullable = false)
	private    int iden;
	
	public final static String IDEN = "Identificador de categoría de menú";
	
	@Column(name = "CTMNDESC", nullable = false)
	private String desc;
	
	public final static String DESC = "Descripción de categoría de menú";
	
	@Column(name = "CTMNACTI", nullable = false)
	private String acti;
	
	public final static String ACTI = "Indicador de categoría de menú activada";
	
	@Column(name = "CTMNORDE", nullable = false)
	private     int orde;
	
	public final static String ORDE = "Orden de categoría de menú";
	
	@Transient
	public List<Dtmn> dtmnList;

	public String key() {
		return key(iden);
	}
	
	public final static String key(long iden) {
		return StringUtil.extend(iden, 10);
	}
	
	public void validate() throws Exception {
	
		validateFieldLong(iden, 0, 9999, IDEN);
		validateFieldDomain(perf, PERF, LiteData.LT_ST_MENUPERF);
		validateFieldString(desc, 40, DESC);
		validateFieldBool(acti, ACTI);
		validateFieldLong(orde, 0, 9999, ORDE);		
	}
	
	public String getPerf() {
		return perf;
	}

	public void setPerf(String perf) {
		this.perf = perf;
	}

	public int getIden() {
		return iden;
	}

	public void setIden(int iden) {
		this.iden = iden;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getActi() {
		return acti;
	}

	public void setActi(String acti) {
		this.acti = acti;
	}

	public int getOrde() {
		return orde;
	}

	public void setOrde(int orde) {
		this.orde = orde;
	}

	public List<Dtmn> getDtmnList() {
		return dtmnList;
	}

	public void setDtmnList(List<Dtmn> dtmnList) {
		this.dtmnList = dtmnList;
	}
	
}
