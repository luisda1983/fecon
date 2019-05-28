package es.ldrsoftware.core.spt.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.ldrsoftware.core.arq.BaseController;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.ResponseArea;
import es.ldrsoftware.core.spt.bs.BsDeleForm;
import es.ldrsoftware.core.spt.bs.BsDeleFormArea;

@RestController
public class CtDeleForm extends BaseController {

	@Autowired
	public BsDeleForm bsDeleForm;

	public CtDeleForm() {
		super("ctDeleForm");
	}
	
	@RequestMapping(value="/angular/dele/form", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctDeleForm(HttpServletRequest servletRqt, @RequestBody CtDeleFormRqt rqt) {
		BsDeleFormArea area = new BsDeleFormArea();
		area.IN.iden = rqt.iden;
		area.IN.domi = rqt.domi;
		area.IN.valo = rqt.valo;
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		BsDeleFormArea area = (BsDeleFormArea)a;
		bsDeleForm.executeBS(area);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsDeleFormArea area = (BsDeleFormArea)a;
		response.OUTPUT.put("dele", area.OUT.dele);
	}
}
