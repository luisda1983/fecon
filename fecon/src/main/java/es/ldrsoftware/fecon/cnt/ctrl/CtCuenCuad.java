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
import es.ldrsoftware.fecon.cnt.bs.BsCuenCuad;
import es.ldrsoftware.fecon.cnt.bs.BsCuenCuadArea;

@RestController
public class CtCuenCuad extends BaseController {

	@Autowired
	public BsCuenCuad bsCuenCuad;

	public CtCuenCuad() {
		super("ctCuenCuad");
	}
	
	@RequestMapping(value="/angular/cuen/cuad", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCuenForm(HttpServletRequest servletRqt, @RequestBody CtCuenCuadRqt rqt) {
		BsCuenCuadArea area = new BsCuenCuadArea();
		area.IN.cuen = rqt.cuen;
		area.IN.cate = rqt.cate;
		area.IN.conc = rqt.conc;
		area.IN.impo = rqt.impo;
		area.IN.sald = rqt.sald;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsCuenCuad.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsCuenCuadArea area = (BsCuenCuadArea)a;
		
		response.OUTPUT.put("cuen", area.OUT.cuen);
	}
}
