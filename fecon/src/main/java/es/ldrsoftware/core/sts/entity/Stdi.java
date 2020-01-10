package es.ldrsoftware.core.sts.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import es.ldrsoftware.core.arq.BaseDTO;
import es.ldrsoftware.core.arq.util.StringUtil;

@Entity	
@Table(name = "STDI")
@IdClass(StdiPK.class)
public class Stdi extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -645928105535579829L;

	@Id
	@Column(name = "STDIFECH", unique = true, nullable = false)
	private int fech;

	public final static String FECH = "Fecha de estadística diaria";
	
	@Id
	@Column(name = "STDICTRL", unique = true, nullable = false)
	private String ctrl;
	
	public final static String CTRL = "Controlador de estadística diaria";
	
	@Column(name = "STDITOTA", nullable = false)
	private int tota;
	
	public final static String TOTA = "Total de ejecuciones";
	
	@Column(name = "STDITIME", nullable = false)
	private float time;
	
	public final static String TIME = "Tiempo medio de ejecución";
	
	@Column(name = "STDITIMA", nullable = false)
	private int tima;
	
	public final static String TIMA = "Tiempo máximo de ejecución";
	
	@Column(name = "STDITIMI", nullable = false)
	private int timi;
	
	public final static String TIMI = "Tiempo mínimo de ejecución";
	
	@Column(name = "STDINUER", nullable = false)
	private int nuer;

	public final static String NUER = "Número de errores";

	public final static String FEIN = "Fecha de inicio";
	public final static String FEFI = "Fecha de fin";
	
	public String key() {
		return Stdi.key(fech, ctrl);
	}
	
	public final static String key(long fech, String ctrl) {
		return StringUtil.extend(fech, 8) + '|' 
			 + ctrl;
	}
	
	public void validate() throws Exception {
	
		validateFieldDate(fech, Stdi.FECH);
		validateFieldString(ctrl, 30, Stdi.CTRL);
		validateFieldLong(tota, 0, 99999999, Stdi.TOTA);
		validateFieldDouble(time, 0, 9999999999.9999, Stdi.TIME);
		validateFieldLong(tima, 0, 99999999, Stdi.TIMA);
		validateFieldDouble(timi, 0, 99999999, Stdi.TIMI);
		validateFieldLong(nuer, 0, 99999999, Stdi.NUER);
	}
	
	public int getFech() {
		return fech;
	}

	public void setFech(int fech) {
		this.fech = fech;
	}

	public String getCtrl() {
		return ctrl;
	}

	public void setCtrl(String ctrl) {
		this.ctrl = ctrl;
	}

	public int getTota() {
		return tota;
	}

	public void setTota(int tota) {
		this.tota = tota;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public int getTima() {
		return tima;
	}

	public void setTima(int tima) {
		this.tima = tima;
	}

	public int getTimi() {
		return timi;
	}

	public void setTimi(int timi) {
		this.timi = timi;
	}

	public int getNuer() {
		return nuer;
	}

	public void setNuer(int nuer) {
		this.nuer = nuer;
	}
}

class StdiPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STDIFECH", unique = true, nullable = false)
	private int fech;

	@Id
	@Column(name = "STDICTRL", unique = true, nullable = false)
	private String ctrl;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof StdiPK) {
			StdiPK obj = (StdiPK)o;
			if (obj.fech == this.fech && obj.ctrl.equals(this.ctrl)) {
				return true;
			}
		}
		return false;
	}
}