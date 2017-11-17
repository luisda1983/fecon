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
import es.ldrsoftware.core.oui.bs.BsInviSoli;
import es.ldrsoftware.core.oui.bs.BsInviSoliArea;

@RestController
public class CtInviSoli extends BaseController {

	@Autowired
	public BsInviSoli bsInviSoli;

	public CtInviSoli() {
		super("ctInviSoli");
	}
	
	@RequestMapping(value="/angular/invi/soli", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctInviSoli(HttpServletRequest servletRqt, @RequestBody CtInviSoliRqt rqt) {
		BsInviSoliArea area = new BsInviSoliArea();
		area.IN.mail = rqt.mail;
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		bsInviSoli.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsInviSoliArea area = (BsInviSoliArea)a;
		response.OUTPUT.put("invi", area.OUT.invi);
	}
}
