package ldrsoftware.app.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.ldrsoftware.fecon.cnt.bs.BsCuenGet;
import es.ldrsoftware.fecon.cnt.bs.BsCuenGetArea;
import es.ldrsoftware.fecon.cnt.bs.BsCuenSave;
import es.ldrsoftware.fecon.cnt.bs.BsCuenSaveArea;
import es.ldrsoftware.fecon.cnt.entity.Cuen;
import es.ldrsoftware.fecon.prp.bs.BsPresGet;
import es.ldrsoftware.fecon.prp.bs.BsPresGetArea;
import es.ldrsoftware.fecon.prp.bs.BsPresSave;
import es.ldrsoftware.fecon.prp.bs.BsPresSaveArea;
import es.ldrsoftware.fecon.prp.entity.Pres;
import ldrsoftware.app.service.IHconService;
import ldrsoftware.app.comunication.request.HconRequest;
import ldrsoftware.app.comunication.response.BaseResponse;
import ldrsoftware.app.comunication.response.HconResponse;
import ldrsoftware.app.core.mensaje.CONCMensaje;
import ldrsoftware.app.core.mensaje.CUENMensaje;
import ldrsoftware.app.core.mensaje.HCONMensaje;
import ldrsoftware.app.core.parm.ParmSecuencias;
import ldrsoftware.app.work.WorkClass;
import ldrsoftware.app.dao.IHConcDAO;
import ldrsoftware.app.dao.IHHconDAO;
import ldrsoftware.app.dao.IHSecuDAO;
import ldrsoftware.app.domain.HConc;
import ldrsoftware.app.domain.HHcon;

