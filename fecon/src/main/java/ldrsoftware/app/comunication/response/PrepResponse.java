package ldrsoftware.app.comunication.response;

import java.util.List;

import ldrsoftware.app.domain.HPrep;

public class PrepResponse extends BaseResponse {

	private HPrep prep;
	private List<HPrep> prepList;
	
	public HPrep getPrep() {
		return prep;
	}

	public void setPrep(HPrep prep) {
		this.prep = prep;
	}

	public List<HPrep> getPrepList() {
		return prepList;
	}

	public void setPrepList(List<HPrep> prepList) {
		this.prepList = prepList;
	}	
}
