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
import es.ldrsoftware.fecon.cnt.bs.BsCuenForm;
import es.ldrsoftware.fecon.cnt.bs.BsCuenFormArea;

@RestController
public class CtCuenForm extends BaseController {

	@Autowired
	public BsCuenForm bsCuenForm;

	public CtCuenForm() {
		super("ctCuenForm");
	}
	
	@RequestMapping(value="/angular/cuen/form", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCuenForm(HttpServletRequest servletRqt, @RequestBody CtCuenFormRqt rqt) {
		BsCuenFormArea area = new BsCuenFormArea();
		area.IN.iden = rqt.iden;
		area.IN.tipo = rqt.tipo;
		area.IN.desc = rqt.desc;
		area.IN.sald = rqt.sald;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsCuenForm.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsCuenFormArea area = (BsCuenFormArea)a;
		
		response.OUTPUT.put("cuen", area.OUT.cuen);
	}
}
