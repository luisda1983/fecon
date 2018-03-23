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
import es.ldrsoftware.core.oui.bs.BsInstList;
import es.ldrsoftware.core.oui.bs.BsInstListArea;

@RestController
public class CtInstList extends BaseController {

	@Autowired
	public BsInstList bsInstList;

	public CtInstList() {
		super("ctInstList");
	}
	
	@RequestMapping(value="/angular/inst/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctInviList(HttpServletRequest servletRqt, @RequestBody CtInstListRqt rqt) {
		BsInstListArea area = new BsInstListArea();
		if (rqt.esta == null || "".equals(rqt.esta)) {
			area.IN.tipo = BsInstList.INST_LIST_FULL;
		} else {
			area.IN.tipo = BsInstList.INST_LIST_ESTA;
			area.IN.esta = rqt.esta;
		}
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		bsInstList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsInstListArea area = (BsInstListArea)a;
		response.OUTPUT.put("instList", area.OUT.instList);
	}
}
