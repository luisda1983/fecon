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
import es.ldrsoftware.fecon.cnt.bs.BsHconApun;
import es.ldrsoftware.fecon.cnt.bs.BsHconApunArea;

@RestController
public class CtHconApun extends BaseController {

	@Autowired
	public BsHconApun bsHconApun;

	public CtHconApun() {
		super("ctHconApun");
	}
	
	@RequestMapping(value="/angular/hcon/apun", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctHconApun(HttpServletRequest servletRqt, @RequestBody CtHconApunRqt rqt) {
		BsHconApunArea area = new BsHconApunArea();
		area.IN.cate = rqt.cate;
		area.IN.conc = rqt.conc;
		area.IN.cuen = rqt.cuen;
		area.IN.feva = rqt.feva;
		area.IN.impo = rqt.impo;
		area.IN.desc = rqt.desc;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsHconApun.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsHconApunArea area = (BsHconApunArea)a;
		
		response.OUTPUT.put("hcon", area.OUT.hcon);
	}
}
