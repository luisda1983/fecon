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
@Table(name = "STME")
@IdClass(StmePK.class)
public class Stme extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 8498481307873855913L;

	@Id
	@Column(name = "STMEANYO", unique = true, nullable = false)
	private int anyo;

	public final static String ANYO = "Año de estadística";
	
	@Id
	@Column(name = "STMEMESS", unique = true, nullable = false)
	private int mess;

	public final static String MESS = "Mes de estadística";
	
	@Id
	@Column(name = "STMECTRL", unique = true, nullable = false)
	private String ctrl;
	
	public final static String CTRL = "Controlador de estadística anual";
	
	@Column(name = "STMETOTA", nullable = false)
	private int tota;
	
	public final static String TOTA = "Total de ejecuciones";
	
	@Column(name = "STMETIME", nullable = false)
	private float time;
	
	public final static String TIME = "Tiempo medio de ejecución";
	
	@Column(name = "STMETIMA", nullable = false)
	private int tima;
	
	public final static String TIMA = "Tiempo máximo de ejecución";
	
	@Column(name = "STMETIMI", nullable = false)
	private int timi;
	
	public final static String TIMI = "Tiempo mínimo de ejecución";
	
	@Column(name = "STMENUER", nullable = false)
	private int nuer;

	public final static String NUER = "Número de errores";

	public String key() {
		return Stme.key(anyo, mess, ctrl);
	}
	
	public final static String key(long anyo, long mess, String ctrl) {
		return StringUtil.extend(anyo, 4) + '|'
			 + StringUtil.extend(mess, 2) + '|'
			 + ctrl;
	}
	
	public void validate() throws Exception {
		validateFieldYear(anyo, Stme.ANYO);
		validateFieldMonth(mess, Stme.MESS);
		validateFieldString(ctrl, 30, Stme.CTRL);
		validateFieldLong(tota, 0, 99999999, Stme.TOTA);
		validateFieldDouble(time, 0, 99999999999.9999, Stme.TIME);
		validateFieldLong(tima, 0, 99999999, Stme.TIMA);
		validateFieldLong(timi, 0, 99999999, Stme.TIMI);
		validateFieldLong(nuer, 0, 99999999, Stme.NUER);
	}
	
	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public int getMess() {
		return mess;
	}

	public void setMess(int mess) {
		this.mess = mess;
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

class StmePK implements Serializable {

	private static final long serialVersionUID = -7310122972933975059L;

	@Id
	@Column(name = "STMEANYO", unique = true, nullable = false)
	private int anyo;

	@Id
	@Column(name = "STMEMESS", unique = true, nullable = false)
	private int mess;

	@Id
	@Column(name = "STMECTRL", unique = true, nullable = false)
	private String ctrl;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof StmePK) {
			StmePK obj = (StmePK)o;
			if (obj.anyo == this.anyo && obj.mess == this.mess && obj.ctrl.equals(this.ctrl)) {
				return true;
			}
		}
		return false;
	}
}