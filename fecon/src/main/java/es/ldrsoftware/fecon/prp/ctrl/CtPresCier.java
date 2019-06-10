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
import es.ldrsoftware.fecon.prp.bs.BsPresCier;
import es.ldrsoftware.fecon.prp.bs.BsPresCierArea;

@RestController
public class CtPresCier extends BaseController {

	@Autowired
	public BsPresCier bsPresCier;

	public CtPresCier() {
		super("ctPresCier");
	}
	
	@RequestMapping(value="/angular/pres/cier", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctPresCier(HttpServletRequest servletRqt, @RequestBody CtPresCierRqt rqt) {
		BsPresCierArea area = new BsPresCierArea();
		area.IN.anua = rqt.anua;
		area.IN.mesp = rqt.mesp;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsPresCier.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsPresCierArea area = (BsPresCierArea)a;

		response.OUTPUT.put("anua", area.OUT.anua);
		response.OUTPUT.put("mesp", area.OUT.mesp);		
	}
}
