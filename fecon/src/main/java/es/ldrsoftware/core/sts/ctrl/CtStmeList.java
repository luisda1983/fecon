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
import es.ldrsoftware.core.sts.bs.BsStmeList;
import es.ldrsoftware.core.sts.bs.BsStmeListArea;

@RestController
public class CtStmeList extends BaseController {

	@Autowired
	public BsStmeList bsStmeList;

	public CtStmeList() {
		super("ctStmeList");
	}
	
	@RequestMapping(value="/angular/stme/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctStmeList(HttpServletRequest servletRqt, @RequestBody CtStmeListRqt rqt) {
		BsStmeListArea area = new BsStmeListArea();
		area.IN.tipo = BsStmeList.STME_LIST_ANYO_MESS;
		area.IN.anyo = rqt.anyo;
		area.IN.mess = rqt.mess;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsStmeList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsStmeListArea area = (BsStmeListArea)a;

		response.OUTPUT.put("stmeList", area.OUT.stmeList);
	}
}
