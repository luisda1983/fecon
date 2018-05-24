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
import es.ldrsoftware.core.btc.bs.BsBtchList;
import es.ldrsoftware.core.btc.bs.BsBtchListArea;

@RestController
public class CtBtchList extends BaseController {

	@Autowired
	public BsBtchList bsBtchList;

	public CtBtchList() {
		super("ctBtchList");
	}
	
	@RequestMapping(value="/angular/btch/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctBtchList(HttpServletRequest servletRqt, @RequestBody CtBtchListRqt rqt) {
		BsBtchListArea area = new BsBtchListArea();
		area.IN.tipo = BsBtchList.BTCH_LIST_FULL;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsBtchList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsBtchListArea area = (BsBtchListArea)a;
		
		response.OUTPUT.put("btchList", area.OUT.btchList);
	}
}
