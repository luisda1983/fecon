package es.ldrsoftware.fecon.prp.bs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.fecon.data.AppNotify;
import es.ldrsoftware.fecon.data.LiteData;
import es.ldrsoftware.fecon.prp.entity.Pres;
import es.ldrsoftware.fecon.prp.entity.PresDAO;

@Component
public class BsPresSave extends BaseBS {
	
	@Autowired
	public PresDAO presDao;
	
	protected void execute(BaseBSArea a) throws Exception {
		BsPresSaveArea area = (BsPresSaveArea)a;

		Pres pres = area.IN.pres;
		
		//Si el estado es 'N', quiere decir que aún no está creado el registro, y debe pasar a 'A'-Abierto
		if (LiteData.LT_EL_PRESESTA_NO_CREADA.equals(pres.getEsta())) {
			pres.setEsta(LiteData.LT_EL_PRESESTA_ABIERTA);
		}
		
		//TODO: todas las validaciones
		
		pres = presDao.save(pres);
		area.OUT.pres = pres;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsPresSaveArea area = (BsPresSaveArea)a;
		
		validateDto(area.IN.pres, AppNotify.PRES_SAVE_PRES_RQRD);
	}
}