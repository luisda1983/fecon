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
import es.ldrsoftware.core.oui.bs.BsUsuaExit;
import es.ldrsoftware.core.oui.bs.BsUsuaExitArea;

@RestController
public class CtUsuaExit extends BaseController {

	@Autowired
	public BsUsuaExit bsUsuaExit;

	public CtUsuaExit() {
		super("ctUsuaExit");
	}
	
	@RequestMapping(value="/angular/usua/exit", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctUsuaExit(HttpServletRequest servletRqt, @RequestBody CtUsuaExitRqt rqt) {
		BsUsuaExitArea area = new BsUsuaExitArea();
		area.IN.sesi = rqt.sesi;
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		bsUsuaExit.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		
	}
}
