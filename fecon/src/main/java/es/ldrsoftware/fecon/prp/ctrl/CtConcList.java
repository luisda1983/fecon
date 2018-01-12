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
import es.ldrsoftware.fecon.prp.bs.BsConcList;
import es.ldrsoftware.fecon.prp.bs.BsConcListArea;

@RestController
public class CtConcList extends BaseController {

	@Autowired
	public BsConcList bsConcList;

	public CtConcList() {
		super("ctConcList");
	}
	
	@RequestMapping(value="/angular/conc/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCateList(HttpServletRequest servletRqt, @RequestBody CtConcListRqt rqt) {
		BsConcListArea area = new BsConcListArea();
		area.IN.tipo = BsConcListArea.LIST_TIPO_CATE;
		area.IN.cate = rqt.cate;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsConcList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsConcListArea area = (BsConcListArea)a;
		
		response.OUTPUT.put("concList", area.OUT.concList);
		response.OUTPUT.put("concListMap", area.OUT.concListMap);
	}
}