@Component
public class HconService extends BaseService implements IHconService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	public IHHconDAO hconDao;

	@Autowired
	public IHConcDAO concDao;
	
	@Autowired
	public IHSecuDAO secuDao;

	//Convivencia
	@Autowired
	public BsCuenGet bsCuenGet;
	
	@Autowired
	public BsCuenSave bsCuenSave;
	
	@Autowired
	public BsPresGet bsPresGet;

	@Autowired
	public BsPresSave bsPresSave;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void apunteContable(HconRequest request, BaseResponse response, WorkClass work) throws Exception {
		
		if (request.getConc() == 0) { mensaje(response, HCONMensaje.HCON_CONCEPTO_OBLIGATORIO); }
		if (request.getCate() == 0) { mensaje(response, HCONMensaje.HCON_CATE_OBLG); }
		if (request.getFval() == 0) { request.setFval(work.getFope()); }
		//TODO: validación fval es fecha correcta.
		//TODO: si concdomi <> 0, validar adic
		if (request.getImpo() == 0) { mensaje(response, HCONMensaje.HCON_IMPORTE_OBLIGATOIO); }
		if (request.getCuen() == 0) { mensaje(response, HCONMensaje.HCON_CUEN_OBLG); }
		
		HConc hConc = concDao.getByIden(work.getInst().getIden(), request.getConc());
		
		if (hConc == null) {
			mensaje(response, CONCMensaje.CONC_NO_EXISTE);
		} else {
			if (!hConc.isActi()) {
				mensaje(response, CONCMensaje.CONC_NO_ACTIVO);
			}
			if ("G".equals(hConc.getTipo())){
				if (request.getImpo() > 0) {
					mensaje(response, HCONMensaje.HCON_IMPO_CONC_ERROR);
				}
			} else {
				if (request.getImpo() < 0) {
					mensaje(response, HCONMensaje.HCON_IMPO_CONC_ERROR);
				}
			}
		}
		
		//Convivencia
		BsCuenGetArea bsCuenGetArea = new BsCuenGetArea();
		bsCuenGetArea.IN.iden = request.getCuen();
		bsCuenGet.executeBS(bsCuenGetArea);
		Cuen cuen = bsCuenGetArea.OUT.cuen;
		
		//HCuen cuen = cuenDao.getByIden(request.getCuen());
		
		if (cuen == null) {
			mensaje(response, CUENMensaje.CUEN_NO_EXISTE);
		} else {
			cuen.setSald(cuen.getSald() + request.getImpo());
			BsCuenSaveArea bsCuenSaveArea = new BsCuenSaveArea();
			bsCuenSaveArea.IN.cuen = cuen;
			bsCuenSave.executeBS(bsCuenSaveArea);
		}
		
		int iden = secuDao.getNext(work.getInst().getIden(), ParmSecuencias.SEC_ID_CONTABLE);
		
		HHcon hcon = new HHcon();
		hcon.setInst(work.getInst().getIden());
		hcon.setIden(iden);
		hcon.setCuen(request.getCuen());
		hcon.setTipo("C");
		hcon.setFope(work.getFope());
		hcon.setHope(work.getHope());
		hcon.setFval(request.getFval());
		hcon.setCate(request.getCate());
		hcon.setConc(request.getConc());
		hcon.setImpo(request.getImpo());
		hcon.setAdic(request.getAdic());
		hcon.setDesc(request.getDesc());
		hcon.setUsua(work.getUsua().getIden());
		hconDao.save(hcon);

		int cate = request.getCate();
		int conc = request.getConc();
		
		//Convivencia
		BsPresGetArea bsPresGetArea = new BsPresGetArea();
		bsPresGetArea.IN.fech = request.getFval();
		bsPresGetArea.IN.cate = cate;
		bsPresGetArea.IN.conc = conc;
		bsPresGet.executeBS(bsPresGetArea);
		
		Pres pres = bsPresGetArea.OUT.pres;
	
		//Mientras no se determine la exclusión de presupuesto mediante la entrada, la partida indicará
		//si computa contra presupuesto o no
		if (pres.getImpo() != 0) {
			pres.setImpr(pres.getImpr() + hcon.getImpo());
		} else {
			pres.setImnp(pres.getImnp() + hcon.getImpo());
		}
		
		pres.setImto(pres.getImto() + hcon.getImpo());
		pres.setDesv(pres.getImto() - pres.getImpo());

		BsPresSaveArea bsPresSaveArea = new BsPresSaveArea();
		bsPresSaveArea.IN.pres = pres;
		bsPresSave.executeBS(bsPresSaveArea);
		
		//HPres pres = presDao.getByAnuaMespCateConc(work.getInst().getIden(), anua, mesp, cate, conc);
		
//		if (pres != null) {
//			if (pres.getImpo() == 0) {
//				mensaje(response, HCONMensaje.HCON_CONCEPTO_NO_PRESUPUESTADO);
//			}
//			pres.setImre(pres.getImre() + hcon.getImpo());
//			pres.setDesv(pres.getImre() - pres.getImpo());
//			presDao.save(pres);
//		} else {
//			pres = presDao.getByAnuaMespCateConc(work.getInst().getIden(), anua, mesp, cate, 0);
//			if (pres != null) {
//				if (pres.getImpo() == 0) {
//					mensaje(response, HCONMensaje.HCON_CONCEPTO_NO_PRESUPUESTADO);
//				}
//				pres.setImre(pres.getImre() + hcon.getImpo());
//				pres.setDesv(pres.getImre() - pres.getImpo());
//				presDao.save(pres);
//			} else {
//				pres = presDao.getByAnuaMespCateConc(work.getInst().getIden(), anua, 0, cate, conc);
//				if (pres != null) {
//					if (pres.getImpo() == 0) {
//						mensaje(response, HCONMensaje.HCON_CONCEPTO_NO_PRESUPUESTADO);
//					}
//					pres.setImre(pres.getImre() + hcon.getImpo());
//					pres.setDesv(pres.getImre() - pres.getImpo());
//					presDao.save(pres);
//				} else {
//					pres = presDao.getByAnuaMespCateConc(work.getInst().getIden(), anua, 0, cate, 0);
//					if (pres != null) {
//						if (pres.getImpo() == 0) {
//							mensaje(response, HCONMensaje.HCON_CONCEPTO_NO_PRESUPUESTADO);
//						}
//						pres.setImre(pres.getImre() + hcon.getImpo());
//						pres.setDesv(pres.getImre() - pres.getImpo());
//						presDao.save(pres);
//					} else {
//						mensaje(response, HCONMensaje.HCON_CONCEPTO_NO_PRESUPUESTADO);
//						pres = new HPres();
//						pres.setInst(work.getInst().getIden());
//						pres.setAnua(anua);
//						pres.setMesp(mesp);
//						pres.setCate(cate);
//						pres.setConc(conc);
//						pres.setImpo(0);
//						pres.setImre(request.getImpo());
//						pres.setEsta("A");
//						pres.setDesv(request.getImpo());
//						presDao.save(pres);
//					}
//				}
//			}
//		}
		
		response.setStatus(BaseResponse.ST_OK);
		mensaje(response, HCONMensaje.HCON_APUNTE_REGISTRADO);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void list(HconRequest request, HconResponse response, WorkClass work) throws Exception {
		if (!(request.getAnua() != 0 && request.getMesp() != 0) &&
			!(request.getAnua() != 0 && request.getCate() != 0) &&
			!(request.getAnua() != 0 && request.getMesp() != 0 && request.getCate() != 0)) {
			//FIXME
			mensaje(response, HCONMensaje.HCON_IMPO_CONC_ERROR);
		}
		int fdes = 0;
		int fhas = 99991231;
		int cate = 0;
		int conc = 0;
		
		if (request.getCate() != 0) {
			cate = request.getCate();
		}
		if (request.getConc() != 0) {
			conc = request.getConc();
		}
		if (request.getAnua() != 0) {
			if (request.getMesp() != 0) {
				fdes = request.getAnua() * 10000 + request.getMesp() * 100;
				fhas = fdes + 99;
			} else {
				fdes = request.getAnua() * 10000;
				fhas = fdes + 1299;
			}
		}
		List<HHcon> hconList = hconDao.getList(fdes, fhas, cate, conc);
		if (hconList == null) {
			//FIXME: informativo
		} 
		response.setHconList(hconList);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void anul(HconRequest request, BaseResponse response, WorkClass work) throws Exception {
		
		if (request.getIden() == 0) { mensaje(response, HCONMensaje.HCON_IDEN_OBLG); }
				
		HHcon hcon = hconDao.getByIden(request.getIden());
		
		if (hcon == null) {
			mensaje(response, HCONMensaje.HCON_NO_EXISTE);
		}

		//Convivencia
		BsCuenGetArea bsCuenGetArea = new BsCuenGetArea();
		bsCuenGetArea.IN.iden = hcon.getCuen();
		bsCuenGet.executeBS(bsCuenGetArea);
		Cuen cuen = bsCuenGetArea.OUT.cuen;

		//HCuen cuen = cuenDao.getByIden(hcon.getCuen());
		
		if (cuen == null) {
			mensaje(response, CUENMensaje.CUEN_NO_EXISTE);
		} else {
			cuen.setSald(cuen.getSald() - hcon.getImpo());
			cuen.setSald(cuen.getSald() + request.getImpo());
			BsCuenSaveArea bsCuenSaveArea = new BsCuenSaveArea();
			bsCuenSaveArea.IN.cuen = cuen;
			bsCuenSave.executeBS(bsCuenSaveArea);
		}
		
		HConc conc = concDao.getByIden(work.getInst().getIden(), hcon.getConc());
		
		if (conc == null) {
			mensaje(response, CONCMensaje.CONC_NO_EXISTE);
		} 
		
		hcon.setTipo("A");
		hcon.setDesc("[ANULADO] " + hcon.getDesc());
		//FIXME: ampliar entidad HCON con informacion de anulacion de movimiento
		hconDao.save(hcon);
		
		int iCate = hcon.getCate();
		int iConc = hcon.getConc();
		
//		HPres pres = presDao.getByAnuaMespCateConc(work.getInst().getIden(), anua, mesp, iCate, iConc);

		//Convivencia
		BsPresGetArea bsPresGetArea = new BsPresGetArea();
		bsPresGetArea.IN.fech = hcon.getFval();
		bsPresGetArea.IN.cate = iCate;
		bsPresGetArea.IN.conc = iConc;
		bsPresGet.executeBS(bsPresGetArea);
		
		Pres pres = bsPresGetArea.OUT.pres;

		//Mientras no se determine la exclusión de presupuesto mediante la entrada, la partida indicará
		//si computa contra presupuesto o no. En la anulación, el propio movimiento indicará si computó
		//contra presupuesto o no
		if (pres.getImpo() != 0) {
			pres.setImpr(pres.getImpr() - hcon.getImpo());
		} else {
			pres.setImnp(pres.getImnp() - hcon.getImpo());
		}

		pres.setImto(pres.getImto() - hcon.getImpo());
		pres.setDesv(pres.getImto() - pres.getImpo());

		BsPresSaveArea bsPresSaveArea = new BsPresSaveArea();
		bsPresSaveArea.IN.pres = pres;
		bsPresSave.executeBS(bsPresSaveArea);

//		if (pres != null) {
//			pres.setImre(pres.getImre() - hcon.getImpo());
//			pres.setDesv(pres.getImre() - pres.getImpo());
//			presDao.save(pres);
//		} else {
//			pres = presDao.getByAnuaMespCateConc(work.getInst().getIden(), anua, mesp, iCate, 0);
//			if (pres != null) {
//				pres.setImre(pres.getImre() - hcon.getImpo());
//				pres.setDesv(pres.getImre() - pres.getImpo());
//				presDao.save(pres);
//			} else {
//				pres = presDao.getByAnuaMespCateConc(work.getInst().getIden(), anua, 0, iCate, iConc);
//				if (pres != null) {
//					pres.setImre(pres.getImre() - hcon.getImpo());
//					pres.setDesv(pres.getImre() - pres.getImpo());
//					presDao.save(pres);
//				} else {
//					pres = presDao.getByAnuaMespCateConc(work.getInst().getIden(), anua, 0, iCate, 0);
//					if (pres != null) {
//						pres.setImre(pres.getImre() - hcon.getImpo());
//						pres.setDesv(pres.getImre() - pres.getImpo());
//						presDao.save(pres);
//					} else {
//						mensaje(response, HCONMensaje.HCON_CONCEPTO_NO_PRESUPUESTADO);
//					}
//				}
//			}
//		}
		
		response.setStatus(BaseResponse.ST_OK);
		mensaje(response, HCONMensaje.HCON_APUNTE_ANUL);
	}
}
