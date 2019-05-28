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
import es.ldrsoftware.core.spt.bs.BsDomiList;
import es.ldrsoftware.core.spt.bs.BsDomiListArea;

@RestController
public class CtDomiList extends BaseController {

	@Autowired
	public BsDomiList bsDomiList;

	public CtDomiList() {
		super("ctDomiList");
	}
	
	@RequestMapping(value="/angular/domi/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctDomiList(HttpServletRequest servletRqt, @RequestBody CtDomiListRqt rqt) {
		BsDomiListArea area = new BsDomiListArea();
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		BsDomiListArea area = (BsDomiListArea)a;
		bsDomiList.executeBS(area);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsDomiListArea area = (BsDomiListArea)a;
		response.OUTPUT.put("domiList", area.OUT.domiList);
	}
}
