package ldrsoftware.app.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ldrsoftware.app.service.IPrepService;
import ldrsoftware.app.comunication.request.BaseRequest;
import ldrsoftware.app.comunication.request.PrepRequest;
import ldrsoftware.app.comunication.response.BaseResponse;
import ldrsoftware.app.comunication.response.PrepResponse;
import ldrsoftware.app.core.mensaje.PREPMensaje;
import ldrsoftware.app.core.parm.ParmMaestro;
import ldrsoftware.app.core.parm.ParmSecuencias;
import ldrsoftware.app.core.parm.valo.ValoPeriodicid;
import ldrsoftware.app.work.WorkClass;
import ldrsoftware.app.dao.IHParmDAO;
import ldrsoftware.app.dao.IHPrepDAO;
import ldrsoftware.app.dao.IHSecuDAO;
import ldrsoftware.app.domain.HParm;
import ldrsoftware.app.domain.HPrep;

@Component
public class PrepService extends BaseService implements IPrepService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	public IHPrepDAO prepDao;

	@Autowired
	public IHSecuDAO secuDao;
	
	@Autowired
	public IHParmDAO parmDao;
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void list(PrepRequest request, PrepResponse response, WorkClass work) throws Exception {

		if (request.getAnua() == 0) { mensaje(response, PREPMensaje.E_PREP_ANUA_OBLG); }
		
		List<HPrep> prepList;
		
		prepList = prepDao.getListByAnua(work.getInst().getIden(), request.getAnua());
		response.setPrepList(prepList);
	}
		
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void save(PrepRequest request, BaseResponse response, WorkClass work) throws Exception {

		HPrep prep = new HPrep();
		
		if (request.getIden() != 0) {
			prep = prepDao.getByIden(work.getInst().getIden(), request.getIden());
			if (prep == null) {
				mensaje(response, PREPMensaje.E_PREP_NF);
			}
		} else {
			int iden = secuDao.getNext(work.getInst().getIden(), ParmSecuencias.SEC_ID_PREP);
			prep.setIden(iden);
		}
		
		if (request.getAnua() == 0) { mensaje(response, PREPMensaje.E_PREP_ANUA_OBLG); }
		if (request.getCate() == 0) { mensaje(response, PREPMensaje.E_PREP_CATE_OBLG); }
		if ("".equals(request.getPeri()) || request.getPeri() == null) { mensaje(response, PREPMensaje.E_PREP_PERI_OBLG); }
		if (request.getImpo() == 0) { mensaje(response, PREPMensaje.E_PREP_IMPO_OBLG); }
	
		HParm parmPeri = parmDao.getByTblaClav(ParmMaestro.PERIODICIDAD, request.getPeri());
		
		if (parmPeri == null) {
			mensaje(response, PREPMensaje.E_PREP_PERI_NF);
		}
		
		ValoPeriodicid parmValo = new ValoPeriodicid(parmPeri);
		
		if (parmValo.isMini()) {
			if (request.getMini() == 0) { mensaje(response, PREPMensaje.E_PREP_MINI_OBLG); }
		}
		
		if (parmValo.isMfin()) {
			if (request.getMfin() == 0) { mensaje(response, PREPMensaje.E_PREP_MFIN_OBLG); }
			if (request.getMfin() <= request.getMini()) { mensaje(response, PREPMensaje.E_PREP_MINI_MFIN); }
			int dif = request.getMfin() - request.getMini();
			Float f = new Float(dif / parmValo.getDivi());
			int i = f.intValue();
			float j = f - i;
			if (j != 0) {
				mensaje(response, PREPMensaje.E_PREP_PERI_MFIN);
			}
		} else {
			if (request.getMfin() > 0) { mensaje(response, PREPMensaje.E_PREP_MFIN_NP); }
		}
		
		if (request.getConc() == 0) {
			List<HPrep> prepList = prepDao.getListByAnuaCate(work.getInst().getIden(), request.getAnua(), request.getCate(), true);
			if (prepList != null && prepList.size() > 0) {
				mensaje(response, PREPMensaje.E_PREP_CATE_NP);
			}
		} else {
			List<HPrep> prepList = prepDao.getListByAnuaCate(work.getInst().getIden(), request.getAnua(), request.getCate(), false);
			if (prepList != null && prepList.size() > 0) {
				mensaje(response, PREPMensaje.E_PREP_CONC_NP);
			}
		}

		
		int meses = 1;
		if (parmValo.isMfin() && parmValo.isMini()) {
			meses = ((request.getMfin() - request.getMini()) / parmValo.getDivi()) + 1;
		}
		
		prep.setInst(work.getInst().getIden());
		prep.setAnua(request.getAnua());
		prep.setCate(request.getCate());
		prep.setConc(request.getConc());
		prep.setPeri(request.getPeri());
		prep.setMini(request.getMini());
		prep.setMfin(request.getMfin());
		prep.setImpo(request.getImpo());
		prep.setTota(meses * request.getImpo());
		
		prepDao.save(prep);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void anua(BaseRequest request, PrepResponse response, WorkClass work) throws Exception {
		HPrep prep = new HPrep();
		List<HParm> parmAnua = parmDao.getListByTbla(ParmMaestro.ANUALIDAD);
		if (parmAnua == null || parmAnua.size() == 0) {
			prep.setAnua(work.getFope() / 10000);
		} else {
			try {
				int i = new Integer(parmAnua.get(0).getClav());
				prep.setAnua(i+1);
			} catch (NumberFormatException e) {
				prep.setAnua(work.getFope() / 10000);
			}
		}
		response.setPrep(prep);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void borr(PrepRequest request, BaseResponse response, WorkClass work) throws Exception {
		if (request.getIden() == 0) { mensaje(response, PREPMensaje.E_PREP_IDEN_OBLG); }
		
		HPrep prep = prepDao.getByIden(work.getInst().getIden(), request.getIden());
		
		if (prep == null) {
			mensaje(response, PREPMensaje.E_PREP_NF);
		}
		
		prepDao.delete(prep);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void copy(PrepRequest request, BaseResponse response, WorkClass work) throws Exception {
		if (request.getAnua() == 0) { mensaje(response, PREPMensaje.E_PREP_ANUA_OBLG); }
		
		List<HPrep> prepList = prepDao.getListByAnua(work.getInst().getIden(), request.getAnua() + 1);
		
		if (prepList != null && prepList.size() > 0) {
			mensaje(response, PREPMensaje.E_PREP_COPY_VACI);
		}
		
		prepList = prepDao.getListByAnua(work.getInst().getIden(), request.getAnua());
		
		if (prepList == null || prepList.size() == 0) {
			mensaje(response, PREPMensaje.I_PREP_COPY_NF);
		} else {
			ListIterator<HPrep> it = prepList.listIterator();
			while (it.hasNext()) {
				HPrep prepAnte = it.next();
				HPrep prep = new HPrep();
				int iden = secuDao.getNext(work.getInst().getIden(), ParmSecuencias.SEC_ID_PREP);
				prep.setInst(prepAnte.getInst());
				prep.setIden(iden);
				prep.setAnua(request.getAnua() + 1);
				prep.setCate(prepAnte.getCate());
				prep.setConc(prepAnte.getConc());
				prep.setPeri(prepAnte.getPeri());
				prep.setMini(prepAnte.getMini());
				prep.setMfin(prepAnte.getMfin());
				prep.setImpo(prepAnte.getImpo());
				prep.setTota(prepAnte.getTota());
				prepDao.save(prep);
			}
		}
		
	}
}
