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
import es.ldrsoftware.core.mnu.bs.BsMenuMake;
import es.ldrsoftware.core.mnu.bs.BsMenuMakeArea;

@RestController
public class CtMenuMake extends BaseController {

	@Autowired
	public BsMenuMake bsMenuMake;

	public CtMenuMake() {
		super("ctMenuMake");
	}
	
	@RequestMapping(value="/angular/menu/make", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctMenuMake(HttpServletRequest servletRqt, @RequestBody CtMenuMakeRqt rqt) {
		BsMenuMakeArea area = new BsMenuMakeArea();
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsMenuMake.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsMenuMakeArea area = (BsMenuMakeArea)a;
		
		response.OUTPUT.put("menu", area.OUT.ctmnList);
	}
}
