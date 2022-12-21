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
import es.ldrsoftware.fecon.cnt.bs.BsCoesForm;
import es.ldrsoftware.fecon.cnt.bs.BsCoesFormArea;

@RestController
public class CtCoesForm extends BaseController {

	@Autowired
	public BsCoesForm bsCoesForm;

	public CtCoesForm() {
		super("ctCoesForm");
	}
	
	@RequestMapping(value="/angular/coes/form", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCoesForm(HttpServletRequest servletRqt, @RequestBody CtCoesFormRqt rqt) {
		BsCoesFormArea area = new BsCoesFormArea();
		area.IN.iden = rqt.iden;
		area.IN.tipo = rqt.tipo;
		area.IN.desc = rqt.desc;
		area.IN.favo = rqt.favo;
		//area.IN.trad = rqt.trad;
		area.IN.cate = rqt.cate;
		area.IN.conc = rqt.conc;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsCoesForm.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsCoesFormArea area = (BsCoesFormArea)a;
		
		response.OUTPUT.put("coes", area.OUT.coes);
	}
}
