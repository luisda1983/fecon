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
import es.ldrsoftware.core.mnu.bs.BsDtmnDesa;
import es.ldrsoftware.core.mnu.bs.BsDtmnDesaArea;

@RestController
public class CtDtmnDesa extends BaseController {

	@Autowired
	public BsDtmnDesa bsDtmnDesa;

	public CtDtmnDesa() {
		super("ctDtmnDesa");
	}
	
	@RequestMapping(value="/angular/dtmn/desa", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctDtmnDesa(HttpServletRequest servletRqt, @RequestBody CtDtmnDesaRqt rqt) {
		BsDtmnDesaArea area = new BsDtmnDesaArea();
		area.IN.ctmn = rqt.ctmn;
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsDtmnDesa.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsDtmnDesaArea area = (BsDtmnDesaArea)a;
		
		response.OUTPUT.put("dtmn", area.OUT.dtmn);
	}
}
