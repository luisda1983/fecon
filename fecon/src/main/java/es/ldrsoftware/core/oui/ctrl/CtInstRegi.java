package es.ldrsoftware.core.oui.ctrl;

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
import es.ldrsoftware.core.oui.bs.BsInstRegi;
import es.ldrsoftware.core.oui.bs.BsInstRegiArea;

@RestController
public class CtInstRegi extends BaseController {

	@Autowired
	public BsInstRegi bsInstRegi;

	public CtInstRegi() {
		super("ctInstRegi");
	}
	
	@RequestMapping(value="/angular/inst/regi", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCtmnActi(HttpServletRequest servletRqt, @RequestBody CtInstRegiRqt rqt) {
		BsInstRegiArea area = new BsInstRegiArea();
		area.IN.invi = false;
		area.IN.desc = rqt.desc;
		area.IN.numo = rqt.numo;
		area.IN.mail = rqt.mail;
		area.IN.usua = rqt.usua;
		area.IN.pass = rqt.pass;
		area.IN.cpas = rqt.cpas;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsInstRegi.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsInstRegiArea area = (BsInstRegiArea)a;
		
		response.OUTPUT.put("inst", area.OUT.inst);
		response.OUTPUT.put("usua", area.OUT.usua);
	}
}
