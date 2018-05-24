package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
import es.ldrsoftware.core.arq.data.CoreNotify;
import es.ldrsoftware.core.btc.entity.Mpla;
import es.ldrsoftware.core.btc.entity.MplaDAO;
import es.ldrsoftware.core.fwk.data.LiteData;

@Component
public class BsMplaSave extends BaseBS {

	@Autowired
	MplaDAO mplaDao;
		
	protected void execute(BaseBSArea a) throws Exception {
		BsMplaSaveArea area = (BsMplaSaveArea)a;
		
		Mpla mpla = mplaDao.save(area.IN.mpla);
		
		area.OUT.mpla = mpla;
	}

	protected void validateInput(BaseBSArea a) throws Exception {
		BsMplaSaveArea area = (BsMplaSaveArea)a;
		
		Mpla mpla = area.IN.mpla;
		
		//Validamos que el maestro de planificación esté informado
		validateDtoRequired(mpla, CoreNotify.MPLA_SAVE_MPLA_RQRD);
		
		//Validamos que la hora del maestro de planificación se encuentre dentro de rango
		validateIntRange(mpla.getHora(), 0000, 2359, CoreNotify.MPLA_SAVE_HORA_RNGE);
		
		//Validamos que el estado del maestro de planificación esté informado, y con un valor correcto.
		validateStringRequired(mpla.getEsta(), CoreNotify.MPLA_SAVE_ESTA_RQRD);
		validateStringDomain(CoreNotify.MPLA_SAVE_ESTA_ERRO, mpla.getEsta(), LiteData.LT_ST_MPLAESTA);
	}

}
