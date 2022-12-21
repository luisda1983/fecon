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
import es.ldrsoftware.fecon.prp.bs.BsCateGetk;
import es.ldrsoftware.fecon.prp.bs.BsCateGetkArea;

@RestController
public class CtCateGetk extends BaseController {

	@Autowired
	public BsCateGetk bsCateGetk;

	public CtCateGetk() {
		super("ctCateGetk");
	}
	
	@RequestMapping(value="/angular/cate/get", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCateGetk(HttpServletRequest servletRqt, @RequestBody CtCateGetkRqt rqt) {
		BsCateGetkArea area = new BsCateGetkArea();
		area.IN.iden = rqt.iden;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsCateGetk.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsCateGetkArea area = (BsCateGetkArea)a;
		
		response.OUTPUT.put("cate", area.OUT.cate);
	}
}
