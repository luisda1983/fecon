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
import es.ldrsoftware.fecon.prp.bs.BsCateForm;
import es.ldrsoftware.fecon.prp.bs.BsCateFormArea;

@RestController
public class CtCateForm extends BaseController {

	@Autowired
	public BsCateForm bsCateForm;

	public CtCateForm() {
		super("ctCateForm");
	}
	
	@RequestMapping(value="/angular/cate/form", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCateList(HttpServletRequest servletRqt, @RequestBody CtCateFormRqt rqt) {
		BsCateFormArea area = new BsCateFormArea();
		area.IN.iden = rqt.iden;
		area.IN.desl = rqt.desl;
		area.IN.desc = rqt.desc;
		area.IN.pres = rqt.pres;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsCateForm.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsCateFormArea area = (BsCateFormArea)a;
		
		response.OUTPUT.put("cate", area.OUT.cate);

	}
}
