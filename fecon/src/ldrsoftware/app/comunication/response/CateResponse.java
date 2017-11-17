package ldrsoftware.app.comunication.response;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import ldrsoftware.app.domain.HCate;

public class CateResponse extends BaseResponse {

	private Map<Integer, HCate> cateMap;
	private List<HCate> cateList;

	public Map<Integer, HCate> getCateMap() {
		return cateMap;
	}

	public void setCateMap(Map<Integer, HCate> cateMap) {
		this.cateMap = cateMap;
	}
	
	public void setCateMap(List<HCate> cateList) {
		cateMap = new HashMap<Integer, HCate>();
		ListIterator<HCate> it = cateList.listIterator();
		while(it.hasNext()) {
			HCate cate = it.next();
			cateMap.put(new Integer(cate.getIden()), cate);
		}
	}

	public List<HCate> getCateList() {
		return cateList;
	}

	public void setCateList(List<HCate> cateList) {
		this.cateList = cateList;
	}
}
