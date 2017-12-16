package es.ldrsoftware.fecon.cnt.ctrl;

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
import es.ldrsoftware.fecon.cnt.bs.BsHconList;
import es.ldrsoftware.fecon.cnt.bs.BsHconListArea;

@RestController
public class CtHconList extends BaseController {

	@Autowired
	public BsHconList bsHconList;

	public CtHconList() {
		super("ctHconList");
	}
	
	@RequestMapping(value="/angular/hcon/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctHconList(HttpServletRequest servletRqt, @RequestBody CtHconListRqt rqt) {
		BsHconListArea area = new BsHconListArea();
		area.IN.tipo = rqt.tipo;
		area.IN.anua = rqt.anua;
		area.IN.mesh = rqt.mesh;
		area.IN.cate = rqt.cate;
		area.IN.conc = rqt.conc;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsHconList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsHconListArea area = (BsHconListArea)a;
		
		response.OUTPUT.put("hconList", area.OUT.hconList);
	}
}
