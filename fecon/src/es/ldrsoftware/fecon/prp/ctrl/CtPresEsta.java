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
import es.ldrsoftware.fecon.prp.bs.BsPresEsta;
import es.ldrsoftware.fecon.prp.bs.BsPresEstaArea;

@RestController
public class CtPresEsta extends BaseController {

	@Autowired
	public BsPresEsta bsPresEsta;

	public CtPresEsta() {
		super("ctPresEsta");
	}
	
	@RequestMapping(value="/angular/pres/esta", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctPresList(HttpServletRequest servletRqt, @RequestBody CtPresEstaRqt rqt) {
		BsPresEstaArea area = new BsPresEstaArea();
		area.IN.anua = rqt.anua;
		area.IN.mesp = rqt.mesp;
		area.IN.cate = rqt.cate;
		area.IN.conc = rqt.conc;
		area.IN.esta = rqt.esta;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsPresEsta.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsPresEstaArea area = (BsPresEstaArea)a;
		
		response.OUTPUT.put("pres", area.OUT.pres);

	}
}
