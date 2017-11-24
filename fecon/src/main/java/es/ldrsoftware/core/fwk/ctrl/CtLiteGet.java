package es.ldrsoftware.core.fwk.ctrl;

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
import es.ldrsoftware.core.fwk.bs.BsLiteGet;
import es.ldrsoftware.core.fwk.bs.BsLiteGetArea;

@RestController
public class CtLiteGet extends BaseController {

	@Autowired
	public BsLiteGet bsLiteGet;

	public CtLiteGet() {
		super("ctLiteGet");
	}
	
	@RequestMapping(value="/angular/lite/get", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctLiteGet(HttpServletRequest servletRqt, @RequestBody CtLiteGetRqt rqt) {
		BsLiteGetArea area = new BsLiteGetArea();
		area.IN.tbla = rqt.tbla;
		area.IN.clav = rqt.clav;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsLiteGet.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsLiteGetArea area = (BsLiteGetArea)a;
		
		response.OUTPUT.put("lite", area.OUT.lite);
	}
}
