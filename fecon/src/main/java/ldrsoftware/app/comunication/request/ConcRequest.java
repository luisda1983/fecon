package ldrsoftware.app.comunication.request;

public class ConcRequest extends BaseRequest {

	private int iden;
	private int cate;
	private String desc;
	private String tipo;
	private int domi;
	private boolean acti;
	private boolean full;
	private boolean down;
	private boolean defa;

	public int getIden() {
		return iden;
	}

	public void setIden(int iden) {
		this.iden = iden;
	}

	public int getCate() {
		return cate;
	}

	public void setCate(int cate) {
		this.cate = cate;
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

	public int getDomi() {
		return domi;
	}

	public void setDomi(int domi) {
		this.domi = domi;
	}

	public boolean isActi() {
		return acti;
	}

	public void setActi(boolean acti) {
		this.acti = acti;
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isDefa() {
		return defa;
	}

	public void setDefa(boolean defa) {
		this.defa = defa;
	}

}
