package ldrsoftware.app.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ldrsoftware.app.service.IParametroService;
import ldrsoftware.app.work.WorkClass;
import ldrsoftware.app.comunication.request.ParmRequest;
import ldrsoftware.app.comunication.response.ParametroResponse;
import ldrsoftware.app.core.parm.ParmMaestro;
import ldrsoftware.app.core.parm.valo.BaseParmValo;
import ldrsoftware.app.core.parm.valo.ValoPeriodicid;
import ldrsoftware.app.dao.IHParmDAO;
import ldrsoftware.app.domain.HParm;

@Component
public class ParametroService extends BaseService implements IParametroService, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	public IHParmDAO parmDao;
		
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void obtenerListaParametro(ParametroResponse response, String tabla) throws Exception {

		List<HParm> parmList = parmDao.getListByTbla(tabla); 
		
		if (parmList == null) {
			parmList = new ArrayList<HParm>();
		}
		response.setParmList(parmList);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public BaseParmValo valo(String tabl, String clav) throws Exception {
		BaseParmValo parmValo = new BaseParmValo();
		HParm parm = parmDao.getByTblaClav(tabl, clav);
		if (parm != null) {
			switch (tabl) {
			case ParmMaestro.PERIODICIDAD:
				 parmValo = new ValoPeriodicid(parm);
				 break;
			default:
				break;
			}
		}
		return parmValo;
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
	public void list(ParmRequest request, ParametroResponse response, WorkClass work) throws Exception {

		List<HParm> parmList = parmDao.getListByTbla(request.getTbla()); 
		
		if (parmList == null) {
			parmList = new ArrayList<HParm>();
		}
		
		if (request.isDefa()) {
			HParm parm = new HParm();
			if (request.getDefv() != null) {
				parm.setClav(request.getDefv());
			}
			parmList.add(0, parm);
		}
		response.setParmList(parmList);
	}

}
