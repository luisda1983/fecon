package es.ldrsoftware.core.mnu.ctrl;

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
import es.ldrsoftware.core.mnu.bs.BsMenuGet;
import es.ldrsoftware.core.mnu.bs.BsMenuGetArea;

@RestController
public class CtMenuGet extends BaseController {

	@Autowired
	public BsMenuGet bsMenuGet;

	public CtMenuGet() {
		super("ctMenuGet");
	}
	
	@RequestMapping(value="/angular/menu/get", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctMenuGet(HttpServletRequest servletRqt, @RequestBody CtMenuGetRqt rqt) {
		BsMenuGetArea area = new BsMenuGetArea();
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		((BsMenuGetArea)a).IN.perf = SESSION.get().perf;
		bsMenuGet.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsMenuGetArea area = (BsMenuGetArea)a;
		
		response.OUTPUT.put("menu", area.OUT.ctmnList);
	}
}
