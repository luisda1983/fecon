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
import es.ldrsoftware.fecon.prp.bs.BsPresList;
import es.ldrsoftware.fecon.prp.bs.BsPresListArea;

@RestController
public class CtPresList extends BaseController {

	@Autowired
	public BsPresList bsPresList;

	public CtPresList() {
		super("ctPresList");
	}
	
	@RequestMapping(value="/angular/pres/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctPresList(HttpServletRequest servletRqt, @RequestBody CtPresListRqt rqt) {
		BsPresListArea area = new BsPresListArea();
		area.IN.tipo = rqt.tipo;
		area.IN.anua = rqt.anua;
		area.IN.mesp = rqt.mesp;
		area.IN.cate = rqt.cate;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsPresList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsPresListArea area = (BsPresListArea)a;
		
		response.OUTPUT.put("presList", area.OUT.presList);
		response.OUTPUT.put("presListAnua", area.OUT.presListAnua);
		response.OUTPUT.put("presListMap", area.OUT.presListMap);

	}
}
