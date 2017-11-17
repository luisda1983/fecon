package es.ldrsoftware.core.fwk.bs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.fwk.entity.Avis;
import es.ldrsoftware.core.fwk.entity.AvisDAO;

@Component
public class BsAvisGet extends BaseBS {

	@Autowired
	AvisDAO avisDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsAvisGetArea area = (BsAvisGetArea)a;
		
		//Obtenemos las tareas pendientes del usuario registrado en la sesión
		List<Avis> avisList = avisDao.getListByEstaInstUsuaPerf("P", SESSION.get().inst, SESSION.get().usua, SESSION.get().perf);
		
		area.OUT.avisList = avisList;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		//BsAvisGetArea area = (BsAvisGetArea)a;
	}

}
