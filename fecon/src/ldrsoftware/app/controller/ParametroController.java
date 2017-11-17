package ldrsoftware.app.controller;

import javax.servlet.http.HttpServletRequest;

import ldrsoftware.app.comunication.request.BaseRequest;
import ldrsoftware.app.comunication.request.ParmRequest;
import ldrsoftware.app.comunication.response.BaseResponse;
import ldrsoftware.app.comunication.response.ParametroResponse;
import ldrsoftware.app.core.parm.valo.BaseParmValo;
import ldrsoftware.app.service.IParametroService;
import ldrsoftware.app.work.WorkClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/angular/parametro/")
public class ParametroController extends BaseController {
	
	@Autowired
	private IParametroService parametroService;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/{tabla}", method = RequestMethod.GET,headers="Accept=application/json")
	public ParametroResponse getParametroList(@PathVariable String tabla) throws Exception {
		ParametroResponse response = new ParametroResponse();
		parametroService.obtenerListaParametro(response, tabla);
		return response;
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "valo/{tabl}/{clav}", method = RequestMethod.GET, headers="Accept=application/json")
	public BaseParmValo getParmValo(@PathVariable String tabl, @PathVariable String clav) throws Exception {
		BaseParmValo parmValo = parametroService.valo(tabl, clav);
		return parmValo;
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	@RequestMapping(value = "/list/", method = RequestMethod.POST, headers="Accept=application/json")
	public BaseResponse getParmListController(HttpServletRequest servletRequest, @RequestBody ParmRequest request) {
		ParametroResponse response = new ParametroResponse();
		WorkClass work = new WorkClass();
		this.controller("parmList", request, response, work, servletRequest);
		return response;
	}
	
	protected void parmList(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		parametroService.list((ParmRequest)request, (ParametroResponse)response, work);
	}

}