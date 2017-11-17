package ldrsoftware.app.comunication.request;

public class HconRequest extends BaseRequest {

	private int iden;
	private int anua;
	private int mesp;
	private int cate;
	private int conc;
	private int cuen;
	private double impo;
	private int fval;
	private String adic;
	private String desc;
	
	
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

	public int getMesp() {
		return mesp;
	}

	public void setMesp(int mesp) {
		this.mesp = mesp;
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
	
	public int getCuen() {
		return cuen;
	}

	public void setCuen(int cuen) {
		this.cuen = cuen;
	}

	public double getImpo() {
		return impo;
	}
	
	public void setImpo(double impo) {
		this.impo = impo;
	}
	
	public int getFval() {
		return fval;
	}
	
	public void setFval(int fval) {
		this.fval = fval;
	}
	
	public String getAdic() {
		return adic;
	}
	
	public void setAdic(String adic) {
		this.adic = adic;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
