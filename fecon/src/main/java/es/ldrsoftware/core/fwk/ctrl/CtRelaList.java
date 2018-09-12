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
import es.ldrsoftware.core.fwk.bs.BsRelaList;
import es.ldrsoftware.core.fwk.bs.BsRelaListArea;

@RestController
public class CtRelaList extends BaseController {

	@Autowired
	public BsRelaList bsRelaList;

	public CtRelaList() {
		super("ctRelaList");
	}
	
	@RequestMapping(value="/angular/rela/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctRelaList(HttpServletRequest servletRqt, @RequestBody CtRelaListRqt rqt) {
		BsRelaListArea area = new BsRelaListArea();
		area.IN.rela = rqt.rela;
		area.IN.clc1 = rqt.clc1;
		area.IN.clc2 = rqt.clc2;
		area.IN.cln1 = rqt.cln1;
		area.IN.cln2 = rqt.cln2;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsRelaList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsRelaListArea area = (BsRelaListArea)a;
		
		response.OUTPUT.put("relaList", area.OUT.relaList);
	}
}
