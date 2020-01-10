package es.ldrsoftware.core.sts.entity;

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
@Table(name = "STST")
public class Stst extends BaseDTO implements Serializable {

	private static final long serialVersionUID = 3569136645585706328L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "STSTIDEN", nullable = false)
	private long iden;
	
	public final static String IDEN = "Identificador de estadística";
	
	@Column(name = "STSTCTRL", nullable = false)
	private String ctrl;
	
	public final static String CTRL = "Controlador de estadística";
	
	@Column(name = "STSTINST", nullable = false)
	private long inst;
	
	public final static String INST = "Instalación de estadística";
	
	@Column(name = "STSTUSUA", nullable = false)
	private String usua;
	
	public final static String USUA = "Usuario de estadística";
	
	@Column(name = "STSTFEEJ", nullable = false)
	private int feej;
	
	public final static String FEEJ = "Fecha de ejecución";
	
	@Column(name = "STSTHOEJ", nullable = false)
	private int hoej;
	
	public final static String HOEJ = "Hora de ejecución";
	
	@Column(name = "STSTTIEJ", nullable = false)
	private int tiej;
	
	public final static String TIEJ = "Tiempo de ejecución";
	
	@Column(name = "STSTREEJ", nullable = false)
	private String reej;
	
	public final static String REEJ = "Resultado de ejecución";
	
	@Column(name = "STSTNOTF", nullable = false)
	private String notf;

	public final static String NOTF = "Notificación de ejecución";
	
	public final static String FECH = "Fecha de estadísticas";
	public final static String INEJ = "Inicio de ejecución";
	public final static String FIEJ = "Fin de ejecución";
	
	public String key() {
		return Stst.key(iden);
	}
	
	public final static String key(long iden) {
		return StringUtil.extend(iden, 18);
	}
	
	public void validate() throws Exception {
		validateFieldString(ctrl, 30, Stst.CTRL);
		validateFieldLong(inst, 0, 999999999, Stst.INST);
		validateFieldString(usua, 30, Stst.USUA);
		validateFieldDate(feej, Stst.FEEJ);
		validateFieldTime(tiej, Stst.TIEJ);
		validateFieldLong(tiej, 0, 99999999, Stst.TIEJ);
		validateFieldDomain(reej, Stst.REEJ, LiteData.LT_ST_STSTREEJ);
		
		if (!LiteData.LT_EL_STSTREEJ_OK.equals(reej)) {
			validateFieldString(notf, 10, Stst.NOTF);
		}
	}
	
	public long getIden() {
		return iden;
	}

	public void setIden(long iden) {
		this.iden = iden;
	}

	public String getCtrl() {
		return ctrl;
	}

	public void setCtrl(String ctrl) {
		this.ctrl = ctrl;
	}

	public long getInst() {
		return inst;
	}

	public void setInst(long inst) {
		this.inst = inst;
	}

	public String getUsua() {
		return usua;
	}

	public void setUsua(String usua) {
		this.usua = usua;
	}

	public int getFeej() {
		return feej;
	}

	public void setFeej(int feej) {
		this.feej = feej;
	}

	public int getHoej() {
		return hoej;
	}

	public void setHoej(int hoej) {
		this.hoej = hoej;
	}

	public int getTiej() {
		return tiej;
	}

	public void setTiej(int tiej) {
		this.tiej = tiej;
	}

	public String getReej() {
		return reej;
	}

	public void setReej(String reej) {
		this.reej = reej;
	}

	public String getNotf() {
		return notf;
	}
	
	public void setNotf(String notf) {
		this.notf = notf;
	}
}
