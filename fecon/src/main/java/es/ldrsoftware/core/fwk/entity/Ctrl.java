package es.ldrsoftware.core.fwk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.ldrsoftware.core.arq.BaseDTO;

@Entity	
@Table(name = "CTRL")
public class Ctrl extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -2323904631592824271L;

	@Id
	@Column(name = "CTRLIDEN", nullable = false)
	private String iden;
	
	public final static String IDEN = "Identificador de Controlador";
	
	@Column(name = "CTRLDESC", nullable = false)
	private String desc;
	
	public final static String DESC = "Descripción de Controlador";
	
	@Column(name = "CTRLESTA", nullable = false)
	private String esta;
	
	public final static String ESTA = "Estado de Controlador";
	
	@Column(name = "CTRLMOTI", nullable = false)
	private String moti;
	
	public final static String MOTI = "Motivo del estado de Controlador";
	
	@Column(name = "CTRLTIAC", nullable = false)
	private String tiac;
	
	public final static String TIAC = "Tipo de acceso del Controlador";
	
	@Column(name = "CTRLPERF", nullable = false)
	private String perf;
	
	public final static String PERF = "Perfil de acceso al Controlador";
	
	@Column(name = "CTRLSTST", nullable = false)
	private String stst;
	
	public final static String STST = "Indicador de generación de estadísticas";
	
	@Column(name = "CTRLFEAL", nullable = false)
	private     int feal;

	public final static String FEAL = "Fecha de alta del Controlador";
	
	@Column(name = "CTRLCONT", nullable = false)
	private String cont;
	
	public final static String CONT = "Indicador de Controlador con continuación";
	
	@Column(name = "CTRLNREG", nullable = false)
	private    int nreg;
	
	public final static String NREG = "Número de registros para continuación";
	
	@Column(name = "CTRLIDAO", nullable = false)
	private String idao;
	
	public final static String IDAO = "DAO afectado por continuacion";
	
	public String key() {
		return key(iden);
	}
	
	public final static String key(String iden) {
		return iden;
	}
	
	public void validate() throws Exception {
		
	}
	
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

	public String getCont() {
		return cont;
	}

	public void setCont(String cont) {
		this.cont = cont;
	}

	public int getNreg() {
		return nreg;
	}

	public void setNreg(int nreg) {
		this.nreg = nreg;
	}

	public String getIdao() {
		return idao;
	}

	public void setIdao(String idao) {
		this.idao = idao;
	}
}
