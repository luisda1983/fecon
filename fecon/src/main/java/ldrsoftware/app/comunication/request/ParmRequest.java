package ldrsoftware.app.comunication.request;

public class ParmRequest extends BaseRequest {

	private String tbla;
	private String clav;
	private boolean defa;
	private String defv;
	
	public String getTbla() {
		return tbla;
	}

	public void setTbla(String tbla) {
		this.tbla = tbla;
	}
	
	public String getClav() {
		return clav;
	}
	
	public void setClav(String clav) {
		this.clav = clav;
	}
	
	public boolean isDefa() {
		return defa;
	}
	
	public void setDefa(boolean defa) {
		this.defa = defa;
	}

	public String getDefv() {
		return defv;
	}

	public void setDefv(String defv) {
		this.defv = defv;
	}
}
