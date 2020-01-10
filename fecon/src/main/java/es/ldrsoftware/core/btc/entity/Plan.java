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
@Table(name = "PLAN")
@IdClass(PlanPK.class)
public class Plan extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 4470288665160191135L;

	@Id
	@Column(name = "PLANFECH", nullable = false)
	private int    fech;

	public final static String FECH = "Fecha de planificaci髇";
	
	@Id
	@Column(name = "PLANHORA", nullable = false)
	private int    hora;
	
	public final static String HORA = "Hora de planificaci髇";
	
	@Column(name = "PLANFBTC", nullable = false)
	private int    fbtc;
	
	public final static String FBTC = "Fecha Batch";
	
	@Column(name = "PLANESTA", nullable = false)
	private String esta;
	
	public final static String ESTA = "Estado de planificaci髇";
	
	@Column(name = "PLANFEIN", nullable = false)
	private int    fein;
	
	public final static String FEIN = "Fecha de inicio de planificaci髇";
	
	@Column(name = "PLANHOIN", nullable = false)
	private int    hoin;
	
	public final static String HOIN = "Hora de inicio de planificaci髇";
	
	@Column(name = "PLANFEFI", nullable = false)
	private int    fefi;
	
	public final static String FEFI = "Fecha de fin de planificaci髇";
	
	@Column(name = "PLANHOFI", nullable = false)
	private int    hofi;
	
	public final static String HOFI = "Hora de fin de planificaci髇";
	
	@Column(name = "PLANPROC", nullable = false)
	private int    proc;

	public final static String PROC = "N鷐ero de procesos ejecutados";
	
	@Column(name = "PLANVENT", nullable = false)
	private int    vent;

	public final static String VENT = "Ventaja de ejecuci髇 de planificaci髇";
	
	@Column(name = "PLANPORC", nullable = false)
	private double porc;

	public final static String PORC = "Porcentaje de ventana de ejecuci髇 utilidada";
	
	public String key() {
		return key(fech, hora);
	}
	
	public final static String key(long fech, long hora) {
		return StringUtil.extend(fech, 8) + "|"
			 + StringUtil.extend(hora, 6);
	}
	
	public void validate() throws Exception {

		validateFieldDate(fech, Plan.FECH);
		validateFieldShortTime(hora, Plan.HORA);
		validateFieldDate(fbtc, Plan.FBTC);
		validateFieldDomain(esta, Plan.ESTA, LiteData.LT_ST_PLANESTA);

		//Si el estado es pendiente, fecha y hora de fin deben estar vac铆o, en caso contrario los validamos
		if (LiteData.LT_EL_PLANESTA_PENDIENTE.equals(esta)) {
			validateFieldEmpty(fein, Plan.FEIN);
			validateFieldEmpty(hoin, Plan.HOIN);
		} else {
			//Validamos que la fecha de inicio de planificaci贸n est茅 informada y dentro de rango
			validateFieldDate(fein, Plan.FEIN);
			validateFieldTime(hoin, Plan.HOIN);
		}
		
		//Si el estado es finalizado o error, validamos fecha y hora de finalizaci贸n, en caso contrario deben estar vac铆os
		if (LiteData.LT_EL_PLANESTA_FINALIZADO.equals(esta) ||
			LiteData.LT_EL_PLANESTA_ERROR.equals(esta)) {
			//Validamos que la fecha de fin de planificaci贸n est茅 informada y dentro de rango
			validateFieldDate(fefi, Plan.FEFI);
			//Validamos que la hora de fin de planificaci贸n est茅 dentro de rango
			validateFieldTime(hofi, Plan.HOFI);
		} else {
			validateFieldEmpty(fefi, Plan.FEFI);
			validateFieldEmpty(hofi, Plan.HOFI);
		}
		
		//Si el estado es pendiente, el n煤mero de procesos ejecutados en la planificaci贸n debe ser 0, en caso contrario, validamos rango
		if (LiteData.LT_EL_PLANESTA_PENDIENTE.equals(esta)) {
			validateFieldEmpty(proc, Plan.PROC);
		} else {
			validateFieldLong(proc, 0, 999999, Plan.PROC);
		}
		
		validateFieldLong(vent, 0015, 2400, Plan.VENT);
		

		//El porcentaje de uso de ventana s贸lo estar谩 informada cuando el proceso est茅 finalizado o en error
		if ((LiteData.LT_EL_PLANESTA_FINALIZADO.equals(esta) ||
			LiteData.LT_EL_PLANESTA_ERROR.equals(esta)) &&
			proc > 0) {
			validateFieldDouble(porc, 0.00, 100.00, Plan.PORC);
		} else {
			validateFieldEmpty(porc, Plan.PORC);
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

	public int getFbtc() {
		return fbtc;
	}

	public void setFbtc(int fbtc) {
		this.fbtc = fbtc;
	}

	public String getEsta() {
		return esta;
	}

	public void setEsta(String esta) {
		this.esta = esta;
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

	public int getProc() {
		return proc;
	}

	public void setProc(int proc) {
		this.proc = proc;
	}

	public int getVent() {
		return vent;
	}

	public void setVent(int vent) {
		this.vent = vent;
	}

	public double getPorc() {
		return porc;
	}

	public void setPorc(double porc) {
		this.porc = porc;
	}
	
}

class PlanPK implements Serializable {

	private static final long serialVersionUID = 487779799474379307L;

	@Id
	@Column(name = "PLANFECH", unique = true, nullable = false)
	private int    fech;

	@Id
	@Column(name = "PLANHORA", unique = true, nullable = false)
	private int    hora;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof PlanPK) {
			PlanPK obj = (PlanPK)o;
			if (obj.fech == this.fech && obj.hora == this.hora) {
				return true;
			}
		}
		return false;
	}
}