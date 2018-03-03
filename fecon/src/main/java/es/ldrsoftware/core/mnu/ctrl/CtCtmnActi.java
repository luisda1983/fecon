package es.ldrsoftware.core.mnu.ctrl;

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
import es.ldrsoftware.core.mnu.bs.BsCtmnActi;
import es.ldrsoftware.core.mnu.bs.BsCtmnActiArea;

@RestController
public class CtCtmnActi extends BaseController {

	@Autowired
	public BsCtmnActi bsCtmnActi;

	public CtCtmnActi() {
		super("ctCtmnActi");
	}
	
	@RequestMapping(value="/angular/ctmn/acti", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCtmnActi(HttpServletRequest servletRqt, @RequestBody CtCtmnActiRqt rqt) {
		BsCtmnActiArea area = new BsCtmnActiArea();
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsCtmnActi.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsCtmnActiArea area = (BsCtmnActiArea)a;
		
		response.OUTPUT.put("ctmn", area.OUT.ctmn);
	}
}
