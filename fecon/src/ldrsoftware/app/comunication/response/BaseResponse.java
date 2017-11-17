package ldrsoftware.app.comunication.response;

import java.util.ArrayList;
import java.util.List;

import ldrsoftware.app.domain.HMens;

public class BaseResponse {

	private HMens error;
	private List<HMens> warnList;
	private List<HMens> infoList;
	
	private String status = ST_OK;
	
	public final static String ST_OK      = "OK";
	public final static String ST_ERROR   = "ER";
	public final static String ST_SESSION = "SS";
	
	public HMens getError() {
		return error;
	}
	
	public void setError(HMens error) {
		this.error = error;
	}
	
	public List<HMens> getWarnList() {
		return warnList;
	}
	
	public void setWarnList(List<HMens> warnList) {
		this.warnList = warnList;
	}
	
	public List<HMens> getInfoList() {
		return infoList;
	}
	
	public void setInfoList(List<HMens> infoList) {
		this.infoList = infoList;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
		if (BaseResponse.ST_ERROR.equals(status)) {
			this.warnList = new ArrayList<HMens>();
			this.infoList = new ArrayList<HMens>();
		}
	}
	
	public void addWarning(HMens mens) {
		if (warnList == null) {
			warnList = new ArrayList<HMens>();
		}
		warnList.add(mens);
	}
	
	public void addInfo(HMens mens) {
		if (infoList == null) {
			infoList = new ArrayList<HMens>();
		}
		infoList.add(mens);
	}
}
