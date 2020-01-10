package es.ldrsoftware.core.fwk.entity;

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
@Table(name = "SESI")
public class Sesi extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -3070323939097781437L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "SESIIDEN", nullable = false)
	private long  iden;
	
	public final static String IDEN = "Identificador de sesi髇";
	
	@Column(name = "SESIUSUA", nullable = false)
	private String usua;
	
	public final static String USUA = "Usuario de sesi髇";
	
	@Column(name = "SESICLAV", nullable = false)
	private long   clav;
	
	public final static String CLAV = "Clave de sesi髇";
	
	@Column(name = "SESIDIIP", nullable = false)
	private String diip;
	
	public final static String DIIP = "Direci髇 IP de Sesi髇";
	
	@Column(name = "SESIPERF", nullable = false)
	private String perf;

	public final static String PERF = "Perfil de Sesi髇";
	
	@Column(name = "SESIDVCE", nullable = false)
	private String dvce;
	
	public final static String DVCE = "Dispositivo de Sesi髇";
	
	@Column(name = "SESIINST", nullable = false)
	private long   inst;
	
	public final static String INST = "Instalaci髇 de Sesi髇";
	
	@Column(name = "SESIFEAP", nullable = false)
	private int    feap;
	
	public final static String FEAP = "Fecha de apertura de Sesi髇";
	
	@Column(name = "SESIHOAP", nullable = false)
	private int    hoap;
	
	public final static String HOAP = "Hora de apertura de Sesi髇";
	
	@Column(name = "SESIESTA", nullable = false)
	private String esta;

	public final static String ESTA = "Estado de sesi髇";
	
	@Column(name = "SESIFEUL", nullable = false)
	private int    feul;
	
	public final static String FEUL = "Fecha de 鷏tima actividad de Sesi髇";
	
	@Column(name = "SESIHOUL", nullable = false)
	private int    houl;

	public final static String HOUL = "Hora de 鷏tima actividad de Sesi髇";
	
	@Column(name = "SESIFERE", nullable = false)
	private int    fere;
	
	public final static String FERE = "Fecha de renovaci髇 de Sesi髇";
	
	@Column(name = "SESIHORE", nullable = false)
	private int    hore;

	public final static String HORE = "Hora de renovaci髇 de Sesi髇";
	
	@Column(name = "SESIFECA", nullable = false)
	private int    feca;
	
	public final static String FECA = "Fecha de caducidad de Sesi髇";
	
	@Column(name = "SESIHOCA", nullable = false)
	private int    hoca;

	public final static String HOCA = "Hora de caducidad de Sesi髇";
	
	public String key() {
		return key(iden);
	}
	
	public final static String key(long iden) {
		return StringUtil.extend(iden, 10);
	}
	
	public void validate() throws Exception {
		validateFieldString(usua, 30, Sesi.USUA);
		//TODO: clave externa de sesi贸n
		validateFieldString(diip, 20, Sesi.DIIP);
		validateFieldDomain(perf, Sesi.PERF, LiteData.LT_ST_USUAPERF);
		validateFieldDomain(dvce, Sesi.DVCE, LiteData.LT_ST_SESIDVCE);
		
		//Validamos que la instalaci贸n est茅 informada y dentro de rango, salvo para el perfil APM
		if (!LiteData.LT_EL_USUAPERF_APM.equals(perf)) {
			validateFieldLong(inst, 1, 999999999, Sesi.INST);
		}

		validateFieldDate(feap, Sesi.FEAP);
		validateFieldTime(hoap, Sesi.HOAP);

		validateFieldDomain(esta, Sesi.ESTA, LiteData.LT_ST_SESIESTA);
		validateFieldDate(feul, Sesi.FEUL);
		validateFieldTime(houl, Sesi.HOUL);
				
		//TODO: fecha/hora de renovaci贸n

		//Validamos que la fecha y hora de caducidad est茅n informada y dentro de rango, si la sesi贸n est谩 abierta
		if (LiteData.LT_EL_SESIESTA_ABIERTA.equals(esta)) {
			validateFieldDate(feca, Sesi.FECA);
			validateFieldTime(hoca, Sesi.HOCA);
		}
	}
	
	public long getIden() {
		return iden;
	}

	public void setIden(long iden) {
		this.iden = iden;
	}

	public String getUsua() {
		return usua;
	}

	public void setUsua(String usua) {
		this.usua = usua;
	}

	public long getClav() {
		return clav;
	}
	
	public void setClav(long clav) {
		this.clav = clav;
	}
	
	public String getDiip() {
		return diip;
	}

	public void setDiip(String diip) {
		this.diip = diip;
	}

	public String getPerf() {
		return perf;
	}

	public void setPerf(String perf) {
		this.perf = perf;
	}

	public String getDvce() {
		return dvce;
	}

	public void setDvce(String dvce) {
		this.dvce = dvce;
	}

	public long getInst() {
		return inst;
	}

	public void setInst(long inst) {
		this.inst = inst;
	}

	public int getFeap() {
		return feap;
	}

	public void setFeap(int feap) {
		this.feap = feap;
	}

	public int getHoap() {
		return hoap;
	}

	public void setHoap(int hoap) {
		this.hoap = hoap;
	}

	public String getEsta() {
		return esta;
	}

	public void setEsta(String esta) {
		this.esta = esta;
	}

	public int getFeul() {
		return feul;
	}

	public void setFeul(int feul) {
		this.feul = feul;
	}

	public int getHoul() {
		return houl;
	}

	public void setHoul(int houl) {
		this.houl = houl;
	}

	public int getFere() {
		return fere;
	}
	
	public void setFere(int fere) {
		this.fere = fere;
	}
	
	public int getHore() {
		return hore;
	}
	
	public void setHore(int hore) {
		this.hore = hore;
	}
	
	public int getFeca() {
		return feca;
	}

	public void setFeca(int feca) {
		this.feca = feca;
	}

	public int getHoca() {
		return hoca;
	}

	public void setHoca(int hoca) {
		this.hoca = hoca;
	}

}
