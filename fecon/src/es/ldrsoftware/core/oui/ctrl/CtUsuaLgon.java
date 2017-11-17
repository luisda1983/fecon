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
import es.ldrsoftware.core.oui.bs.BsUsuaLgon;
import es.ldrsoftware.core.oui.bs.BsUsuaLgonArea;

@RestController
public class CtUsuaLgon extends BaseController {

	@Autowired
	public BsUsuaLgon bsUsuaLgon;

	public CtUsuaLgon() {
		super("ctUsuaLgon");
	}
	
	@RequestMapping(value="/angular/usua/lgon", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctUsuaExit(HttpServletRequest servletRqt, @RequestBody CtUsuaLgonRqt rqt) {
		BsUsuaLgonArea area = new BsUsuaLgonArea();
		area.IN.iden = rqt.iden;
		area.IN.pass = rqt.pass;
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		bsUsuaLgon.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsUsuaLgonArea area = (BsUsuaLgonArea)a;
		response.OUTPUT.put("sesi", area.OUT.sesi);
		response.OUTPUT.put("usua", area.OUT.usua);
	}
}
