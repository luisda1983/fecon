package ldrsoftware.app.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ldrsoftware.app.service.IConcService;
import ldrsoftware.app.comunication.request.BaseRequest;
import ldrsoftware.app.comunication.request.ConcRequest;
import ldrsoftware.app.comunication.response.ConceptoResponse;
import ldrsoftware.app.core.mensaje.CONCMensaje;
import ldrsoftware.app.core.parm.ParmSecuencias;
import ldrsoftware.app.work.WorkClass;
import ldrsoftware.app.dao.IHConcDAO;
import ldrsoftware.app.dao.IHSecuDAO;
import ldrsoftware.app.dao.search.ConcSearch;
import ldrsoftware.app.domain.HConc;

@Component
public class ConcService extends BaseService implements IConcService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	public IHConcDAO concDao;

	@Autowired
	public IHSecuDAO secuDao;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void getListFull(BaseRequest request, ConceptoResponse response, WorkClass work) throws Exception {

		ConcSearch concSearch = new ConcSearch();
		List<HConc> concList = concDao.getList(work.getInst().getIden(), concSearch); 
		
		if (concList == null || concList.size() == 0) {
			mensaje(response, CONCMensaje.CONC_NO_HAY_CONCEPTOS);
		}
		response.setConcMap(concList);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void getListByIdsu(ConcRequest request, ConceptoResponse response, WorkClass work) throws Exception {

		ConcSearch concSearch = new ConcSearch();
		concSearch.setCate(request.getCate());
		if (!request.isFull()) {
			concSearch.setActi(true);
		}
		List<HConc> concList = concDao.getList(work.getInst().getIden(), concSearch); 
		
		if (concList == null || concList.size() == 0) {
			mensaje(response, CONCMensaje.CONC_NO_HAY_CONCEPTOS);
		}
		if (request.isDefa()) {
			HConc conc = new HConc();
			concList.add(0, conc);
		}
		response.setConcList(concList);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void saveEdit(ConcRequest request, ConceptoResponse response, WorkClass work) throws Exception {
		if (request.getDesc() == null || "".equals(request.getDesc())) {
			mensaje(response, CONCMensaje.CONC_DESC_OBLIG);
		}
		if (request.getTipo() == null || "".equals(request.getTipo())) {
			mensaje(response, CONCMensaje.CONC_TIPO_OBLIG);
		}
		
		HConc conc = concDao.getByIden(work.getInst().getIden(), request.getIden());
		
		if (conc == null) {
			mensaje(response, CONCMensaje.CONC_NO_EXISTE);
		}
		
		conc.setDesc(request.getDesc());
		conc.setTipo(request.getTipo());
		
		concDao.save(conc);
		
		mensaje(response, CONCMensaje.CONC_EDITADO);
	}

	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void acti(ConcRequest request, ConceptoResponse response, WorkClass work) throws Exception {
			
		HConc conc = concDao.getByIden(work.getInst().getIden(), request.getIden());
		
		if (conc == null) {
			mensaje(response, CONCMensaje.CONC_NO_EXISTE);
		}
		
		conc.setActi(request.isActi());
		
		concDao.save(conc);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void saveNew(ConcRequest request, ConceptoResponse response, WorkClass work) throws Exception {
		if (request.getDesc() == null || "".equals(request.getDesc())) {
			mensaje(response, CONCMensaje.CONC_DESC_OBLIG);
		}
		if (request.getCate() == 0) {
			mensaje(response, CONCMensaje.E_CONC_CATE_OBLG);
		}
		if (request.getTipo() == null || "".equals(request.getTipo())) {
			mensaje(response, CONCMensaje.CONC_TIPO_OBLIG);
		}
		
		HConc conc;
		
		conc = concDao.getByCateDesc(work.getInst().getIden(), request.getCate(), request.getDesc());
		
		if (conc != null) {
			mensaje(response, CONCMensaje.CONC_DUPL);
		}
		
		int iden = secuDao.getNext(work.getInst().getIden(), ParmSecuencias.SEC_ID_CONC);
		int orde = concDao.getOrde(work.getInst().getIden(), request.getCate());
		
		conc = new HConc();
		conc.setInst(work.getInst().getIden());
		conc.setIden(iden);
		conc.setDesc(request.getDesc());
		conc.setActi(true);
		conc.setTipo(request.getTipo());
		conc.setCate(request.getCate());
		conc.setDomi(request.getDomi());
		conc.setOrde(orde);
		
		concDao.save(conc);
		
		mensaje(response, CONCMensaje.CONC_CREADO);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void move (ConcRequest request, ConceptoResponse response, WorkClass work) throws Exception {
		HConc conc = concDao.getByIden(work.getInst().getIden(), request.getIden());
		
		if (conc == null) {
			mensaje(response, CONCMensaje.CONC_NO_EXISTE);
		}
		
		int swapOrde;
		
		if (request.isDown()) {
			swapOrde = conc.getOrde() + 1;
		} else {
			swapOrde = conc.getOrde() - 1;
		}
		HConc concSwap = concDao.getByCateOrde(work.getInst().getIden(), conc.getCate(), swapOrde);
		if (concSwap == null) {
			//WARNING
			mensaje(response, CONCMensaje.CONC_ORDE_WARN);
		} else {
			concSwap.setOrde(conc.getOrde());
			concDao.save(concSwap);
		}
		conc.setOrde(swapOrde);
		concDao.save(conc);
	}
}
