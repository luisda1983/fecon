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
import es.ldrsoftware.core.btc.bs.BsMplaDesa;
import es.ldrsoftware.core.btc.bs.BsMplaDesaArea;

@RestController
public class CtMplaDesa extends BaseController {

	@Autowired
	public BsMplaDesa bsMplaDesa;

	public CtMplaDesa() {
		super("ctMplaDesa");
	}
	
	@RequestMapping(value="/angular/mpla/desa", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctMplaDesa(HttpServletRequest servletRqt, @RequestBody CtMplaDesaRqt rqt) {
		BsMplaDesaArea area = new BsMplaDesaArea();
		area.IN.hora = rqt.hora;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsMplaDesa.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsMplaDesaArea area = (BsMplaDesaArea)a;
		
		response.OUTPUT.put("mpla", area.OUT.mpla);
	}
}
