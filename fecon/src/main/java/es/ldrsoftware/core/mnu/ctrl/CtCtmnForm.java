package es.ldrsoftware.core.mnu.ctrl;

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
import es.ldrsoftware.core.mnu.bs.BsCtmnForm;
import es.ldrsoftware.core.mnu.bs.BsCtmnFormArea;

@RestController
public class CtCtmnForm extends BaseController {

	@Autowired
	public BsCtmnForm bsCtmnForm;

	public CtCtmnForm() {
		super("ctCtmnForm");
	}
	
	@RequestMapping(value="/angular/ctmn/form", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCtmnForm(HttpServletRequest servletRqt, @RequestBody CtCtmnFormRqt rqt) {
		BsCtmnFormArea area = new BsCtmnFormArea();
		area.IN.iden = rqt.iden;
		area.IN.perf = rqt.perf;
		area.IN.desc = rqt.desc;
		area.IN.acti = rqt.acti;
		area.IN.orde = rqt.orde;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsCtmnForm.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsCtmnFormArea area = (BsCtmnFormArea)a;
		
		response.OUTPUT.put("ctmn", area.OUT.ctmn);
	}
}
