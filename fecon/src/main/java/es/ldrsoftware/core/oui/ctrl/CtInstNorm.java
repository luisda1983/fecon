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
import es.ldrsoftware.core.oui.bs.BsInstNorm;
import es.ldrsoftware.core.oui.bs.BsInstNormArea;

@RestController
public class CtInstNorm extends BaseController {

	@Autowired
	public BsInstNorm bsInstNorm;

	public CtInstNorm() {
		super("ctInstNorm");
	}
	
	@RequestMapping(value="/angular/inst/norm", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCtmnActi(HttpServletRequest servletRqt, @RequestBody CtInstNormRqt rqt) {
		BsInstNormArea area = new BsInstNormArea();
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsInstNorm.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsInstNormArea area = (BsInstNormArea)a;
		
		response.OUTPUT.put("inst", area.OUT.inst);
	}
}
