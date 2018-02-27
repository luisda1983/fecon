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
import es.ldrsoftware.core.oui.bs.BsInviEnvi;
import es.ldrsoftware.core.oui.bs.BsInviEnviArea;

@RestController
public class CtInviEnvi extends BaseController {

	@Autowired
	public BsInviEnvi bsInviEnvi;

	public CtInviEnvi() {
		super("ctInviEnvi");
	}
	
	@RequestMapping(value="/angular/invi/envi", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctInviList(HttpServletRequest servletRqt, @RequestBody CtInviEnviRqt rqt) {
		BsInviEnviArea area = new BsInviEnviArea();
		area.IN.mail = rqt.mail;
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		if ("APM".equals(SESSION.get().perf)) {
			((BsInviEnviArea)a).IN.tipo = LiteData.LT_EL_INVITIPO_INSTALACION;
		} else {
			((BsInviEnviArea)a).IN.tipo = LiteData.LT_EL_INVITIPO_USUARIO;
		}
		bsInviEnvi.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsInviEnviArea area = (BsInviEnviArea)a;
		response.OUTPUT.put("invi", area.OUT.invi);
	}
}
