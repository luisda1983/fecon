package es.ldrsoftware.fecon.cnt.ctrl;

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
import es.ldrsoftware.fecon.cnt.bs.BsHconAnul;
import es.ldrsoftware.fecon.cnt.bs.BsHconAnulArea;

@RestController
public class CtHconAnul extends BaseController {

	@Autowired
	public BsHconAnul bsHconAnul;

	public CtHconAnul() {
		super("ctHconAnul");
	}
	
	@RequestMapping(value="/angular/hcon/anul", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctHconAnul(HttpServletRequest servletRqt, @RequestBody CtHconAnulRqt rqt) {
		BsHconAnulArea area = new BsHconAnulArea();
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsHconAnul.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		//BsHconAnulArea area = (BsHconAnulArea)a;
	}
}
