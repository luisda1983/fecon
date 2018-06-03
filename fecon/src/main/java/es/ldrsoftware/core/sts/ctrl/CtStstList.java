package es.ldrsoftware.core.sts.ctrl;

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
import es.ldrsoftware.core.sts.bs.BsStstList;
import es.ldrsoftware.core.sts.bs.BsStstListArea;

@RestController
public class CtStstList extends BaseController {

	@Autowired
	public BsStstList bsStstList;

	public CtStstList() {
		super("ctStstList");
	}
	
	@RequestMapping(value="/angular/stst/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctEjecList(HttpServletRequest servletRqt, @RequestBody CtStstListRqt rqt) {
		BsStstListArea area = new BsStstListArea();
		area.IN.tipo = BsStstList.STST_LIST_FECH;
		area.IN.fech = rqt.fech;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsStstList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsStstListArea area = (BsStstListArea)a;
		
		response.OUTPUT.put("ststList", area.OUT.ststList);
	}
}
