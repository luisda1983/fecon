package ldrsoftware.app.work;


import es.ldrsoftware.core.fwk.entity.Sesi;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.Usua;

public class WorkClass {

	private String ip;
	private int fope;
	private int hope;
	//Convivencia
	//private HSesi sesi;
	private Sesi sesi;
	//private HUsua usua;
	private Usua usua;
	//Convivencia
	//private HInst inst;
	private Inst inst;
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public int getFope() {
		return fope;
	}
	
	public void setFope(int fope) {
		this.fope = fope;
	}
	
	public int getHope() {
		return hope;
	}
	
	public void setHope(int hope) {
		this.hope = hope;
	}

	public Sesi getSesi() {
		return sesi;
	}

	public void setSesi(Sesi sesi) {
		this.sesi = sesi;
	}

	public Usua getUsua() {
		return usua;
	}

	public void setUsua(Usua usua) {
		this.usua = usua;
	}

	public Inst getInst() {
		return inst;
	}

	public void setInst(Inst inst) {
		this.inst = inst;
	}

}
