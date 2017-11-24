package ldrsoftware.app.comunication.response;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import ldrsoftware.app.domain.HParm;

public class ParametroResponse extends BaseResponse {

	private List<HParm> parmList;
	private Map<String, HParm> parmMap;

	public List<HParm> getParmList() {
		return parmList;
	}

	public void setParmList(List<HParm> parmList) {
		this.parmList = parmList;
		
		parmMap = new HashMap<String, HParm>();
		ListIterator<HParm> it = parmList.listIterator();
		while(it.hasNext()) {
			HParm parm = it.next();
			parmMap.put(parm.getClav(), parm);
		}
	}
	
	public Map<String, HParm> getParmMap() {
		return this.parmMap;
	}
}
