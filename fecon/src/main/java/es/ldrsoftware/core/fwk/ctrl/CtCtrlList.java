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
import es.ldrsoftware.core.fwk.bs.BsCtrlList;
import es.ldrsoftware.core.fwk.bs.BsCtrlListArea;

@RestController
public class CtCtrlList extends BaseController {

	@Autowired
	public BsCtrlList bsCtrlList;

	public CtCtrlList() {
		super("ctCtrlList");
	}
	
	@RequestMapping(value="/angular/ctrl/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCtrlList(HttpServletRequest servletRqt, @RequestBody CtCtrlListRqt rqt) {
		BsCtrlListArea area = new BsCtrlListArea();
		area.IN.tipo = BsCtrlList.CTRL_LIST_FULL;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsCtrlList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsCtrlListArea area = (BsCtrlListArea)a;
		
		response.OUTPUT.put("ctrlList", area.OUT.ctrlList);
	}
}
