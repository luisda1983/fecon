package ldrsoftware.app.comunication.request;

public class CuenRequest extends BaseRequest {

	private int    iden;
	private String desc;
	private String tipo;
	private float  sald;
	private int    orig;
	private int    dest;
	private float  impo;
	private int    cate;
	private int    conc;
	
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
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public float getSald() {
		return sald;
	}

	public void setSald(float sald) {
		this.sald = sald;
	}

	public int getOrig() {
		return orig;
	}

	public void setOrig(int orig) {
		this.orig = orig;
	}

	public int getDest() {
		return dest;
	}

	public void setDest(int dest) {
		this.dest = dest;
	}

	public float getImpo() {
		return impo;
	}

	public void setImpo(float impo) {
		this.impo = impo;
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

}
