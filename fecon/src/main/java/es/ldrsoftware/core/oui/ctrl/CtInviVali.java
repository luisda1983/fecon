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
import es.ldrsoftware.core.oui.bs.BsInviVali;
import es.ldrsoftware.core.oui.bs.BsInviValiArea;

@RestController
public class CtInviVali extends BaseController {

	@Autowired
	public BsInviVali bsInviVali;

	public CtInviVali() {
		super("ctInviVali");
	}
	
	@RequestMapping(value="/angular/invi/vali", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctInviVali(HttpServletRequest servletRqt, @RequestBody CtInviValiRqt rqt) {
		BsInviValiArea area = new BsInviValiArea();
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		bsInviVali.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsInviValiArea area = (BsInviValiArea)a;
		response.OUTPUT.put("invi", area.OUT.invi);
		response.OUTPUT.put("exus", area.OUT.exus);
	}
}
