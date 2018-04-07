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
import es.ldrsoftware.core.fwk.bs.BsSesiList;
import es.ldrsoftware.core.fwk.bs.BsSesiListArea;

@RestController
public class CtSesiList extends BaseController {

	@Autowired
	public BsSesiList bsSesiList;

	public CtSesiList() {
		super("ctSesiList");
	}
	
	@RequestMapping(value="/angular/sesi/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctSesiList(HttpServletRequest servletRqt, @RequestBody CtSesiListRqt rqt) {
		BsSesiListArea area = new BsSesiListArea();
		area.IN.esta = rqt.esta;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsSesiList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsSesiListArea area = (BsSesiListArea)a;
		
		response.OUTPUT.put("sesiList", area.OUT.sesiList);
	}
}
