package es.ldrsoftware.core.oui.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.ldrsoftware.core.arq.BaseController;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.ResponseArea;
import es.ldrsoftware.core.oui.bs.BsInviList;
import es.ldrsoftware.core.oui.bs.BsInviListArea;

@RestController
public class CtInviList extends BaseController {

	@Autowired
	public BsInviList bsInviList;

	public CtInviList() {
		super("ctInviList");
	}
	
	@RequestMapping(value="/angular/invi/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctInviList(HttpServletRequest servletRqt, @RequestBody CtInviListRqt rqt) {
		BsInviListArea area = new BsInviListArea();
		area.IN.esta = rqt.esta;
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		if ("APM".equals(SESSION.get().perf)) {
			((BsInviListArea)a).IN.tipo = "I";
		} else {
			((BsInviListArea)a).IN.tipo = "U";
			((BsInviListArea)a).IN.inst = SESSION.get().inst;
		}
		bsInviList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsInviListArea area = (BsInviListArea)a;
		response.OUTPUT.put("inviList", area.OUT.inviList);
	}
}
