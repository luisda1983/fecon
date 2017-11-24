package ldrsoftware.app.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ldrsoftware.app.service.ICateService;
import ldrsoftware.app.comunication.request.CateRequest;
import ldrsoftware.app.comunication.response.BaseResponse;
import ldrsoftware.app.comunication.response.CateResponse;
import ldrsoftware.app.core.mensaje.CATEMensaje;
import ldrsoftware.app.core.parm.ParmSecuencias;
import ldrsoftware.app.work.WorkClass;
import ldrsoftware.app.dao.IHCateDAO;
import ldrsoftware.app.dao.IHConcDAO;
import ldrsoftware.app.dao.IHSecuDAO;
import ldrsoftware.app.dao.search.CateSearch;
import ldrsoftware.app.dao.search.ConcSearch;
import ldrsoftware.app.domain.HCate;
import ldrsoftware.app.domain.HConc;

@Component
public class CateService extends BaseService implements ICateService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	public IHCateDAO cateDao;

	@Autowired
	public IHConcDAO concDao;
	
	@Autowired
	public IHSecuDAO secuDao;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void getListFull(CateRequest request, CateResponse response, WorkClass work) throws Exception {

		CateSearch cateSearch = new CateSearch();
		cateSearch.setActi(false);
		List<HCate> cateList = cateDao.getList(work.getInst().getIden(), cateSearch); 
		
		if (cateList == null || cateList.size() == 0) {
			mensaje(response, CATEMensaje.I_CATE_LIST_NF);
		}
		
		ListIterator<HCate> it = cateList.listIterator();
		while(it.hasNext()) {
			HCate cate = it.next();
			ConcSearch search = new ConcSearch();
			search.setCate(cate.getIden());
			List<HConc> concList = concDao.getList(work.getInst().getIden(), search);
			cate.setConcMap(concList);
		}
		
		if (request.isDefa()) {
			HCate cate = new HCate();
			cateList.add(0, cate);
		}
		
		response.setCateList(cateList);
		response.setCateMap(cateList);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void getList(CateRequest request, CateResponse response, WorkClass work) throws Exception {

		//TODO CateRequest:full
		CateSearch cateSearch = new CateSearch();
		cateSearch.setActi(true);
		List<HCate> cateList = cateDao.getList(work.getInst().getIden(), cateSearch); 
		
		if (cateList == null || cateList.size() == 0) {
			mensaje(response, CATEMensaje.I_CATE_LIST_NF);
		}
		response.setCateList(cateList);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void saveEdit(CateRequest request, BaseResponse response, WorkClass work) throws Exception {
		if (request.getDesc() == null || "".equals(request.getDesc())) {
			mensaje(response, CATEMensaje.E_CATE_DESC_OBLG);
		}
		
		HCate cate = cateDao.getByIden(work.getInst().getIden(), request.getIden());

		if (cate == null) {
			mensaje(response, CATEMensaje.E_CATE_NF);
		}
		
		cate.setDesc(request.getDesc());
		cateDao.save(cate);
		
		mensaje(response, CATEMensaje.I_CATE_EDIT);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void acti(CateRequest request, BaseResponse response, WorkClass work) throws Exception {
			
		HCate cate = cateDao.getByIden(work.getInst().getIden(), request.getIden());
		
		if (cate == null) {
			mensaje(response, CATEMensaje.E_CATE_NF);
		}
		
		cate.setActi(request.isActi());
		
		cateDao.save(cate);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void saveNuev(CateRequest request, BaseResponse response, WorkClass work) throws Exception {
		if (request.getDesc() == null || "".equals(request.getDesc())) {
			mensaje(response, CATEMensaje.E_CATE_DESC_OBLG);
		}
		
		HCate cate = cateDao.getByDesc(work.getInst().getIden(), request.getDesc());
		
		if (cate != null) {
			mensaje(response, CATEMensaje.E_CATE_DP);
		}
		
		int iden = secuDao.getNext(work.getInst().getIden(), ParmSecuencias.SEC_ID_CATE);
		int orde = cateDao.getOrde(work.getInst().getIden());
		
		cate = new HCate();
		cate.setInst(work.getInst().getIden());
		cate.setIden(iden);
		cate.setDesc(request.getDesc());
		cate.setActi(true);
		cate.setOrde(orde);
		
		cateDao.save(cate);
		
		mensaje(response, CATEMensaje.I_CATE_SAVE);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void move (CateRequest request, BaseResponse response, WorkClass work) throws Exception {
		HCate cate = cateDao.getByIden(work.getInst().getIden(), request.getIden());
		
		if (cate == null) {
			mensaje(response, CATEMensaje.E_CATE_NF);
		}
		
		int swapOrde;
		
		if (request.isDown()) {
			swapOrde = cate.getOrde() + 1;
		} else {
			swapOrde = cate.getOrde() - 1;
		}
		HCate cateSwap = cateDao.getByOrde(work.getInst().getIden(), swapOrde);
		if (cateSwap == null) {
			//WARNING
			mensaje(response, CATEMensaje.W_CATE_ORDE);
		} else {
			cateSwap.setOrde(cate.getOrde());
			cateDao.save(cateSwap);
		}
		cate.setOrde(swapOrde);
		cateDao.save(cate);
	}
}
