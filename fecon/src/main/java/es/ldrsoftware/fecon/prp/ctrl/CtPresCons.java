package es.ldrsoftware.fecon.prp.ctrl;

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
import es.ldrsoftware.fecon.prp.bs.BsPresCons;
import es.ldrsoftware.fecon.prp.bs.BsPresConsArea;

@RestController
public class CtPresCons extends BaseController {

	@Autowired
	public BsPresCons bsPresCons;

	public CtPresCons() {
		super("ctPresCons");
	}
	
	@RequestMapping(value="/angular/pres/cons", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctPresCons(HttpServletRequest servletRqt, @RequestBody CtPresConsRqt rqt) {
		BsPresConsArea area = new BsPresConsArea();
		area.IN.anua = rqt.anua;
		area.IN.mesp = rqt.mesp;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsPresCons.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsPresConsArea area = (BsPresConsArea)a;

		response.OUTPUT.put("presList", area.OUT.presList);
		response.OUTPUT.put("presAnuaList", area.OUT.presAnuaList);
		response.OUTPUT.put("impoNpre", area.OUT.impoNpre);
		response.OUTPUT.put("cuenList", area.OUT.cuenList);		
	}
}
