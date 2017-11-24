package ldrsoftware.app.controller;

import javax.servlet.http.HttpServletRequest;

import ldrsoftware.app.service.ICateService;
import ldrsoftware.app.work.WorkClass;
import ldrsoftware.app.comunication.request.BaseRequest;
import ldrsoftware.app.comunication.request.CateRequest;
import ldrsoftware.app.comunication.response.BaseResponse;
import ldrsoftware.app.comunication.response.CateResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/angular/cate/")
public class CateController extends BaseController {
	
	@Autowired
	private ICateService cateService;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/map", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse cateMapController(HttpServletRequest servletRequest, @RequestBody CateRequest request) {
		CateResponse response = new CateResponse();
		WorkClass work = new WorkClass();
		this.controller("cateMap", request, response, work, servletRequest);
		return response;
	}
	
	protected void cateMap(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		cateService.getListFull((CateRequest)request, (CateResponse)response, work);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/list/", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse cateListController(HttpServletRequest servletRequest, @RequestBody CateRequest request) {
		CateResponse response = new CateResponse();
		WorkClass work = new WorkClass();
		this.controller("cateList", request, response, work, servletRequest);
		return response;
	}
	
	protected void cateList(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		cateService.getList((CateRequest)request, (CateResponse)response, work);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/nuev/", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse cateNuevController(HttpServletRequest servletRequest, @RequestBody CateRequest request) {
		CateResponse response = new CateResponse();
		WorkClass work = new WorkClass();
		this.controller("cateSaveNuev", request, response, work, servletRequest);
		return response;
	}
	
	protected void cateSaveNuev(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		cateService.saveNuev((CateRequest)request, (CateResponse)response, work);
		cateService.getList((CateRequest)request, (CateResponse)response, work);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/edit/", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse cateEditController(HttpServletRequest servletRequest, @RequestBody CateRequest request) {
		CateResponse response = new CateResponse();
		WorkClass work = new WorkClass();
		this.controller("cateSaveEdit", request, response, work, servletRequest);
		return response;
	}
	
	protected void cateSaveEdit(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		cateService.saveEdit((CateRequest)request, (CateResponse)response, work);
		cateService.getList((CateRequest)request, (CateResponse)response, work);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/acti/", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse cateActiController(HttpServletRequest servletRequest, @RequestBody CateRequest request) {
		CateResponse response = new CateResponse();
		WorkClass work = new WorkClass();
		this.controller("cateActi", request, response, work, servletRequest);
		return response;
	}
	
	protected void cateActi(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		cateService.acti((CateRequest)request, (CateResponse)response, work);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/move/", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse cateMoveController(HttpServletRequest servletRequest, @RequestBody CateRequest request) {
		CateResponse response = new CateResponse();
		WorkClass work = new WorkClass();
		this.controller("cateMove", request, response, work, servletRequest);
		return response;
	}
	
	protected void cateMove(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		cateService.move((CateRequest)request, (CateResponse)response, work);
		cateService.getList((CateRequest)request, (CateResponse)response, work);
	}
}