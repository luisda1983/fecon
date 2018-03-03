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
import es.ldrsoftware.core.mnu.bs.BsDtmnForm;
import es.ldrsoftware.core.mnu.bs.BsDtmnFormArea;

@RestController
public class CtDtmnForm extends BaseController {

	@Autowired
	public BsDtmnForm bsDtmnForm;

	public CtDtmnForm() {
		super("ctDtmnForm");
	}
	
	@RequestMapping(value="/angular/dtmn/form", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctDtmnForm(HttpServletRequest servletRqt, @RequestBody CtDtmnFormRqt rqt) {
		BsDtmnFormArea area = new BsDtmnFormArea();
		area.IN.ctmn = rqt.ctmn;
		area.IN.iden = rqt.iden;
		area.IN.desc = rqt.desc;
		area.IN.acti = rqt.acti;
		area.IN.orde = rqt.orde;
		area.IN.path = rqt.path;
		area.IN.icon = rqt.icon;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsDtmnForm.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsDtmnFormArea area = (BsDtmnFormArea)a;
		
		response.OUTPUT.put("dtmn", area.OUT.dtmn);
	}
}
