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
import es.ldrsoftware.core.mnu.bs.BsDtmnActi;
import es.ldrsoftware.core.mnu.bs.BsDtmnActiArea;

@RestController
public class CtDtmnActi extends BaseController {

	@Autowired
	public BsDtmnActi bsDtmnActi;

	public CtDtmnActi() {
		super("ctDtmnActi");
	}
	
	@RequestMapping(value="/angular/dtmn/acti", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctDtmnActi(HttpServletRequest servletRqt, @RequestBody CtDtmnActiRqt rqt) {
		BsDtmnActiArea area = new BsDtmnActiArea();
		area.IN.ctmn = rqt.ctmn;
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsDtmnActi.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsDtmnActiArea area = (BsDtmnActiArea)a;
		
		response.OUTPUT.put("dtmn", area.OUT.dtmn);
	}
}
