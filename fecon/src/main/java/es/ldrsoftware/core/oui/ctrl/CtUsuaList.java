package es.ldrsoftware.core.oui.ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.ldrsoftware.core.arq.BaseController;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.ResponseArea;
import es.ldrsoftware.core.fwk.data.LiteData;
import es.ldrsoftware.core.oui.bs.BsUsuaList;
import es.ldrsoftware.core.oui.bs.BsUsuaListArea;

@RestController
public class CtUsuaList extends BaseController {

	@Autowired
	public BsUsuaList bsUsuaList;

	public CtUsuaList() {
		super("ctUsuaList");
	}
	
	@RequestMapping(value="/angular/usua/list", method = RequestMethod.POST, headers="Accept=application/json")
	public ResponseArea ctUsuaList(HttpServletRequest servletRqt, @RequestBody CtUsuaListRqt rqt) {
		BsUsuaListArea area = new BsUsuaListArea();
		area.IN.inst = rqt.inst;
		return ctrl(servletRqt, rqt, area);
	}
	
	public void execute(BaseBSArea a) throws Exception {
		BsUsuaListArea area = (BsUsuaListArea)a;
		if (LiteData.LT_EL_USUAPERF_APM.equals(SESSION.get().perf) && area.IN.inst == 0) {
			area.IN.tipo = BsUsuaList.USUA_LIST_FULL;
		} else {
			area.IN.tipo = BsUsuaList.USUA_LIST_INST;
		}
		bsUsuaList.executeBS(a);
	}
	
	public void output(BaseBSArea a, ResponseArea response) {
		BsUsuaListArea area = (BsUsuaListArea)a;
		response.OUTPUT.put("usuaList", area.OUT.usuaList);
	}
}
