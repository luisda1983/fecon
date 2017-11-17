package es.ldrsoftware.core.fwk.ctrl;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.ldrsoftware.core.arq.BaseController;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.ResponseArea;
import es.ldrsoftware.core.fwk.bs.BsLiteList;
import es.ldrsoftware.core.fwk.bs.BsLiteListArea;
import es.ldrsoftware.core.fwk.entity.Lite;

@RestController
public class CtLiteList extends BaseController {

	@Autowired
	public BsLiteList bsLiteList;

	public CtLiteList() {
		super("ctLiteList");
	}
	
	@RequestMapping(value="/angular/lite/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctLiteList(HttpServletRequest servletRqt, @RequestBody CtLiteListRqt rqt) {
		BsLiteListArea area = new BsLiteListArea();
		area.IN.tbla = rqt.tbla;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsLiteList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsLiteListArea area = (BsLiteListArea)a;
		
		response.OUTPUT.put("liteList", area.OUT.liteList);
		
		ListIterator<Lite> it = area.OUT.liteList.listIterator();
		Map<String, Lite> liteMap = new HashMap<String, Lite>();
		while (it.hasNext()) {
			Lite lite = it.next();
			liteMap.put(lite.getClav(), lite);
		}
		
		response.OUTPUT.put("liteMap" , liteMap);
	}
}
