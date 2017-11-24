package ldrsoftware.app.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.fecon.cnt.bs.BsCuenGet;
import es.ldrsoftware.fecon.cnt.bs.BsCuenGetArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import ldrsoftware.app.service.ICuenService;
import ldrsoftware.app.service.IHconService;
import ldrsoftware.app.comunication.request.CuenRequest;
import ldrsoftware.app.comunication.request.HconRequest;
import ldrsoftware.app.comunication.response.BaseResponse;
import ldrsoftware.app.core.mensaje.CUENMensaje;
import ldrsoftware.app.core.parm.ParmSecuencias;
import ldrsoftware.app.work.WorkClass;
import ldrsoftware.app.dao.IHHconDAO;
import ldrsoftware.app.dao.IHSecuDAO;
import ldrsoftware.app.domain.HHcon;

@Component
public class CuenService extends BaseService implements ICuenService, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	public IHconService hconService;

	@Autowired
	public IHSecuDAO secuDao;
	
	@Autowired
	public IHHconDAO hconDao;

	@Autowired
	public BsCuenGet bsCuenGet;
			
	public void tras(CuenRequest request, BaseResponse response, WorkClass work) throws Exception {
		
		if (request.getImpo() == 0) { mensaje(response, CUENMensaje.CUEN_IMPO_OBLG); }
		if (request.getImpo()  < 0) { mensaje(response, CUENMensaje.CUEN_IMPO_NEGA); }
		if (request.getOrig() == 0) { mensaje(response, CUENMensaje.CUEN_ORIG_OBLG); }
		if (request.getDest() == 0) { mensaje(response, CUENMensaje.CUEN_DEST_OBLG); }
		
		if (request.getOrig() == request.getDest()) {
			mensaje(response, CUENMensaje.CUEN_ORIG_DEST_DIST);
		}
		
		//Convivencia
		BsCuenGetArea bsCuenGetArea = new BsCuenGetArea();
		bsCuenGetArea.IN.iden = request.getOrig();
		bsCuenGet.executeBS(bsCuenGetArea);
		Cuen cuenOrig = bsCuenGetArea.OUT.cuen;

		//HCuen cuenOrig = cuenDao.getByIden(request.getOrig());
		
		if (cuenOrig == null) {
			mensaje(response, CUENMensaje.CUEN_NO_EXISTE);
		}
		
		if (request.getImpo() > cuenOrig.getSald()) {
			mensaje(response, CUENMensaje.CUEN_SALD_INSU);
		}
		
		//Convivencia
		bsCuenGetArea = new BsCuenGetArea();
		bsCuenGetArea.IN.iden = request.getDest();
		bsCuenGet.executeBS(bsCuenGetArea);
		Cuen cuenDest = bsCuenGetArea.OUT.cuen;

		//HCuen cuenDest = cuenDao.getByIden(request.getDest());
		
		if (cuenDest == null) {
			mensaje(response, CUENMensaje.CUEN_NO_EXISTE);
		}
		
		cuenOrig.setSald(cuenOrig.getSald() - request.getImpo());
		//cuenDao.save(cuenOrig);
		
		cuenDest.setSald(cuenDest.getSald() + request.getImpo());
		//cuenDao.save(cuenDest);
		
		int iden = secuDao.getNext(work.getInst().getIden(), ParmSecuencias.SEC_ID_CONTABLE);
		
		HHcon hcon = new HHcon();
		hcon.setIden(iden);
		hcon.setCuen(request.getOrig());
		hcon.setTipo("T");
		hcon.setFope(work.getFope());
		hcon.setHope(work.getHope());
		hcon.setFval(work.getFope());
		hcon.setConc(0);
		hcon.setImpo((-1)*request.getImpo());
		hcon.setAdic("");
		hcon.setDesc("TRASPASO A CUENTA " + request.getDest());
		hcon.setUsua(work.getUsua().getIden());
		hconDao.save(hcon);
		
		iden = secuDao.getNext(work.getInst().getIden(), ParmSecuencias.SEC_ID_CONTABLE);
		
		hcon = new HHcon();
		hcon.setIden(iden);
		hcon.setCuen(request.getDest());
		hcon.setTipo("T");
		hcon.setFope(work.getFope());
		hcon.setHope(work.getHope());
		hcon.setFval(work.getFope());
		hcon.setConc(0);
		hcon.setImpo(request.getImpo());
		hcon.setAdic("");
		hcon.setDesc("TRASPASO DE CUENTA " + request.getOrig());
		hcon.setUsua(work.getUsua().getIden());
		hconDao.save(hcon);
		
		mensaje(response, CUENMensaje.CUEN_TRAS_OK);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void cuad(CuenRequest request, BaseResponse response, WorkClass work) throws Exception {
		
		if (request.getImpo() == 0) { mensaje(response, CUENMensaje.CUEN_IMPO_OBLG); }
		if (request.getIden() == 0) { mensaje(response, CUENMensaje.CUEN_IDEN_OBLG); }

		//Convivencia
		BsCuenGetArea bsCuenGetArea = new BsCuenGetArea();
		bsCuenGetArea.IN.iden = request.getIden();
		bsCuenGet.executeBS(bsCuenGetArea);
		Cuen cuen = bsCuenGetArea.OUT.cuen;

		//HCuen cuen = cuenDao.getByIden(request.getIden());
		
		if (cuen == null) {
			mensaje(response, CUENMensaje.CUEN_NO_EXISTE);
		}
		
		HconRequest hconRequest = new HconRequest();
		hconRequest.setCate(request.getCate());
		hconRequest.setConc(request.getConc());
		hconRequest.setCuen(request.getIden());
		hconRequest.setImpo(request.getImpo());
		hconRequest.setDesc("CUADRE DE CUENTA");
		
		BaseResponse hconResponse = new BaseResponse();
		
		try {
			hconService.apunteContable(hconRequest, hconResponse, work);
			multiService(response, hconResponse);
		} catch (Exception e) {
			mensaje(response, hconResponse.getError());
		}
		
		mensaje(response, CUENMensaje.CUEN_CUAD_OK);
	}
}
