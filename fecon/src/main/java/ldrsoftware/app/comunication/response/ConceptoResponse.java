package ldrsoftware.app.comunication.response;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import ldrsoftware.app.domain.HConc;

public class ConceptoResponse extends BaseResponse {

	private Map<Integer, HConc> concMap;
	private List<HConc> concList;

	public Map<Integer, HConc> getConcMap() {
		return concMap;
	}

	public void setConcMap(Map<Integer, HConc> concMap) {
		this.concMap = concMap;
	}
	
	public void setConcMap(List<HConc> concList) {
		concMap = new HashMap<Integer, HConc>();
		ListIterator<HConc> it = concList.listIterator();
		while(it.hasNext()) {
			HConc conc = it.next();
			concMap.put(new Integer(conc.getIden()), conc);
		}
	}

	public List<HConc> getConcList() {
		return concList;
	}

	public void setConcList(List<HConc> concList) {
		this.concList = concList;
	}
	
}
