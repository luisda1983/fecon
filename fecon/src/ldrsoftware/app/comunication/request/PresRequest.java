package ldrsoftware.app.comunication.request;

public class PresRequest extends BaseRequest {

	private int anua;
	private int mesp;
	private int cate;
	private int conc;
	
	private boolean npre;

	public int getAnua() {
		return anua;
	}

	public void setAnua(int anua) {
		this.anua = anua;
	}
	
	public int getCate() {
		return cate;
	}
	
	public void setCate(int cate) {
		this.cate = cate;
	}
	
	public int getMesp() {
		return mesp;
	}
	
	public void setMesp(int mesp) {
		this.mesp = mesp;
	}

	public int getConc() {
		return conc;
	}

	public void setConc(int conc) {
		this.conc = conc;
	}

	public boolean isNpre() {
		return npre;
	}

	public void setNpre(boolean npre) {
		this.npre = npre;
	}
}
