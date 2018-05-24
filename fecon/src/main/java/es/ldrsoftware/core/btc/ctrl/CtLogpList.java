package es.ldrsoftware.core.btc.ctrl;

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
import es.ldrsoftware.core.btc.bs.BsLogpList;
import es.ldrsoftware.core.btc.bs.BsLogpListArea;

@RestController
public class CtLogpList extends BaseController {

	@Autowired
	public BsLogpList bsLogpList;

	public CtLogpList() {
		super("ctLogpList");
	}
	
	@RequestMapping(value="/angular/logp/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctLogpList(HttpServletRequest servletRqt, @RequestBody CtLogpListRqt rqt) {
		BsLogpListArea area = new BsLogpListArea();
		area.IN.tipo = rqt.tipo;
		area.IN.iden = rqt.iden;
		area.IN.fech = rqt.fech;
		area.IN.hora = rqt.hora;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsLogpList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsLogpListArea area = (BsLogpListArea)a;
		
		response.OUTPUT.put("logpList", area.OUT.logpList);
	}
}
