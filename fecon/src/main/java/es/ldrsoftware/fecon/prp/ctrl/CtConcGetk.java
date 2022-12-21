package es.ldrsoftware.fecon.prp.ctrl;

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
import es.ldrsoftware.fecon.prp.bs.BsConcGetk;
import es.ldrsoftware.fecon.prp.bs.BsConcGetkArea;

@RestController
public class CtConcGetk extends BaseController {

	@Autowired
	public BsConcGetk bsConcGetk;

	public CtConcGetk() {
		super("ctConcGetk");
	}
	
	@RequestMapping(value="/angular/conc/get", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctConcGetk(HttpServletRequest servletRqt, @RequestBody CtConcGetkRqt rqt) {
		BsConcGetkArea area = new BsConcGetkArea();
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsConcGetk.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsConcGetkArea area = (BsConcGetkArea)a;
		
		response.OUTPUT.put("conc", area.OUT.conc);
	}
}
