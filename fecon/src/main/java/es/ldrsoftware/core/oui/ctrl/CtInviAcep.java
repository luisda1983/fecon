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
import es.ldrsoftware.core.oui.bs.BsInviAcep;
import es.ldrsoftware.core.oui.bs.BsInviAcepArea;

@RestController
public class CtInviAcep extends BaseController {

	@Autowired
	public BsInviAcep bsInviAcep;

	public CtInviAcep() {
		super("ctInviAcep");
	}
	
	@RequestMapping(value="/angular/invi/acep", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctInviAcep(HttpServletRequest servletRqt, @RequestBody CtInviAcepRqt rqt) {
		BsInviAcepArea area = new BsInviAcepArea();
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		bsInviAcep.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsInviAcepArea area = (BsInviAcepArea)a;
		response.OUTPUT.put("invi", area.OUT.invi);
	}
}
