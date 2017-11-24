package ldrsoftware.app.comunication.response;

import java.util.List;

import ldrsoftware.app.domain.HHcon;

public class HconResponse extends BaseResponse {

	private List<HHcon> hconList;

	public List<HHcon> getHconList() {
		return hconList;
	}

	public void setHconList(List<HHcon> hconList) {
		this.hconList = hconList;
	}
	
}
