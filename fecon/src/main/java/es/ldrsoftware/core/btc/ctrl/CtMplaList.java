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
import es.ldrsoftware.core.btc.bs.BsMplaList;
import es.ldrsoftware.core.btc.bs.BsMplaListArea;

@RestController
public class CtMplaList extends BaseController {

	@Autowired
	public BsMplaList bsMplaList;

	public CtMplaList() {
		super("ctMplaList");
	}
	
	@RequestMapping(value="/angular/mpla/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctMplaList(HttpServletRequest servletRqt, @RequestBody CtMplaListRqt rqt) {
		BsMplaListArea area = new BsMplaListArea();
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsMplaList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsMplaListArea area = (BsMplaListArea)a;
		
		response.OUTPUT.put("mplaList", area.OUT.mplaList);
	}
}
