package ldrsoftware.app.controller;

import javax.servlet.http.HttpServletRequest;

import ldrsoftware.app.service.IConcService;
import ldrsoftware.app.work.WorkClass;
import ldrsoftware.app.comunication.request.BaseRequest;
import ldrsoftware.app.comunication.request.ConcRequest;
import ldrsoftware.app.comunication.response.BaseResponse;
import ldrsoftware.app.comunication.response.ConceptoResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/angular/conc/")
public class ConcController extends BaseController {
	
	@Autowired
	private IConcService conceptoService;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/map", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse conceptoMapController(HttpServletRequest servletRequest, @RequestBody BaseRequest request) {
		ConceptoResponse response = new ConceptoResponse();
		WorkClass work = new WorkClass();
		this.controller("conceptoMap", request, response, work, servletRequest);
		return response;
	}
	
	protected void conceptoMap(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		conceptoService.getListFull(request, (ConceptoResponse)response, work);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/list/", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse conceptoListController(HttpServletRequest servletRequest, @RequestBody ConcRequest request) {
		ConceptoResponse response = new ConceptoResponse();
		WorkClass work = new WorkClass();
		this.controller("conceptoList", request, response, work, servletRequest);
		return response;
	}
	
	protected void conceptoList(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		conceptoService.getListByIdsu((ConcRequest)request, (ConceptoResponse)response, work);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/nuev/", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse concNuevController(HttpServletRequest servletRequest, @RequestBody ConcRequest request) {
		ConceptoResponse response = new ConceptoResponse();
		WorkClass work = new WorkClass();
		this.controller("concSaveNew", request, response, work, servletRequest);
		return response;
	}
	
	protected void concSaveNew(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		conceptoService.saveNew((ConcRequest)request, (ConceptoResponse)response, work);
		conceptoService.getListByIdsu((ConcRequest)request, (ConceptoResponse)response, work);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/edit/", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse concEditController(HttpServletRequest servletRequest, @RequestBody ConcRequest request) {
		ConceptoResponse response = new ConceptoResponse();
		WorkClass work = new WorkClass();
		this.controller("concSaveEdit", request, response, work, servletRequest);
		return response;
	}
	
	protected void concSaveEdit(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		conceptoService.saveEdit((ConcRequest)request, (ConceptoResponse)response, work);
		conceptoService.getListByIdsu((ConcRequest)request, (ConceptoResponse)response, work);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/acti/", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse concActiController(HttpServletRequest servletRequest, @RequestBody ConcRequest request) {
		ConceptoResponse response = new ConceptoResponse();
		WorkClass work = new WorkClass();
		this.controller("concActi", request, response, work, servletRequest);
		return response;
	}
	
	protected void concActi(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		conceptoService.acti((ConcRequest)request, (ConceptoResponse)response, work);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/move/", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse concMoveController(HttpServletRequest servletRequest, @RequestBody ConcRequest request) {
		ConceptoResponse response = new ConceptoResponse();
		WorkClass work = new WorkClass();
		this.controller("concMove", request, response, work, servletRequest);
		return response;
	}
	
	protected void concMove(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		conceptoService.move((ConcRequest)request, (ConceptoResponse)response, work);
		conceptoService.getListByIdsu((ConcRequest)request, (ConceptoResponse)response, work);
	}
}