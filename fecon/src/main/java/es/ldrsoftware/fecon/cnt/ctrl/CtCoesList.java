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
import es.ldrsoftware.fecon.cnt.bs.BsCoesList;
import es.ldrsoftware.fecon.cnt.bs.BsCoesListArea;

@RestController
public class CtCoesList extends BaseController {

	@Autowired
	public BsCoesList bsCoesList;

	public CtCoesList() {
		super("ctCoesList");
	}
	
	@RequestMapping(value="/angular/coes/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCoesList(HttpServletRequest servletRqt, @RequestBody CtCoesListRqt rqt) {
		BsCoesListArea area = new BsCoesListArea();
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsCoesList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsCoesListArea area = (BsCoesListArea)a;
		
		response.OUTPUT.put("coesList", area.OUT.coesList);
	}
}
