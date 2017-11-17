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
import es.ldrsoftware.core.oui.bs.BsInviRech;
import es.ldrsoftware.core.oui.bs.BsInviRechArea;

@RestController
public class CtInviRech extends BaseController {

	@Autowired
	public BsInviRech bsInviRech;

	public CtInviRech() {
		super("ctInviRech");
	}
	
	@RequestMapping(value="/angular/invi/rech", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctInviRech(HttpServletRequest servletRqt, @RequestBody CtInviRechRqt rqt) {
		BsInviRechArea area = new BsInviRechArea();
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		bsInviRech.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsInviRechArea area = (BsInviRechArea)a;
		response.OUTPUT.put("invi", area.OUT.invi);
	}
}
