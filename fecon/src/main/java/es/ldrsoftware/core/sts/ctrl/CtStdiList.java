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
import es.ldrsoftware.core.sts.bs.BsStdiList;
import es.ldrsoftware.core.sts.bs.BsStdiListArea;

@RestController
public class CtStdiList extends BaseController {

	@Autowired
	public BsStdiList bsStdiList;

	public CtStdiList() {
		super("ctStdiList");
	}
	
	@RequestMapping(value="/angular/stdi/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctStdiList(HttpServletRequest servletRqt, @RequestBody CtStdiListRqt rqt) {
		BsStdiListArea area = new BsStdiListArea();
		area.IN.tipo = BsStdiList.STDI_LIST_FECH;
		area.IN.fech = rqt.fech;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsStdiList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsStdiListArea area = (BsStdiListArea)a;

		response.OUTPUT.put("stdiList", area.OUT.stdiList);
	}
}
