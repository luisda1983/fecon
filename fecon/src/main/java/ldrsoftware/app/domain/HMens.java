package ldrsoftware.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "THMENS")
public class HMens {

	@Id
	@Column(name = "MENSIDEN", unique = true, nullable = false)
	private String iden;
	
	@Column(name = "MENSTIPO", unique = true, nullable = false)
	private String tipo;
	
	@Column(name = "MENSDESC", nullable = false)
	private String desc;
	
	public String getIden() {
		return iden;
	}

	public void setIden(String iden) {
		this.iden = iden;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Mens [iden=" + iden + ", "
				   + "tipo=" + tipo + ", "
				   + "desc=" + desc + ", "
				   ;
	}		
}