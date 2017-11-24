package ldrsoftware.app.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.core.arq.data.Session;
import es.ldrsoftware.core.fwk.bs.BsSesiGet;
import es.ldrsoftware.core.fwk.bs.BsSesiGetArea;
import es.ldrsoftware.core.oui.bs.BsInstGet;
import es.ldrsoftware.core.oui.bs.BsInstGetArea;
import es.ldrsoftware.core.oui.bs.BsUsuaGet;
import es.ldrsoftware.core.oui.bs.BsUsuaGetArea;
import es.ldrsoftware.core.oui.entity.Inst;
import es.ldrsoftware.core.oui.entity.Usua;
import ldrsoftware.app.domain.HMens;
import ldrsoftware.app.util.DateTimeUtil;
import ldrsoftware.app.work.WorkClass;
import ldrsoftware.app.comunication.request.BaseRequest;
import ldrsoftware.app.comunication.response.BaseResponse;

@Component
public class BaseController {

	//Convivencia
	@Autowired
	private BsSesiGet bsSesiGet;
	
	@Autowired
	private BsInstGet bsInstGet;
	
	@Autowired
	private BsUsuaGet bsUsuaGet;

	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	protected void controller(String method, BaseRequest request, BaseResponse response, WorkClass work, HttpServletRequest servletRequest) {
		try {
			BsSesiGet.SESSION.set(new Session());
			BsSesiGet.SESSION.get().initializeSession(servletRequest);
			
			inicWork(work, servletRequest);
			if (!"login".equals(method) &&
				!("menu".equals(method) && request.getIdSesion() == 0)) {
				valOper(request, response, work);
			} else {
				if ("menu".equals(method) && request.getIdSesion() == 0) {
					Usua usua = new Usua();
					usua.setPerf("OFF");
					work.setUsua(usua);
				}
			}
			Method m = this.getClass().getDeclaredMethod(method, BaseRequest.class, BaseResponse.class, WorkClass.class);
			m.invoke(this, request, response, work);
			
		} catch (NoSuchMethodException e) {
			systemException(response, e.getLocalizedMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			systemException(response, e.getLocalizedMessage());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			systemException(response, e.getLocalizedMessage());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			systemException(response, e.getLocalizedMessage());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			response.setStatus(BaseResponse.ST_ERROR);
			if (e.getCause() != null) {
				String s = e.getCause().getMessage();
				if (!"APP".equals(s)) {
					systemException(response, e.getCause().getLocalizedMessage());
					e.printStackTrace();
				}
			} 
		} catch (Exception e) {
			if (!"APP".equals(e.getLocalizedMessage())) {
				String s = e.getLocalizedMessage();
				if (s == null) {
					s = e.toString();
				}
				systemException(response, s);
				e.printStackTrace();
			}
		}
	}
	
	private void systemException(BaseResponse response, String s) {
		HMens mens = new HMens();
		mens.setIden("ARQ0000000");
		mens.setTipo("E");
		mens.setDesc(s);
		response.setError(mens);
		response.setStatus(BaseResponse.ST_ERROR);
	}
	
	private void inicWork(WorkClass work, HttpServletRequest servletRequest) {
		if (work == null) { work = new WorkClass(); }
		work.setIp(servletRequest.getRemoteAddr());
		work.setFope(DateTimeUtil.getFope());
		work.setHope(DateTimeUtil.getHope());		
	}
	
	private void valOper(BaseRequest request, BaseResponse response, WorkClass work) throws Exception {
		//Convivencia
		//sesionService.valSesion(request, response, work);
		BsSesiGetArea area = new BsSesiGetArea();
		area.IN.iden = request.getIdSesion();
		bsSesiGet.executeBS(area);
		work.setSesi(area.OUT.sesi);
		
		BsSesiGet.SESSION.get().perf = area.OUT.sesi.getPerf();
		BsSesiGet.SESSION.get().inst = area.OUT.sesi.getInst();
		BsSesiGet.SESSION.get().usua = area.OUT.sesi.getUsua();
		
		//Convivencia
		//usuarioService.valUsuario(request, response, work);
		BsUsuaGetArea usuaGetArea = new BsUsuaGetArea();
		usuaGetArea.IN.iden = area.OUT.sesi.getUsua();
		bsUsuaGet.executeBS(usuaGetArea);
		work.setUsua(usuaGetArea.OUT.usua);
		
		//Convivencia
		//instService.valInst(request, response, work);
		if (request.getInst() != 0) {
			BsInstGetArea instGetArea = new BsInstGetArea();
			instGetArea.IN.iden = request.getInst();
			bsInstGet.executeBS(instGetArea);
			
			work.setInst(instGetArea.OUT.inst);
		} else {
			Inst inst = new Inst();
			inst.setIden(BsSesiGet.SESSION.get().inst);
			inst.setEsta("A");
			inst.setFeal(19000101);
			inst.setFeul(work.getFope());
			inst.setFeca(29991231);
			inst.setTipo("P");
			work.setInst(inst);
		}
	}
}