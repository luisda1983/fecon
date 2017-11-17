package ldrsoftware.app.controller;

import javax.servlet.http.HttpServletRequest;

import ldrsoftware.app.service.IPrepService;
import ldrsoftware.app.work.WorkClass;
import ldrsoftware.app.comunication.request.BaseRequest;
import ldrsoftware.app.comunication.request.PrepRequest;
import ldrsoftware.app.comunication.response.BaseResponse;
import ldrsoftware.app.comunication.response.PrepResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/angular/prep/")
public class PrepController extends BaseController {
	
	@Autowired
	private IPrepService prepService;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/list", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse prepListController(HttpServletRequest servletRequest, @RequestBody PrepRequest request) {
		PrepResponse response = new PrepResponse();
		WorkClass work = new WorkClass();
		this.controller("prepList", request, response, work, servletRequest);
		return response;
	}
	
	protected void prepList(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		prepService.list((PrepRequest)request, (PrepResponse)response, work);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/save", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse prepSaveController(HttpServletRequest servletRequest, @RequestBody PrepRequest request) {
		PrepResponse response = new PrepResponse();
		WorkClass work = new WorkClass();
		this.controller("prepSave", request, response, work, servletRequest);
		return response;
	}
	
	protected void prepSave(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		prepService.save((PrepRequest)request, (PrepResponse)response, work);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/borr", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse prepBorrController(HttpServletRequest servletRequest, @RequestBody PrepRequest request) {
		PrepResponse response = new PrepResponse();
		WorkClass work = new WorkClass();
		this.controller("prepBorr", request, response, work, servletRequest);
		return response;
	}
	
	protected void prepBorr(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		prepService.borr((PrepRequest)request, (PrepResponse)response, work);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/copy", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse prepCopyController(HttpServletRequest servletRequest, @RequestBody PrepRequest request) {
		PrepResponse response = new PrepResponse();
		WorkClass work = new WorkClass();
		this.controller("prepCopy", request, response, work, servletRequest);
		return response;
	}
	
	protected void prepCopy(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		prepService.copy((PrepRequest)request, (PrepResponse)response, work);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/anua", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse prepAnuaController(HttpServletRequest servletRequest, @RequestBody BaseRequest request) {
		PrepResponse response = new PrepResponse();
		WorkClass work = new WorkClass();
		this.controller("prepAnua", request, response, work, servletRequest);
		return response;
	}
	
	protected void prepAnua(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		prepService.anua(request, (PrepResponse)response, work);
	}
}