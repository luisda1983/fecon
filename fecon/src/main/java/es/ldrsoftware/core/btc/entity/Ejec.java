package es.ldrsoftware.core.btc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.arq.util.StringUtil;
import es.ldrsoftware.core.fwk.data.LiteData;

@Entity	
@Table(name = "EJEC")
@IdClass(EjecPK.class)
public class Ejec extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -5894490436685677229L;

	@Id
	@Column(name = "EJECFECH", unique = true, nullable = false)
	private    int fech;

	public final static String FECH = "Fecha de ejecuci髇";
	
	@Id
	@Column(name = "EJECHORA", unique = true, nullable = false)
	private    int hora;
	
	public final static String HORA = "Hora de ejecuci髇"; 
	
	@Id
	@Column(name = "EJECBTCH", unique = true, nullable = false)
	private String btch;

	public final static String BTCH = "Identificador de Batch";
	
	@Id
	@Column(name = "EJECSECU", nullable = false)
	private    int secu;

	public final static String SECU = "Secuencia de ejecuci髇";
	
	@Column(name = "EJECORDE", nullable = false)
	private    int orde;
	
	public final static String ORDE = "Orden de ejecuci髇";
	
	@Column(name = "EJECESTA", nullable = false)
	private String esta;
	
	public final static String ESTA = "Estado de ejecuci髇";
	
	@Column(name = "EJECNOTF", nullable = false)
	private String notf;
	
	public final static String NOTF = "Notificaci髇 de ejecuci髇";
	
	@Column(name = "EJECFBTC", nullable = false)
	private    int fbtc;
	
	public final static String FBTC = "Fecha batch de ejecuci髇";
	
	@Column(name = "EJECFEIN", nullable = false)
	private    int fein;

	public final static String FEIN = "Fecha de inicio de ejecuci髇";
	
	@Column(name = "EJECHOIN", nullable = false)
	private    int hoin;
	
	public final static String HOIN = "Hora de inicio de ejecuci髇";
	
	@Column(name = "EJECFEFI", nullable = false)
	private    int fefi;
	
	public final static String FEFI = "Fecha de fin de ejecuci髇";
	
	@Column(name = "EJECHOFI", nullable = false)
	private    int hofi;
	
	public final static String HOFI = "Hora de fin de ejecuci髇";
	
	@Column(name = "EJECTIEJ", nullable = false)
	private    int tiej;

	public final static String TIEJ = "Tiempo de ejecuci髇";
	
	@Column(name = "EJECFEPL", nullable = false)
	private    int fepl;
	
	public final static String FEPL = "Fecha planificaci髇 de la ejecuci髇";
	
	@Column(name = "EJECHOPL", nullable = false)
	private    int hopl;

	public final static String HOPL = "Hora planificaci髇 de la ejecuci髇";
	
	public String key() {
		return key(fech, hora, btch, secu);
	}
	
	public final static String key(long fech, long hora, String btch, long secu) {
		return StringUtil.extend(fech, 8) + "|"
			 + StringUtil.extend(hora, 6) + "|"
			 + btch + "|"
			 + StringUtil.extend(secu, 4);
	}
	
	public void validate() throws Exception {
		validateFieldDate(fech, Ejec.FECH);
		validateFieldShortTime(hora, Ejec.HORA);
		validateFieldString(btch, 30, Ejec.BTCH);
		validateFieldLong(secu, 00, 99, Ejec.SECU);
		validateFieldLong(orde, 00000, 99999, Ejec.ORDE);
		validateFieldDomain(esta, Ejec.ESTA, LiteData.LT_ST_EJECESTA);
		
		//Si la ejecuci贸n est谩 pendiente, no se permtien los datos de auditor铆a
		if (LiteData.LT_EL_EJECESTA_PENDIENTE.equals(esta)) {
			validateFieldEmpty(fein, Ejec.FEIN);
			validateFieldEmpty(hoin, Ejec.HOIN);
			validateFieldEmpty(fefi, Ejec.FEFI);
			validateFieldEmpty(hofi, Ejec.HOFI);
			validateFieldEmpty(tiej, Ejec.TIEJ);
			validateFieldEmpty(fepl, Ejec.FEPL);
			validateFieldEmpty(hopl, Ejec.HOPL);
		} else {
		//Si la ejecuci贸n no est谩 pendiente, los datos de auditor铆a son obligatorios.
			validateFieldDate(fein, Ejec.FEIN);
			validateFieldTime(hoin, Ejec.HOIN);
			validateFieldDate(fefi, Ejec.FEFI);
			validateFieldTime(hofi, Ejec.HOFI);
			validateFieldLong(tiej, 0, 99999999, Ejec.TIEJ);			
			
			//La auditoria de planificaci贸n s贸lo es obligatoria en primeras ejecuciones de procesos
			if (secu == 1) {
				validateFieldDate(fepl, Ejec.FEPL);
				validateFieldTime(hopl, Ejec.HOPL);
			}
		}

		//Si la ejecuci贸n ha finalizado con error, debe informarse el c贸digo de notificaci贸n
		if (LiteData.LT_EL_EJECESTA_VOID.equals(esta)) {
			validateFieldString(notf, 10, Ejec.NOTF);
		} else {
		//En caso contrario, no se permite
			validateFieldEmpty(notf, Ejec.NOTF);
		}

		//Si la ejecuci贸n no est谩 pendiente, debe informarse la fecha batch
		if (!LiteData.LT_EL_EJECESTA_PENDIENTE.equals(esta)) {
			validateFieldDate(fbtc, Ejec.FBTC);
		} else {
			validateFieldEmpty(fbtc, Ejec.FBTC);
		}
	}
	
	public int getFech() {
		return fech;
	}

	public void setFech(int fech) {
		this.fech = fech;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public String getBtch() {
		return btch;
	}

	public void setBtch(String btch) {
		this.btch = btch;
	}

	public int getSecu() {
		return secu;
	}

	public void setSecu(int secu) {
		this.secu = secu;
	}

	public int getOrde() {
		return orde;
	}

	public void setOrde(int orde) {
		this.orde = orde;
	}

	public String getEsta() {
		return esta;
	}

	public void setEsta(String esta) {
		this.esta = esta;
	}

	public String getNotf() {
		return notf;
	}

	public void setNotf(String notf) {
		this.notf = notf;
	}

	public int getFbtc() {
		return fbtc;
	}

	public void setFbtc(int fbtc) {
		this.fbtc = fbtc;
	}

	public int getFein() {
		return fein;
	}

	public void setFein(int fein) {
		this.fein = fein;
	}

	public int getHoin() {
		return hoin;
	}

	public void setHoin(int hoin) {
		this.hoin = hoin;
	}

	public int getFefi() {
		return fefi;
	}

	public void setFefi(int fefi) {
		this.fefi = fefi;
	}

	public int getHofi() {
		return hofi;
	}

	public void setHofi(int hofi) {
		this.hofi = hofi;
	}

	public int getTiej() {
		return tiej;
	}

	public void setTiej(int tiej) {
		this.tiej = tiej;
	}

	public int getFepl() {
		return fepl;
	}

	public void setFepl(int fepl) {
		this.fepl = fepl;
	}

	public int getHopl() {
		return hopl;
	}

	public void setHopl(int hopl) {
		this.hopl = hopl;
	}
}

class EjecPK implements Serializable {

	private static final long serialVersionUID = -3586423640423543811L;

	@Id
	@Column(name = "EJECFECH", unique = true, nullable = false)
	private    int fech;

	@Id
	@Column(name = "EJECHORA", unique = true, nullable = false)
	private    int hora;

	@Id
	@Column(name = "EJECBTCH", unique = true, nullable = false)
	private String btch;

	@Id
	@Column(name = "EJECSECU", nullable = false)
	private    int secu;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof EjecPK) {
			EjecPK obj = (EjecPK)o;
			if (obj.fech == this.fech && obj.hora == this.hora && obj.btch.equals(this.btch) && obj.secu == this.secu) {
				return true;
			}
		}
		return false;
	}
}