package es.ldrsoftware.core.spt.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.ldrsoftware.core.arq.BaseController;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.ResponseArea;
import es.ldrsoftware.core.spt.bs.BsDeleList;
import es.ldrsoftware.core.spt.bs.BsDeleListArea;

@RestController
public class CtDeleList extends BaseController {

	@Autowired
	public BsDeleList bsDeleList;

	public CtDeleList() {
		super("ctDeleList");
	}
	
	@RequestMapping(value="/angular/dele/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctDeleList(HttpServletRequest servletRqt, @RequestBody CtDeleListRqt rqt) {
		BsDeleListArea area = new BsDeleListArea();
		area.IN.domi = rqt.domi;
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		BsDeleListArea area = (BsDeleListArea)a;
		bsDeleList.executeBS(area);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsDeleListArea area = (BsDeleListArea)a;
		response.OUTPUT.put("deleList", area.OUT.deleList);
	}
}
