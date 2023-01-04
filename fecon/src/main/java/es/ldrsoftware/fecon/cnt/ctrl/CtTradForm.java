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
import es.ldrsoftware.fecon.cnt.bs.BsTradForm;
import es.ldrsoftware.fecon.cnt.bs.BsTradFormArea;

@RestController
public class CtTradForm extends BaseController {

	@Autowired
	public BsTradForm bsTradForm;

	public CtTradForm() {
		super("ctTradForm");
	}
	
	@RequestMapping(value="/angular/trad/form", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctTradForm(HttpServletRequest servletRqt, @RequestBody CtTradFormRqt rqt) {
		BsTradFormArea area = new BsTradFormArea();
		area.IN.iden = rqt.iden;
		area.IN.nomb = rqt.nomb;
		area.IN.tip1 = rqt.tip1;
		area.IN.dom1 = rqt.dom1;
		area.IN.ide1 = rqt.ide1;
		area.IN.obl1 = rqt.obl1;
		area.IN.tip2 = rqt.tip2;
		area.IN.dom2 = rqt.dom2;
		area.IN.ide2 = rqt.ide2;
		area.IN.obl2 = rqt.obl2;
		area.IN.tip3 = rqt.tip3;
		area.IN.dom3 = rqt.dom3;
		area.IN.ide3 = rqt.ide3;
		area.IN.obl3 = rqt.obl3;
		area.IN.desc = rqt.desc;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsTradForm.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsTradFormArea area = (BsTradFormArea)a;
		
		response.OUTPUT.put("trad", area.OUT.trad);
	}
}
