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
import es.ldrsoftware.core.mnu.bs.BsCtmnList;
import es.ldrsoftware.core.mnu.bs.BsCtmnListArea;

@RestController
public class CtCtmnList extends BaseController {

	@Autowired
	public BsCtmnList bsCtmnList;

	public CtCtmnList() {
		super("ctCtmnList");
	}
	
	@RequestMapping(value="/angular/ctmn/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCtmnList(HttpServletRequest servletRqt, @RequestBody CtCtmnListRqt rqt) {
		BsCtmnListArea area = new BsCtmnListArea();
		area.IN.tipo = BsCtmnList.CTMN_LIST_PERF_FULL;
		area.IN.perf = rqt.perf;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsCtmnList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsCtmnListArea area = (BsCtmnListArea)a;
		
		response.OUTPUT.put("ctmnList", area.OUT.ctmnList);
	}
}
