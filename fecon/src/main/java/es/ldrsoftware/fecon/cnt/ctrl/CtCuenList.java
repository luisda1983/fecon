package es.ldrsoftware.fecon.cnt.ctrl;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

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
import es.ldrsoftware.fecon.cnt.bs.BsCuenList;
import es.ldrsoftware.fecon.cnt.bs.BsCuenListArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;

@RestController
public class CtCuenList extends BaseController {

	//TODO: revisar tarea pendiente del cuenListCtrl.js
	@Autowired
	public BsCuenList bsCuenList;

	public CtCuenList() {
		super("ctCuenList");
	}
	
	@RequestMapping(value="/angular/cuen/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctCuenList(HttpServletRequest servletRqt, @RequestBody CtCuenListRqt rqt) {
		BsCuenListArea area = new BsCuenListArea();
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsCuenList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsCuenListArea area = (BsCuenListArea)a;
		
		response.OUTPUT.put("cuenList", area.OUT.cuenList);
		
		ListIterator<Cuen> it = area.OUT.cuenList.listIterator();
		Map<Long, Cuen> cuenMap = new HashMap<Long, Cuen>();
		while (it.hasNext()) {
			Cuen cuen = it.next();
			cuenMap.put(cuen.getIden(), cuen);
		}
		
		response.OUTPUT.put("cuenMap" , cuenMap);
	}
}
