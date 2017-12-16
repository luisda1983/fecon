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
import es.ldrsoftware.fecon.cnt.bs.BsCuenTras;
import es.ldrsoftware.fecon.cnt.bs.BsCuenTrasArea;

@RestController
public class CtCuenTras extends BaseController {

	@Autowired
	public BsCuenTras bsCuenTras;

	public CtCuenTras() {
		super("ctCuenTras");
	}
	
	@RequestMapping(value="/angular/cuen/tras", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCuenTras(HttpServletRequest servletRqt, @RequestBody CtCuenTrasRqt rqt) {
		BsCuenTrasArea area = new BsCuenTrasArea();
		area.IN.ctor = rqt.ctor;
		area.IN.ctde = rqt.ctde;
		area.IN.feva = rqt.feva;
		area.IN.impo = rqt.impo;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsCuenTras.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsCuenTrasArea area = (BsCuenTrasArea)a;
		
		response.OUTPUT.put("ctor", area.OUT.ctor);
		response.OUTPUT.put("ctde", area.OUT.ctde);
	}
}
