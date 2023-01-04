package es.ldrsoftware.fecon.cnt.ctrl;

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
import es.ldrsoftware.fecon.cnt.bs.BsTradList;
import es.ldrsoftware.fecon.cnt.bs.BsTradListArea;

@RestController
public class CtTradList extends BaseController {

	@Autowired
	public BsTradList bsTradList;

	public CtTradList() {
		super("ctTradList");
	}
	
	@RequestMapping(value="/angular/trad/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctTradList(HttpServletRequest servletRqt, @RequestBody CtTradListRqt rqt) {
		BsTradListArea area = new BsTradListArea();
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsTradList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsTradListArea area = (BsTradListArea)a;
		
		response.OUTPUT.put("tradList", area.OUT.tradList);
	}
}
