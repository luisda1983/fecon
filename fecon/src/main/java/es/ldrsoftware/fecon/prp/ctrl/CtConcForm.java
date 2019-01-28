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
import es.ldrsoftware.fecon.prp.bs.BsConcForm;
import es.ldrsoftware.fecon.prp.bs.BsConcFormArea;

@RestController
public class CtConcForm extends BaseController {

	@Autowired
	public BsConcForm bsConcForm;

	public CtConcForm() {
		super("ctConcForm");
	}
	
	@RequestMapping(value="/angular/conc/form", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCateList(HttpServletRequest servletRqt, @RequestBody CtConcFormRqt rqt) {
		BsConcFormArea area = new BsConcFormArea();
		area.IN.iden = rqt.iden;
		area.IN.cate = rqt.cate;
		area.IN.tipo = rqt.tipo;
		area.IN.desl = rqt.desl;
		area.IN.desc = rqt.desc;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsConcForm.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsConcFormArea area = (BsConcFormArea)a;
		
		response.OUTPUT.put("conc", area.OUT.conc);

	}
}
