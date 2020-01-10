package es.ldrsoftware.core.fwk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.arq.util.StringUtil;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.fwk.data.RD;

@Entity	
@Table(name = "RELA")
@IdClass(RelaPK.class)
public class Rela extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 4179560358220168105L;

	@Id
	@Column(name = "RELAMAE1", unique = true, nullable = false)
	private String mae1;

	public final static String MAE1 = "Maestro 1 de Relación";
	
	@Id
	@Column(name = "RELACLN1", unique = true, nullable = false)
	private long   cln1;
	
	public final static String CLN1 = "Clave de relación (N1)";
	
	@Id
	@Column(name = "RELACLC1", unique = true, nullable = false)
	private String clc1;
	
	public final static String CLC1 = "Clave de relación (C1)";
	
	@Id
	@Column(name = "RELAMAE2", unique = true, nullable = false)
	private String mae2;

	public final static String MAE2 = "Maestro 2 de relación";
	
	@Id
	@Column(name = "RELACLN2", unique = true, nullable = false)
	private long   cln2;

	public final static String CLN2 = "Clave de relación (N2)";
	@Id
	@Column(name = "RELACLC2", unique = true, nullable = false)
	private String clc2;
	
	public final static String CLC2 = "Clave de relación (C2)";
	
	@Column(name = "RELAESTA", nullable = false)
	private String esta;

	public final static String ESTA = "Estado de relación";
	
	@Column(name = "RELAFEAL", nullable = false)
	private    int feal;

	public final static String FEAL = "Fecha de alta de relación";
	
	@Column(name = "RELADATA", nullable = false)
	private String data;
	
	public final static String DATA = "Datos de relación";
	
	@Transient
	private RD rdat;
	
	public final static String USUA = "Usuario";
	public final static String PERF = "Perfil";
	public final static String INST = "Instalación";
	
	public String key() {
		return key(mae1, cln1, clc1, mae2, cln2, clc2);
	}
	
	public final static String key(String mae1, long cln1, String clc1, String mae2, long cln2, String clc2) {
		return mae1 + "|"
			 + StringUtil.extend(cln1, 10) + "|"
			 + clc1 + "|"
			 + mae2 + "|"
			 + StringUtil.extend(cln2, 10) + "|"
			 + clc2 + "|";
	}
	
	public void validate() throws Exception {
		validateFieldDomain(mae1, Rela.MAE1, LiteData.LT_ST_RELAMAES);
		validateFieldDomain(mae2, Rela.MAE2, LiteData.LT_ST_RELAMAES);
		//FIXME: admite 18
		validateFieldLong(cln1, 0, 999999999, Rela.CLN1);
		validateFieldLong(cln2, 0, 999999999, Rela.CLN2);
		
		if (clc1 != null && !"".equals(clc1)) {
			validateFieldString(clc1, 30, Rela.CLC1);
		}
		if (clc2 != null && !"".equals(clc2)) {
			validateFieldString(clc2, 30, Rela.CLC2);
		}

		validateFieldDomain(esta, Rela.ESTA, LiteData.LT_ST_RELAESTA);
		validateFieldDate(feal, Rela.FEAL);

		if (data != null && !"".equals(data)) {
			validateFieldString(data, 100, Rela.DATA);
		}
	}
	
	public String getMae1() {
		return mae1;
	}

	public void setMae1(String mae1) {
		this.mae1 = mae1;
	}

	public long getCln1() {
		return cln1;
	}

	public void setCln1(long cln1) {
		this.cln1 = cln1;
	}

	public String getClc1() {
		return clc1;
	}

	public void setClc1(String clc1) {
		this.clc1 = clc1;
	}

	public String getMae2() {
		return mae2;
	}

	public void setMae2(String mae2) {
		this.mae2 = mae2;
	}

	public long getCln2() {
		return cln2;
	}

	public void setCln2(long cln2) {
		this.cln2 = cln2;
	}

	public String getClc2() {
		return clc2;
	}

	public void setClc2(String clc2) {
		this.clc2 = clc2;
	}

	public String getEsta() {
		return esta;
	}

	public void setEsta(String esta) {
		this.esta = esta;
	}

	public int getFeal() {
		return feal;
	}

	public void setFeal(int feal) {
		this.feal = feal;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public RD getRdat() {
		return rdat;
	}
	
	public void setRdat(RD rdat) {
		this.rdat = rdat;
	}
}

class RelaPK implements Serializable {

	private static final long serialVersionUID = 5036199401189225974L;

	@Id
	@Column(name = "RELAMAE1", unique = true, nullable = false)
	private String mae1;

	@Id
	@Column(name = "RELACLN1", unique = true, nullable = false)
	private long   cln1;
	
	@Id
	@Column(name = "RELACLC1", unique = true, nullable = false)
	private String clc1;
	
	@Id
	@Column(name = "RELAMAE2", unique = true, nullable = false)
	private String mae2;

	@Id
	@Column(name = "RELACLN2", unique = true, nullable = false)
	private long   cln2;

	@Id
	@Column(name = "RELACLC2", unique = true, nullable = false)
	private String clc2;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof RelaPK) {
			RelaPK obj = (RelaPK)o;
			if (obj.mae1.equals(this.mae1) && obj.mae2.equals(this.mae2) && 
				obj.clc1.equals(this.clc1) && obj.clc2.equals(this.clc2) &&
				obj.cln1 == this.cln1 && obj.cln2 == this.cln2) {
				return true;
			}
		}
		return false;
	}
}