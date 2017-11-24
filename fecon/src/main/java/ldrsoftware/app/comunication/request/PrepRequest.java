package ldrsoftware.app.comunication.request;

public class PrepRequest extends BaseRequest {

	private int iden;
	private int anua;
	private int cate;
	private int conc;
	private String peri;
	private int mini;
	private int mfin;
	private float impo;

	public int getIden() {
		return iden;
	}

	public void setIden(int iden) {
		this.iden = iden;
	}

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
	
	public int getConc() {
		return conc;
	}

	public void setConc(int conc) {
		this.conc = conc;
	}

	public String getPeri() {
		return peri;
	}

	public void setPeri(String peri) {
		this.peri = peri;
	}

	public int getMini() {
		return mini;
	}

	public void setMini(int mini) {
		this.mini = mini;
	}

	public int getMfin() {
		return mfin;
	}

	public void setMfin(int mfin) {
		this.mfin = mfin;
	}

	public float getImpo() {
		return impo;
	}

	public void setImpo(float impo) {
		this.impo = impo;
	}

}
