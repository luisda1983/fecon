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
import es.ldrsoftware.fecon.prp.bs.BsPresCalc;
import es.ldrsoftware.fecon.prp.bs.BsPresCalcArea;

@RestController
public class CtPresCalc extends BaseController {

	@Autowired
	public BsPresCalc bsPresCalc;

	public CtPresCalc() {
		super("ctPresCalc");
	}
	
	@RequestMapping(value="/angular/pres/calc", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctPresCalc(HttpServletRequest servletRqt, @RequestBody CtPresCalcRqt rqt) {
		BsPresCalcArea area = new BsPresCalcArea();
		area.IN.fech = rqt.fech;
		area.IN.cate = rqt.cate;
		area.IN.conc = rqt.conc;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsPresCalc.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsPresCalcArea area = (BsPresCalcArea)a;
		
		response.OUTPUT.put("pres", area.OUT.pres);

	}
}
