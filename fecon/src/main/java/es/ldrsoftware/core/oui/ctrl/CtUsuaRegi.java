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
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.bs.BsUsuaRegi;
import es.ldrsoftware.core.oui.bs.BsUsuaRegiArea;

@RestController
public class CtUsuaRegi extends BaseController {

	@Autowired
	public BsUsuaRegi bsUsuaRegi;

	public CtUsuaRegi() {
		super("ctUsuaRegi");
	}
	
	@RequestMapping(value="/angular/usua/regi", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctUsuaExit(HttpServletRequest servletRqt, @RequestBody CtUsuaRegiRqt rqt) {
		BsUsuaRegiArea area = new BsUsuaRegiArea();
		area.IN.invi = false;
		area.IN.codi = rqt.codi;
		area.IN.numo = rqt.numo;
		area.IN.mail = rqt.mail;
		area.IN.iden = rqt.iden;
		area.IN.pass = rqt.pass;
		area.IN.cpas = rqt.cpas;
		area.IN.perf = LiteData.LT_EL_USUAPERF_USR;
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		bsUsuaRegi.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsUsuaRegiArea area = (BsUsuaRegiArea)a;
		response.OUTPUT.put("usua", area.OUT.usua);
	}
}
