package es.ldrsoftware.core.fwk.ctrl;

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
import es.ldrsoftware.core.fwk.bs.BsNotfEdit;
import es.ldrsoftware.core.fwk.bs.BsNotfEditArea;

@RestController
public class CtNotfEdit extends BaseController {

	@Autowired
	public BsNotfEdit bsNotfEdit;

	public CtNotfEdit() {
		super("ctNotfEdit");
	}
	
	@RequestMapping(value="/angular/notf/edit", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctNotfEdit(HttpServletRequest servletRqt, @RequestBody CtNotfEditRqt rqt) {
		BsNotfEditArea area = new BsNotfEditArea();
		area.IN.iden = rqt.iden;
		area.IN.tipo = rqt.tipo;
		area.IN.desc = rqt.desc;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsNotfEdit.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsNotfEditArea area = (BsNotfEditArea)a;
		
		response.OUTPUT.put("notf", area.OUT.notf);
	}
}
