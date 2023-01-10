package es.ldrsoftware.core.btc.bs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ldrsoftware.core.arq.BaseBS;
import es.ldrsoftware.core.arq.data.BaseBSArea;
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
		
		//Validamos que el maestro de planificación esté informado
		Mpla mpla = (Mpla)validateDto(area.IN.mpla, LiteData.LT_EL_DTO_MPLA); 
		
	}

}
