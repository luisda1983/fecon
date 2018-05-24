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
import es.ldrsoftware.core.btc.bs.BsEjecList;
import es.ldrsoftware.core.btc.bs.BsEjecListArea;

@RestController
public class CtEjecList extends BaseController {

	@Autowired
	public BsEjecList bsEjecList;

	public CtEjecList() {
		super("ctEjecList");
	}
	
	@RequestMapping(value="/angular/ejec/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctEjecList(HttpServletRequest servletRqt, @RequestBody CtEjecListRqt rqt) {
		BsEjecListArea area = new BsEjecListArea();
		if (rqt.inpl) {
			area.IN.tipo = BsEjecList.EJEC_LIST_FEPL_HOPL;
		} else {
			area.IN.tipo = BsEjecList.EJEC_LIST_FECH;
		}
		area.IN.fech = rqt.fech;
		area.IN.hora = rqt.hora;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsEjecList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsEjecListArea area = (BsEjecListArea)a;
		
		response.OUTPUT.put("ejecList", area.OUT.ejecList);
	}
}
