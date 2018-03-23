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
import es.ldrsoftware.core.oui.bs.BsUsuaInac;
import es.ldrsoftware.core.oui.bs.BsUsuaInacArea;

@RestController
public class CtUsuaInac extends BaseController {

	@Autowired
	public BsUsuaInac bsUsuaInac;

	public CtUsuaInac() {
		super("ctUsuaInac");
	}
	
	@RequestMapping(value="/angular/usua/inac", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctUsuaInac(HttpServletRequest servletRqt, @RequestBody CtUsuaInacRqt rqt) {
		BsUsuaInacArea area = new BsUsuaInacArea();
		area.IN.inst = rqt.inst;
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsUsuaInac.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsUsuaInacArea area = (BsUsuaInacArea)a;
		
		response.OUTPUT.put("usua", area.OUT.usua);
	}
}
