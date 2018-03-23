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
import es.ldrsoftware.core.oui.bs.BsInstActi;
import es.ldrsoftware.core.oui.bs.BsInstActiArea;

@RestController
public class CtInstActi extends BaseController {

	@Autowired
	public BsInstActi bsInstActi;

	public CtInstActi() {
		super("ctInstActi");
	}
	
	@RequestMapping(value="/angular/inst/acti", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCtmnActi(HttpServletRequest servletRqt, @RequestBody CtInstActiRqt rqt) {
		BsInstActiArea area = new BsInstActiArea();
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsInstActi.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsInstActiArea area = (BsInstActiArea)a;
		
		response.OUTPUT.put("inst", area.OUT.inst);
	}
}
