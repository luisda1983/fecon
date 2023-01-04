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
import es.ldrsoftware.fecon.cnt.bs.BsTradGetk;
import es.ldrsoftware.fecon.cnt.bs.BsTradGetkArea;

@RestController
public class CtTradGet extends BaseController {

	@Autowired
	public BsTradGetk bsTradGetk;

	public CtTradGet() {
		super("ctTradGet");
	}
	
	@RequestMapping(value="/angular/trad/get", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctTradGet(HttpServletRequest servletRqt, @RequestBody CtTradGetRqt rqt) {
		BsTradGetkArea area = new BsTradGetkArea();
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsTradGetk.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsTradGetkArea area = (BsTradGetkArea)a;
		
		response.OUTPUT.put("trad", area.OUT.trad);
	}
}
