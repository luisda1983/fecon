package ldrsoftware.app.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "THCATE")
@IdClass(HCatePK.class)
public class HCate {

	@Id
	@Column(name = "CATEINST", unique = true, nullable = false)
	private long inst;
	
	@Id
	@Column(name = "CATEIDEN", unique = true, nullable = false)
	private int iden;
	
	@Column(name = "CATEDESC", nullable = false)
	private String desc;

	@Column(name = "CATEORDE", nullable = false)
	private int orde;
	
	@Column(name = "CATEACTI", nullable = false)
	private boolean acti;

	@Transient
	private Map<Integer, HConc> concMap;
	
	public long getInst() {
		return this.inst;
	}
	
	public void setInst(long inst) {
		this.inst = inst;
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
	
	public int getOrde() {
		return orde;
	}

	public void setOrde(int orde) {
		this.orde = orde;
	}

	public boolean isActi() {
		return acti;
	}

	public void setActi(boolean acti) {
		this.acti = acti;
	}

	public Map<Integer, HConc> getConcMap() {
		return this.concMap;
	}
	
	public void setConcMap(Map<Integer, HConc> concMap) {
		this.concMap = concMap;
	}
	
	public void setConcMap(List<HConc> concList) {
		concMap = new HashMap<Integer, HConc>();
		ListIterator<HConc> it = concList.listIterator();
		while(it.hasNext()) {
			HConc conc = it.next();
			concMap.put(conc.getIden(), conc);
		}
	}
	
	@Override
	public String toString() {
		return "Cate [inst=" + inst + ", "
				   + "iden=" + iden + ", "
				   + "desc=" + desc + ", "
				   + "orde=" + orde + ", "
				   + "acti=" + acti + "]"
				   ;
	}		
}

class HCatePK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CATEINST", unique = true, nullable = false)
	private long inst;
	
	@Id
	@Column(name = "CATEIDEN", unique = true, nullable = false)
	private int iden;

	public long getInst() {
		return this.inst;
	}
	
	public void setInst(int inst) {
		this.inst = inst;
	}
	
	public int getIden() {
		return iden;
	}

	public void setIden(int iden) {
		this.iden = iden;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof HCatePK ) {
			HCatePK obj = (HCatePK) o;
			if (this.inst == obj.getInst() && this.iden == obj.getIden()) {
				return true;
			}
		}
		return false;
	}
}