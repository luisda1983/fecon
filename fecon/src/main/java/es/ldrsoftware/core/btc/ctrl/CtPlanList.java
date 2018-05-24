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
import es.ldrsoftware.core.btc.bs.BsPlanList;
import es.ldrsoftware.core.btc.bs.BsPlanListArea;

@RestController
public class CtPlanList extends BaseController {

	@Autowired
	public BsPlanList bsPlanList;

	public CtPlanList() {
		super("ctPlanList");
	}
	
	@RequestMapping(value="/angular/plan/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctPlanList(HttpServletRequest servletRqt, @RequestBody CtPlanListRqt rqt) {
		BsPlanListArea area = new BsPlanListArea();
		if (rqt.proc) {
			area.IN.tipo = BsPlanList.PLAN_LIST_FECH_PROC;
		} else {
			area.IN.tipo = BsPlanList.PLAN_LIST_FECH;
		}
		area.IN.fech = rqt.fech;
		return ctrl(servletRqt, rqt, area);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void execute(BaseBSArea a) throws Exception {
		bsPlanList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsPlanListArea area = (BsPlanListArea)a;
		
		response.OUTPUT.put("planList", area.OUT.planList);
	}
}
