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
import es.ldrsoftware.core.mnu.bs.BsCtmnDesa;
import es.ldrsoftware.core.mnu.bs.BsCtmnDesaArea;

@RestController
public class CtCtmnDesa extends BaseController {

	@Autowired
	public BsCtmnDesa bsCtmnDesa;

	public CtCtmnDesa() {
		super("ctCtmnDesa");
	}
	
	@RequestMapping(value="/angular/ctmn/desa", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCtmnDesa(HttpServletRequest servletRqt, @RequestBody CtCtmnDesaRqt rqt) {
		BsCtmnDesaArea area = new BsCtmnDesaArea();
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsCtmnDesa.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsCtmnDesaArea area = (BsCtmnDesaArea)a;
		
		response.OUTPUT.put("ctmn", area.OUT.ctmn);
	}
}
