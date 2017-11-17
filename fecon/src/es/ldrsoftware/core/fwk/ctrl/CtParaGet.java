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
import es.ldrsoftware.core.fwk.bs.BsParaGetFront;
import es.ldrsoftware.core.fwk.bs.BsParaGetFrontArea;

@RestController
public class CtParaGet extends BaseController {

	@Autowired
	public BsParaGetFront bsParaGetFront;

	public CtParaGet() {
		super("ctParaGet");
	}
	
	@RequestMapping(value="/angular/para/get", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctParaGetFront(HttpServletRequest servletRqt, @RequestBody CtParaGetRqt rqt) {
		BsParaGetFrontArea area = new BsParaGetFrontArea();
		area.IN.tipo = rqt.tipo;
		area.IN.tbla = rqt.tbla;
		area.IN.clav = rqt.clav;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsParaGetFront.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsParaGetFrontArea area = (BsParaGetFrontArea)a;
		
		response.OUTPUT.put("para", area.OUT.para);
	}
}
