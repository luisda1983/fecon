package es.ldrsoftware.core.mnu.entity;

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
@Table(name = "DTMN")
@IdClass(DtmnPK.class)
public class Dtmn extends BaseDTO implements Serializable {

	private static final long serialVersionUID = -1074116688488083826L;

	@Id
	@Column(name = "DTMNCTMN", unique = true, nullable = false)
	private    int ctmn;

	public final static String CTMN = "Categoría de menú";
	
	@Id
	@Column(name = "DTMNIDEN", unique = true, nullable = false)
	private    int iden;
	
	public final static String IDEN = "Identificador de detalle de menú";
	
	@Column(name = "DTMNDESC", nullable = false)
	private String desc;
	
	public final static String DESC = "Descripción de detalle de menú";
	
	@Column(name = "DTMNACTI", nullable = false)
	private String acti;
	
	public final static String ACTI = "Indicador de detalle de menú activado";
	
	@Column(name = "DTMNORDE", nullable = false)
	private    int orde;
	
	public final static String ORDE = "Orden de detalle de menú";
	
	@Column(name = "DTMNPATH", nullable = false)
	private String path;
	
	public final static String PATH = "Path de detalle de menú";
	
	@Column(name = "DTMNICON", nullable = false)
	private String icon;

	public final static String ICON = "Icono asociado al detalle de menú";
	
	public String key() {
		return key(ctmn, iden);
	}
	
	public final static String key(long ctmn, long iden) {
		return StringUtil.extend(ctmn, 10) + "|"
			 + StringUtil.extend(iden, 10);
	}
	
	public void validate() throws Exception {
		validateFieldLong(ctmn, 0, 9999, CTMN);
		validateFieldLong(iden, 0, 9999, IDEN);
		validateFieldString(desc, 40, DESC);
		validateFieldDomain(acti, ACTI, LiteData.LT_ST_BOOL);
		validateFieldLong(orde, 0, 9999, ORDE);
		validateFieldString(path, 30, PATH);
		validateFieldString(icon, 50, ICON);					
	}
	
	public int getCtmn() {
		return ctmn;
	}

	public void setCtmn(int ctmn) {
		this.ctmn = ctmn;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}

class DtmnPK implements Serializable {

	private static final long serialVersionUID = -6710988508175895822L;

	@Id
	@Column(name = "DTMNCTMN", unique = true, nullable = false)
	private    int ctmn;

	@Id
	@Column(name = "DTMNIDEN", unique = true, nullable = false)
	private    int iden;
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof DtmnPK) {
			DtmnPK obj = (DtmnPK)o;
			if (obj.ctmn == this.ctmn && obj.iden == this.iden) {
				return true;
			}
		}
		return false;
	}
}