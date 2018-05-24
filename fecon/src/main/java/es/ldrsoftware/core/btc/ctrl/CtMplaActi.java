package es.ldrsoftware.core.btc.ctrl;

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
import es.ldrsoftware.core.btc.bs.BsMplaActi;
import es.ldrsoftware.core.btc.bs.BsMplaActiArea;

@RestController
public class CtMplaActi extends BaseController {

	@Autowired
	public BsMplaActi bsMplaActi;

	public CtMplaActi() {
		super("ctMplaActi");
	}
	
	@RequestMapping(value="/angular/mpla/acti", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctMplaActi(HttpServletRequest servletRqt, @RequestBody CtMplaActiRqt rqt) {
		BsMplaActiArea area = new BsMplaActiArea();
		area.IN.hora = rqt.hora;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsMplaActi.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsMplaActiArea area = (BsMplaActiArea)a;
		
		response.OUTPUT.put("mpla", area.OUT.mpla);
	}
}
