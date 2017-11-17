package ldrsoftware.app.controller;

import javax.servlet.http.HttpServletRequest;

import ldrsoftware.app.service.ICuenService;
import ldrsoftware.app.work.WorkClass;
import ldrsoftware.app.comunication.request.BaseRequest;
import ldrsoftware.app.comunication.request.CuenRequest;
import ldrsoftware.app.comunication.response.BaseResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/angular/cuen/")
public class CuenController extends BaseController {
	
	@Autowired
	private ICuenService cuenService;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/tras/", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse cuenTrasController(HttpServletRequest servletRequest, @RequestBody CuenRequest request) {
		BaseResponse response = new BaseResponse();
		WorkClass work = new WorkClass();
		this.controller("cuenTras", request, response, work, servletRequest);
		return response;
	}

	protected void cuenTras(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		cuenService.tras((CuenRequest)request, (BaseResponse)response, work);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/cuad/", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse cuenCuadController(HttpServletRequest servletRequest, @RequestBody CuenRequest request) {
		BaseResponse response = new BaseResponse();
		WorkClass work = new WorkClass();
		this.controller("cuenCuad", request, response, work, servletRequest);
		return response;
	}
	
	protected void cuenCuad(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		cuenService.cuad((CuenRequest)request, (BaseResponse)response, work);
	}
}