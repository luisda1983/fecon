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
import es.ldrsoftware.core.fwk.bs.BsLiteGetk;
import es.ldrsoftware.core.fwk.bs.BsLiteGetkArea;

@RestController
public class CtLiteGet extends BaseController {

	@Autowired
	public BsLiteGetk bsLiteGetk;

	public CtLiteGet() {
		super("ctLiteGet");
	}
	
	@RequestMapping(value="/angular/lite/get", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctLiteGet(HttpServletRequest servletRqt, @RequestBody CtLiteGetRqt rqt) {
		BsLiteGetkArea area = new BsLiteGetkArea();
		area.IN.tbla = rqt.tbla;
		area.IN.clav = rqt.clav;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsLiteGetk.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsLiteGetkArea area = (BsLiteGetkArea)a;
		
		response.OUTPUT.put("lite", area.OUT.lite);
	}
}
