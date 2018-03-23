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
import es.ldrsoftware.core.oui.bs.BsInstInac;
import es.ldrsoftware.core.oui.bs.BsInstInacArea;

@RestController
public class CtInstInac extends BaseController {

	@Autowired
	public BsInstInac bsInstInac;

	public CtInstInac() {
		super("ctInstInac");
	}
	
	@RequestMapping(value="/angular/inst/inac", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCtmnDesa(HttpServletRequest servletRqt, @RequestBody CtInstInacRqt rqt) {
		BsInstInacArea area = new BsInstInacArea();
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsInstInac.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsInstInacArea area = (BsInstInacArea)a;
		
		response.OUTPUT.put("inst", area.OUT.inst);
	}
}
