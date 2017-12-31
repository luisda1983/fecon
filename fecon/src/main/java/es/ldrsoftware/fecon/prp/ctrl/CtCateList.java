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
import es.ldrsoftware.fecon.prp.bs.BsCateList;
import es.ldrsoftware.fecon.prp.bs.BsCateListArea;

@RestController
public class CtCateList extends BaseController {

	@Autowired
	public BsCateList bsCateList;

	public CtCateList() {
		super("ctCateList");
	}
	
	@RequestMapping(value="/angular/cate/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCateList(HttpServletRequest servletRqt, @RequestBody CtCateListRqt rqt) {
		BsCateListArea area = new BsCateListArea();
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsCateList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsCateListArea area = (BsCateListArea)a;
		
		response.OUTPUT.put("cateList", area.OUT.cateList);
		response.OUTPUT.put("cateListMap", area.OUT.cateListMap);

	}
}
