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
import es.ldrsoftware.core.fwk.bs.BsNotfList;
import es.ldrsoftware.core.fwk.bs.BsNotfListArea;

@RestController
public class CtNotfList extends BaseController {

	@Autowired
	public BsNotfList bsNotfList;

	public CtNotfList() {
		super("ctNotfList");
	}
	
	@RequestMapping(value="/angular/notf/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctNotfList(HttpServletRequest servletRqt, @RequestBody CtNotfListRqt rqt) {
		BsNotfListArea area = new BsNotfListArea();
		area.IN.apli = rqt.apli;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsNotfList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsNotfListArea area = (BsNotfListArea)a;
		
		response.OUTPUT.put("notfList", area.OUT.notfList);
	}
}
