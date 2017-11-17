package ldrsoftware.app.controller;

import javax.servlet.http.HttpServletRequest;

import ldrsoftware.app.service.IHconService;
import ldrsoftware.app.work.WorkClass;
import ldrsoftware.app.comunication.request.BaseRequest;
import ldrsoftware.app.comunication.request.HconRequest;
import ldrsoftware.app.comunication.response.BaseResponse;
import ldrsoftware.app.comunication.response.HconResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/angular/hcon/")
public class HconController extends BaseController {
	
	@Autowired
	private IHconService hconService;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/apun", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse apunteController(HttpServletRequest servletRequest, @RequestBody HconRequest request) {
		BaseResponse response = new BaseResponse();
		WorkClass work = new WorkClass();
		this.controller("apun", request, response, work, servletRequest);
		return response;
	}
	
	protected void apun(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		hconService.apunteContable((HconRequest)request, response, work);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/list", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse listController(HttpServletRequest servletRequest, @RequestBody HconRequest request) {
		HconResponse response = new HconResponse();
		WorkClass work = new WorkClass();
		this.controller("list", request, response, work, servletRequest);
		return response;
	}
	
	protected void list(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		hconService.list((HconRequest)request, (HconResponse)response, work);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/anul", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse anulController(HttpServletRequest servletRequest, @RequestBody HconRequest request) {
		BaseResponse response = new BaseResponse();
		WorkClass work = new WorkClass();
		this.controller("anul", request, response, work, servletRequest);
		return response;
	}
	
	protected void anul(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		hconService.anul((HconRequest)request, response, work);
	}
}