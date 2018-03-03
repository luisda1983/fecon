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
import es.ldrsoftware.core.mnu.bs.BsDtmnList;
import es.ldrsoftware.core.mnu.bs.BsDtmnListArea;

@RestController
public class CtDtmnList extends BaseController {

	@Autowired
	public BsDtmnList bsDtmnList;

	public CtDtmnList() {
		super("ctDtmnList");
	}
	
	@RequestMapping(value="/angular/dtmn/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctDtmnList(HttpServletRequest servletRqt, @RequestBody CtDtmnListRqt rqt) {
		BsDtmnListArea area = new BsDtmnListArea();
		area.IN.tipo = BsDtmnList.DTMN_LIST_CTMN_FULL;
		area.IN.ctmn = rqt.ctmn;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsDtmnList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsDtmnListArea area = (BsDtmnListArea)a;
		
		response.OUTPUT.put("dtmnList", area.OUT.dtmnList);
	}
}
