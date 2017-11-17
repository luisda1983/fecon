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
import es.ldrsoftware.core.fwk.bs.BsAvisGet;
import es.ldrsoftware.core.fwk.bs.BsAvisGetArea;

@RestController
public class CtAvisGet extends BaseController {

	@Autowired
	public BsAvisGet bsAvisGet;

	public CtAvisGet() {
		super("ctAvisGet");
	}
	
	@RequestMapping(value="/angular/avis/get", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctAvisGet(HttpServletRequest servletRqt, @RequestBody CtAvisGetRqt rqt) {
		BsAvisGetArea area = new BsAvisGetArea();
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsAvisGet.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsAvisGetArea area = (BsAvisGetArea)a;
		
		response.OUTPUT.put("avisList", area.OUT.avisList);
	}
}
