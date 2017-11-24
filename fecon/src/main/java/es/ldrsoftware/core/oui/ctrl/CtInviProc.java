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
import es.ldrsoftware.core.oui.bs.BsInviProc;
import es.ldrsoftware.core.oui.bs.BsInviProcArea;

@RestController
public class CtInviProc extends BaseController {

	@Autowired
	public BsInviProc bsInviProc;

	public CtInviProc() {
		super("ctInviProc");
	}
	
	@RequestMapping(value="/angular/invi/proc", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctInstRegi(HttpServletRequest servletRqt, @RequestBody CtInviProcRqt rqt) {
		BsInviProcArea area = new BsInviProcArea();
		area.IN.iden = rqt.iden;
		area.IN.usua = rqt.usua;
		area.IN.pass = rqt.pass;
		area.IN.cpas = rqt.cpas;
		area.IN.mail = rqt.mail;
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		bsInviProc.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsInviProcArea area = (BsInviProcArea)a;
		response.OUTPUT.put("invi", area.OUT.invi);
	}
}
