package ldrsoftware.app.comunication.request;

public class CateRequest extends BaseRequest {

	private int iden;
	private String desc;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
