package es.ldrsoftware.core.oui.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.ldrsoftware.core.arq.BaseController;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.ResponseArea;
import es.ldrsoftware.core.oui.bs.BsInstPrem;
import es.ldrsoftware.core.oui.bs.BsInstPremArea;

@RestController
public class CtInstPrem extends BaseController {

	@Autowired
	public BsInstPrem bsInstPrem;

	public CtInstPrem() {
		super("ctInstPrem");
	}
	
	@RequestMapping(value="/angular/inst/prem", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCtmnActi(HttpServletRequest servletRqt, @RequestBody CtInstPremRqt rqt) {
		BsInstPremArea area = new BsInstPremArea();
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsInstPrem.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsInstPremArea area = (BsInstPremArea)a;
		
		response.OUTPUT.put("inst", area.OUT.inst);
	}
}
