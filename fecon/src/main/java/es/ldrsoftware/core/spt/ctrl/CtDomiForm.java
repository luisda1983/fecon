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
import es.ldrsoftware.core.spt.bs.BsDomiForm;
import es.ldrsoftware.core.spt.bs.BsDomiFormArea;

@RestController
public class CtDomiForm extends BaseController {

	@Autowired
	public BsDomiForm bsDomiForm;

	public CtDomiForm() {
		super("ctDomiForm");
	}
	
	@RequestMapping(value="/angular/domi/form", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctDomiForm(HttpServletRequest servletRqt, @RequestBody CtDomiFormRqt rqt) {
		BsDomiFormArea area = new BsDomiFormArea();
		area.IN.iden = rqt.iden;
		area.IN.nomb = rqt.nomb;
		area.IN.desc = rqt.desc;
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		BsDomiFormArea area = (BsDomiFormArea)a;
		bsDomiForm.executeBS(area);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsDomiFormArea area = (BsDomiFormArea)a;
		response.OUTPUT.put("domi", area.OUT.domi);
	}
}
