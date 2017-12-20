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
import es.ldrsoftware.fecon.cnt.bs.BsHconPresGest;
import es.ldrsoftware.fecon.cnt.bs.BsHconPresGestArea;

@RestController
public class CtHconPresGest extends BaseController {

	@Autowired
	public BsHconPresGest bsHconPresGest;

	public CtHconPresGest() {
		super("ctHconPresGest");
	}
	
	@RequestMapping(value="/angular/hcon/pres/gest", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctHconPresGest(HttpServletRequest servletRqt, @RequestBody CtHconPresGestRqt rqt) {
		BsHconPresGestArea area = new BsHconPresGestArea();
		area.IN.acci = rqt.acci;
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsHconPresGest.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsHconPresGestArea area = (BsHconPresGestArea)a;
		
		response.OUTPUT.put("hcon", area.OUT.hcon);
	}
}
